#include "pch.h"
#define _CRT_SECURE_NO_DEPRECATE
#include <string>
#include <stdio.h>
#include <iostream>
#include "MscnProblem.h"
#include "Random.h"
#define VERY_SMALL_DOUBLE 0.1

bool MscnProblem::initialize(int seed, int suppliers, int factories, int DCs, int shops, bool &error)
{
	if (initialized) deleteAll();
	random = new Random(seed);
	solutionSize = suppliers * factories + factories * DCs + DCs * shops;
	return initalizeProblem(suppliers,factories, DCs, shops, error);
}

bool MscnProblem::initializeWithInput(int seed, int suppliers, int factories, int DCs, int shops, double lowerCostBound_, double upperCostBound_, double lowerRestrictionsBound_, double upperRestrictionsBound_,
	double lowerAdditionalCostBound_, double upperAdditionalCostBound_, double shopPriceMultiplier_, double min_, double lowerGapBound_, double upperGapBound_, double max_, bool &error)
{
	if (initialized) deleteAll();
	random = new Random(seed, lowerCostBound_, upperCostBound_, lowerRestrictionsBound_, upperRestrictionsBound_,
		lowerAdditionalCostBound_, upperAdditionalCostBound_, shopPriceMultiplier_, min_, lowerGapBound_, upperGapBound_, max_, error);
	solutionSize = suppliers * factories + factories * DCs + DCs * shops;
	return initalizeProblem(suppliers, factories, DCs, shops, error);
}

bool MscnProblem::initalizeProblem(int suppliers, int factories, int DCs, int shops, bool &error)
{
	std::vector<Matrix*> *vector = random->createProblem(suppliers, factories, DCs, shops, error);

	suppliersMatrix = vector->at(0);
	factoriesMatrix = vector->at(1);
	DCsMatrix = vector->at(2);

	suppliersRestrictions = vector->at(3);
	factoriesRestrictions = vector->at(4);
	DCsRestrictions = vector->at(5);
	shopsRestrictions = vector->at(6);

	additionalCostOfSuppliers = vector->at(7);
	additionalCostOfFactories = vector->at(8);
	additionalCostOfDCs = vector->at(9);

	shopsPrices = vector->at(10);

	minSuppliers = vector->at(11);
	minFactories = vector->at(12);
	minDCs = vector->at(13);

	maxSuppliers = vector->at(14);
	maxFactories = vector->at(15);
	maxDCs = vector->at(16);

	initialized = true;
	bestSolution = NULL;
	return true;
}

bool MscnProblem::initialize(std::string fileName)
{
	random = NULL;
	FILE *file = std::fopen(fileName.c_str(), "r");
	if (file == NULL) return false;
	int suppliers, factories, DCs, shops;
	fscanf(file, " %*s %d %*s %d %*s %d %*s %d", &suppliers, &factories, &DCs, &shops);
	suppliersMatrix = createMatrix(suppliers, factories, file);
	factoriesMatrix = createMatrix(factories, DCs, file);
	DCsMatrix = createMatrix(DCs, shops, file);

	suppliersRestrictions = createMatrix(1, suppliers, file);
	factoriesRestrictions = createMatrix(1, factories, file);
	DCsRestrictions = createMatrix(1, DCs, file);
	shopsRestrictions = createMatrix(1, shops, file);

	additionalCostOfSuppliers = createMatrix(1, suppliers, file);
	additionalCostOfFactories = createMatrix(1, factories, file);
	additionalCostOfDCs = createMatrix(1, DCs, file);
	shopsPrices = createMatrix(1, shops, file);

	maxSuppliers = createMatrix(suppliers, factories, file);
	maxFactories = createMatrix(factories, DCs, file);
	maxDCs = createMatrix(DCs, shops, file);

	minSuppliers = createMatrix(suppliers, factories, file);
	minFactories = createMatrix(factories, DCs, file);
	minDCs = createMatrix(DCs, shops, file);

	initialized = true;
	bestSolution = NULL;
	std::fclose(file);
	return true;
}

bool MscnProblem::setRandom(int seed)
{
	random = new Random(seed);
	return true;
}

void MscnProblem::showProblem()
{
	if (initialized)
	{
		std::cout << "--------------------------------------------------\n";
		std::cout << "Macierz kosztow dostawy dostawcy-fabryki\n";
		suppliersMatrix->show();
		std::cout << "Macierz kosztow dostawy fabryki-centra dystrybucji\n";
		factoriesMatrix->show();
		std::cout << "Macierz kosztow dostawy centra dystrybucji-sklepy\n";
		DCsMatrix->show();

		std::cout << "Tablica ograniczen dostawcow\n";
		suppliersRestrictions->show();
		std::cout << "Tablica ograniczen fabryk\n";
		factoriesRestrictions->show();
		std::cout << "Tablica ograniczen centrow dystrybucji\n";
		DCsRestrictions->show();
		std::cout << "Tablica ograniczen sklepow\n";
		shopsRestrictions->show();

		std::cout << "Tablica jednorazowych kosztow dostawcow\n";
		additionalCostOfSuppliers->show();
		std::cout << "Tablica jednorazowych kosztow fabryk\n";
		additionalCostOfFactories->show();
		std::cout << "Tablica jednorazowych kosztow centrow dystybucji\n";
		additionalCostOfDCs->show();
		std::cout << "Tablica cen sklepowych\n";
		shopsPrices->show();

		std::cout << "Max dostawcy-fabryki\n";
		maxSuppliers->show();
		std::cout << "Max fabryki-centra dystrybucji\n";
		maxFactories->show();
		std::cout << "Max centra dystrybucji-sklepy\n";
		maxDCs->show();
		std::cout << "Min dostawcy-fabryki\n";
		minSuppliers->show();
		std::cout << "Min fabryki-centra dystrybucji\n";
		minFactories->show();
		std::cout << "Min centra dystrybucji-sklepy\n";
		minDCs->show();

		std::cout << "Liczba dostawcow: "
			<< getSizeofSuppliers()
			<< "\nLiczba fabryk : "
			<< getSizeofFactories()
			<< "\nLiczba centrow dystybucji : "
			<< getSizeofDCs()
			<< "\nLiczba sklepow : "
			<< getSizeofShops() << "\n\n";
		std::cout << "--------------------------------------------------\n";

	}
	else std::cout << "Problem nie zainicjalizowany!\n";
}

bool MscnProblem::showBestSolution()
{
	std::cout << "Najlepsze rozwiazanie: \n";
	if (bestSolution == NULL) return false;
	int size = getSizeofSuppliers()*getSizeofFactories() + getSizeofFactories()*getSizeofDCs() + getSizeofDCs()*getSizeofShops();
	for (int i = 0; i < size; i++)
	{
		std::cout << bestSolution[i] << " ";
	}
	std::cout << "\n";
	return true;
}

bool MscnProblem::showSolution(double *solution)
{
	for (int i = 0; i < solutionSize; i++)
	{
		std::cout << solution[i] << " ";
	}
	std::cout << "\n";
	return true;
}

bool MscnProblem::saveToFile(std::string fileName)
{
	if (!initialized) return false;
	FILE *file = std::fopen(fileName.c_str(), "w+");
	std::fprintf(file, "suppliers: ");
	std::fprintf(file, std::to_string(getSizeofSuppliers()).c_str());

	std::fprintf(file, "\nfactories: ");
	std::fprintf(file, std::to_string(getSizeofFactories()).c_str());

	std::fprintf(file, "\nDCs: ");
	std::fprintf(file, std::to_string(getSizeofDCs()).c_str());

	std::fprintf(file, "\nshops: ");
	std::fprintf(file, std::to_string(getSizeofShops()).c_str());

	std::fprintf(file, "\n\nsuppliersMatrix\n");
	saveMatrix(suppliersMatrix, file);
	std::fprintf(file, "factoriesMatrix\n");
	saveMatrix(factoriesMatrix, file);
	std::fprintf(file, "DCMatrix\n");
	saveMatrix(DCsMatrix, file);

	std::fprintf(file, "suppliersRestrictions\n");
	saveMatrix(suppliersRestrictions, file);
	std::fprintf(file, "factoriesRestrictions\n");
	saveMatrix(factoriesRestrictions, file);
	std::fprintf(file, "DCRestrictions\n");
	saveMatrix(DCsRestrictions, file);
	std::fprintf(file, "shopsRestrictions\n");
	saveMatrix(shopsRestrictions, file);

	std::fprintf(file, "additionalCostOfSuppliers\n");
	saveMatrix(additionalCostOfSuppliers, file);
	std::fprintf(file, "additionalCostOfFactories\n");
	saveMatrix(additionalCostOfFactories, file);
	std::fprintf(file, "additionalCostOfDC\n");
	saveMatrix(additionalCostOfDCs, file);
	std::fprintf(file, "shopsPrices\n");
	saveMatrix(shopsPrices, file);

	std::fprintf(file, "maxSuppliers: ");
	saveMatrix(maxSuppliers, file);

	std::fprintf(file, "\nmaxFactories: ");
	saveMatrix(maxFactories, file);

	std::fprintf(file, "\nmaxDCs: ");
	saveMatrix(maxDCs, file);

	std::fprintf(file, "minSuppliers: ");
	saveMatrix(minSuppliers, file);

	std::fprintf(file, "\nminFactories: ");
	saveMatrix(minFactories, file);

	std::fprintf(file, "\nminDCs: ");
	saveMatrix(minDCs, file);

	std::fclose(file);
	return true;
}

//size changing methods
bool MscnProblem::changeNumberOfSuppliers(int amountOfSuppliers)
{
	if (!initialized) return false;
	return changeNumerOfSomething(suppliersRestrictions, additionalCostOfSuppliers, NULL, suppliersMatrix, amountOfSuppliers);
}

bool MscnProblem::changeNumberOfFactories(int amountOfFactories)
{
	if (!initialized) return false;
	return changeNumerOfSomething(factoriesRestrictions, additionalCostOfFactories, suppliersMatrix, factoriesMatrix, amountOfFactories);
}

bool MscnProblem::changeNumberOfDCs(int amountOfDC)
{
	if (!initialized) return false;
	return changeNumerOfSomething(DCsRestrictions, additionalCostOfDCs, factoriesMatrix, DCsMatrix, amountOfDC);
}

bool MscnProblem::changeNumberOfShops(int amountOfShops)
{
	if (!initialized) return false;
	return changeNumerOfSomething(shopsRestrictions, NULL, DCsMatrix, NULL, amountOfShops);
}

//setting values methods
bool MscnProblem::setValueofSuppliersMatrix(int indexOfRow, int indexOfColumn, double value)
{
	if (!initialized) return false;
	return setValueofSomething(suppliersMatrix, indexOfRow, indexOfColumn, value);
}

bool MscnProblem::setValueofFactoriesMatrix(int indexOfRow, int indexOfColumn, double value)
{
	if (!initialized) return false;
	return setValueofSomething(factoriesMatrix, indexOfRow, indexOfColumn, value);
}

bool MscnProblem::setValueofDCsMatrix(int indexOfRow, int indexOfColumn, double value)
{
	if (!initialized) return false;
	return setValueofSomething(DCsMatrix, indexOfRow, indexOfColumn, value);
}

bool MscnProblem::setValueofSuppliersRestrictions(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(suppliersRestrictions, 0, index, value);
}

bool MscnProblem::setValueofFactoriesRestrictions(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(factoriesRestrictions, 0, index, value);
}

bool MscnProblem::setValueofDCsRestrictions(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(DCsRestrictions, 0, index, value);
}

bool MscnProblem::setValueofShopsRestrictions(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(shopsRestrictions, 0, index, value);
}

bool MscnProblem::setValueofAdditionalCostOfSuppliers(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(additionalCostOfSuppliers, 0, index, value);
}

bool MscnProblem::setValueofAdditionalCostOfFactories(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(additionalCostOfFactories, 0, index, value);
}

bool MscnProblem::setValueofAdditionalCostOfDCs(int index, double value)
{
	if (!initialized) return false;
	return setValueofSomething(additionalCostOfDCs, 0, index, value);
}

bool MscnProblem::initalizeSolutionGenerator()
{
	random->setMatrices(suppliersRestrictions, factoriesRestrictions, DCsRestrictions, shopsRestrictions,
		maxSuppliers, maxFactories, maxDCs, minSuppliers, minFactories, minDCs);
	return true;
}

//solution methods
double* MscnProblem::getRandomSolution()
{
	return random->getSolution();
}

double MscnProblem::getBestQuality(int tries, bool &error)
{
	if (bestSolution == NULL)
	{
		bestSolution = getRandomSolution();
	}
	double *currentSolution = getRandomSolution();
	double bestQuality = getQuality(bestSolution, error), currentQuality = getQuality(currentSolution, error);
	for (int i = 0; i < tries; i++)
	{
		if (bestQuality < currentQuality)
		{
			delete bestSolution;
			bestSolution = currentSolution;
			bestQuality = currentQuality;
			//std::cout << "Nowe najlepsze rozwiazanie: " << bestQuality << "\n";
			currentSolution = getRandomSolution();
			currentQuality = getQuality(currentSolution, error);
		}
		else
		{
			delete currentSolution;
			currentSolution = getRandomSolution();
			currentQuality = getQuality(currentSolution, error);
		}
	}
	return bestQuality;
}

double* MscnProblem::getBestSolution()
{
	if (bestSolution == NULL) return new double[0];
	return bestSolution;
}

double MscnProblem::getQuality(double *solution, bool &error)
{
	if (!initialized)
	{
		error = true;
		return -1;
	}
	if (!containsSatisfied(solution))
	{
		error = true;
		return -1;
	}
	double costs = 0;
	costs += qualityCounter(getSizeofSuppliers(), getSizeofFactories(), solution, additionalCostOfSuppliers, suppliersRestrictions, suppliersMatrix, minSuppliers, maxSuppliers, error);
	if (error) return -1;
	solution += getSizeofSuppliers()*getSizeofFactories();
	costs += qualityCounter(getSizeofFactories(), getSizeofDCs(), solution, additionalCostOfFactories, factoriesRestrictions, factoriesMatrix, minFactories, maxFactories, error);
	if (error) return -1;
	solution += getSizeofFactories()*getSizeofDCs();
	costs += qualityCounter(getSizeofDCs(), getSizeofShops(), solution, additionalCostOfDCs, DCsRestrictions, DCsMatrix, minDCs, maxDCs, error);
	if (error) return -1;

	double profit = 0;
	int currentSizeA = getSizeofDCs();
	int currentSizeB = getSizeofShops();
	for (int i = 0; i < currentSizeA; i++)
	{
		for (int j = 0; j < currentSizeB; j++)
		{
			if (solution[i* currentSizeB + j] > shopsRestrictions->getValueAt(0, j))
			{
				error = true;
				return -1;
			}
			profit += solution[i* currentSizeB + j] * shopsPrices->getValueAt(0, j);
		}
	}
	if (error) return -1;
	return profit - costs;
}

double* MscnProblem::readSolution(std::string fileName, bool &error)
{
	if (!initialized)
	{
		error = true;
		return NULL;
	}
	FILE *file = std::fopen(fileName.c_str(), "r");
	if (file == NULL) return false;
	int suppliers, factories, DCs, shops;
	fscanf(file, " %*s %d %*s %d %*s %d %*s %d", &suppliers, &factories, &DCs, &shops);
	int goodLength = suppliers * factories +
		factories * DCs +
		DCs * shops;
	double *solution = new double[goodLength];
	double number = 0;
	for (int i = 0; i < goodLength; i++)
	{
		if (i == 0 || i == suppliers * factories || i == suppliers * factories + factories * DCs)
		{
			fscanf(file, " %*s");
		}
		fscanf(file, " %lf", &number);
		solution[i] = number;
	}
	std::fclose(file);
	return solution;
}

MscnProblem::~MscnProblem()
{
	deleteAll();
}

//private methods
bool MscnProblem::setValueofSomething(Matrix *someMatrix, int indexOfRow, int indexOfColumn, double value)
{
	return someMatrix->setValueAt(indexOfRow, indexOfColumn, value);
}

bool MscnProblem::changeNumerOfSomething(Matrix *restricions, Matrix *addtionalCost, Matrix *thisMatrix, Matrix *previousMatrix, int amountOfSomething)
{
	if (amountOfSomething < 0) return false;
	int difference = restricions->getWidht(0) - amountOfSomething;
	if (difference > 0)
	{
		for (int i = 0; i < difference; i++)
			if (!deleteSomething(restricions, addtionalCost, thisMatrix, previousMatrix)) return false;
		return true;
	}
	else
	{
		difference = -difference;
		for (int i = 0; i < difference; i++)
			if (!addSomething(restricions, addtionalCost, thisMatrix, previousMatrix)) return false;
		return true;
	}
}

bool MscnProblem::addSomething(Matrix *restricions, Matrix *addtionalCost, Matrix *thisMatrix, Matrix *previousMatrix)
{
	if (!restricions->addToRow(0, -1)) return false;
	if (addtionalCost != NULL) if (!addtionalCost->addToRow(0, -1)) return false;
	if (thisMatrix != NULL)
		for (int i = 0; i < thisMatrix->getHeight(); i++)
		{
			if (!thisMatrix->addToRow(i, -1)) return false;
		}
	if (previousMatrix != NULL)
	{
		previousMatrix->addRow();
		for (int i = 0; i < previousMatrix->getWidht(0); i++)
		{
			if (!previousMatrix->addToRow(previousMatrix->getHeight() - 1, -1)) return false;
		}
	}
	return true;
}

bool MscnProblem::deleteSomething(Matrix *restricions, Matrix *addtionalCost, Matrix *thisMatrix, Matrix* previousMatrix)
{
	if (!restricions->deleteColumn(0)) return false;
	if (addtionalCost != NULL) if (!addtionalCost->deleteColumn(0)) return false;
	if (thisMatrix != NULL) if (!thisMatrix->deleteColumn(0)) return false;
	if (previousMatrix != NULL) if (!previousMatrix->deleteRow(previousMatrix->getHeight() - 1)) return false;
	return true;
}

double MscnProblem::qualityCounter(int currentSizeA, int currentSizeB, double *solution, Matrix *additionalCost, Matrix *restrictions, Matrix *matrix, Matrix* min, Matrix *max, bool &error)
{
	double cost = 0;
	bool paid = false;
	double currentSolution;
	double currentSumOfCargo;
	for (int i = 0; i < currentSizeA; i++)
	{
		currentSumOfCargo = 0;
		for (int j = 0; j < currentSizeB; j++)
		{
			currentSolution = solution[i*currentSizeB + j];
			if ((currentSolution < min->getValueAt(i, j)
				|| currentSolution > max->getValueAt(i, j)) && currentSolution != 0)
			{
				error = true;
				return -1;
			}
			if (paid == false && currentSolution != 0)
			{
				cost += additionalCost->getValueAt(0, i);
				paid = true;
			}// paid check
			currentSumOfCargo += currentSolution;
			cost += currentSolution * matrix->getValueAt(i, j);
		}
		paid = false;
	}
	return cost;
}

bool MscnProblem::containsSatisfied(double *solution)
{
	if (solution == NULL) return false;
	if (!restrictionsCheck(solution)) return false; 
	int goodLength = getSizeofSuppliers()*getSizeofFactories() +
		getSizeofFactories()*getSizeofDCs() +
		getSizeofDCs()*getSizeofShops();
	for (int i = 0; i < goodLength; i++)
	{
		if (solution[i] < 0) return false; 
	}
	if (!containsGoodWeights(getSizeofSuppliers(), getSizeofFactories(), getSizeofDCs(), solution)) return false; 
	solution += getSizeofSuppliers() * getSizeofFactories();
	if (!containsGoodWeights(getSizeofFactories(), getSizeofDCs(), getSizeofShops(), solution)) return false; 
	return true;
}

bool MscnProblem::containsGoodWeights(int sizeFrom, int sizeThis, int sizeTo, double *solution)
{
	double inputCounter, outputCounter;
	double *currentSolution = solution;
	for (int i = 0; i < sizeThis; i++)
	{
		inputCounter = 0;
		for (int j = 0; j < sizeFrom; j++)
		{
			inputCounter += currentSolution[j * sizeThis + i];
		}
		currentSolution += sizeThis * sizeFrom;
		outputCounter = 0;
		for (int j = 0; j < sizeTo; j++)
		{
			outputCounter += currentSolution[i * sizeTo + j];
		}
		currentSolution = solution;
		if (inputCounter + VERY_SMALL_DOUBLE < outputCounter) return false;
	}
	return true;
}

Matrix* MscnProblem::createMatrix(int height, int width, FILE *file)
{
	Matrix *current = new Matrix();
	current->initialize();
	double number = 0;
	fscanf(file, " %*s");
	for (int i = 0; i < height; i++)
	{
		current->addRow();
		for (int j = 0; j < width; j++)
		{
			fscanf(file, "%lf", &number);
			current->addToRow(i, number);
		}
	}
	return current;
}

bool MscnProblem::saveMatrix(Matrix *matrix, FILE *file)
{
	for (int i = 0; i < matrix->getHeight(); i++)
	{
		for (int j = 0; j < matrix->getWidht(i); j++)
		{
			std::fprintf(file, (std::to_string(matrix->getValueAt(i, j)).c_str()));
			std::fprintf(file, " ");
		}
		std::fprintf(file, "\n");
	}
	std::fprintf(file, "\n");
	return true;
}

bool MscnProblem::restrictionsCheck(double *solution)
{
	if (!restrictionsCheckFor(suppliersRestrictions, factoriesRestrictions, solution)) return false;
	solution += getSizeofSuppliers()*getSizeofFactories();
	if (!restrictionsCheckFor(factoriesRestrictions, DCsRestrictions, solution)) return false;
	solution += getSizeofFactories()*getSizeofDCs();
	if (!restrictionsCheckFor(DCsRestrictions, shopsRestrictions, solution)) return false;
	return true;
}

bool MscnProblem::restrictionsCheckFor(Matrix* fromRestrictions, Matrix* toRestrictions, double *solution)
{
	int sizeFrom = fromRestrictions->getWidht(0);
	int sizeTo = toRestrictions->getWidht(0);
	for (int i = 0; i < sizeFrom; i++)
	{
		double sumOfVerse = 0;
		for (int j = 0; j < sizeTo; j++)
		{
			sumOfVerse += solution[i * sizeTo + j];
		}
		if (sumOfVerse > fromRestrictions->getValueAt(0, i)) return false;
	}
	for (int i = 0; i < sizeTo; i++)
	{
		double sumOfColumn = 0;
		for (int j = 0; j < sizeFrom; j++)
		{
			sumOfColumn += solution[j * sizeTo + i];
		}
		if (sumOfColumn > toRestrictions->getValueAt(0, i)) return false;
	}
	return true;
}

void MscnProblem::deleteAll()
{
	delete suppliersMatrix;
	delete factoriesMatrix;
	delete DCsMatrix;

	delete suppliersRestrictions;
	delete factoriesRestrictions;
	delete DCsRestrictions;
	delete shopsRestrictions;

	delete additionalCostOfSuppliers;
	delete additionalCostOfFactories;
	delete additionalCostOfDCs;
	delete shopsPrices;
}
