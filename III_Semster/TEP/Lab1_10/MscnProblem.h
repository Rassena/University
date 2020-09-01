#pragma once
#include "Matrix.h"
#include "Random.h"
#include <algorithm>
class MscnProblem
{
public:
	MscnProblem() { initialized = false; solutionSize = 0; };
	bool initialize(int seed, int suppliers, int factories, int DCs, int shops, bool &error);
	bool initializeWithInput(int seed, int suppliers, int factories, int DCs, int shops, double lowerCostBound_, double upperCostBound_, double lowerRestrictionsBound_, double upperRestrictionsBound_,
		double lowerAdditionalCostBound_, double upperAdditionalCostBound_, double shopPriceMultiplier_, double min_, double lowerGapBound_, double upperGapBound_, double max_, bool &error);
	bool initalizeProblem(int suppliers, int factories, int DCs, int shops, bool &error);
	bool initialize(std::string fileName);
	bool setRandom(int seed);
	void showProblem();
	bool showBestSolution();
	bool showSolution(double *solution);
	bool saveToFile(std::string fileName);
	//size changing methods
	bool changeNumberOfSuppliers(int amountOfSuppliers);
	bool changeNumberOfFactories(int amountOfFactories);
	bool changeNumberOfDCs(int amountOfDC);
	bool changeNumberOfShops(int amountOfShops);
	//size getting methods
	int getSizeofSuppliers() { return suppliersRestrictions->getWidht(0); }
	int getSizeofFactories() { return factoriesRestrictions->getWidht(0); }
	int getSizeofDCs() { return DCsRestrictions->getWidht(0); }
	int getSizeofShops() { return shopsRestrictions->getWidht(0); }
	//setting values methods
	bool setValueofSuppliersMatrix(int indexOfRow, int indexOfColumn, double value);
	bool setValueofFactoriesMatrix(int indexOfRow, int indexOfColumn, double value);
	bool setValueofDCsMatrix(int indexOfRow, int indexOfColumn, double value);

	bool setValueofSuppliersRestrictions(int index, double value);
	bool setValueofFactoriesRestrictions(int index, double value);
	bool setValueofDCsRestrictions(int index, double value);
	bool setValueofShopsRestrictions(int index, double value);

	bool setValueofAdditionalCostOfSuppliers(int index, double value);
	bool setValueofAdditionalCostOfFactories(int index, double value);
	bool setValueofAdditionalCostOfDCs(int index, double value);

	//solution methods
	bool initalizeSolutionGenerator();
	double* getRandomSolution();
	double getBestQuality(int tries, bool &error);
	double* getBestSolution();
	double getQuality(double *solution, bool &error);
	double* readSolution(std::string fileName, bool &error);
	int solutionSize;

	bool initialized;
	Matrix *suppliersRestrictions, *factoriesRestrictions, *DCsRestrictions, *shopsRestrictions;
	Matrix *maxSuppliers, *maxFactories, *maxDCs;
	Matrix *minSuppliers, *minFactories, *minDCs;
	~MscnProblem();
private:
	Random *random;
	Matrix *suppliersMatrix, *factoriesMatrix, *DCsMatrix;
	Matrix *additionalCostOfSuppliers, *additionalCostOfFactories, *additionalCostOfDCs;
	Matrix *shopsPrices;
	double *bestSolution;

	bool setValueofSomething(Matrix *someMatrix, int indexOfRow, int indexOfColumn, double value);
	bool changeNumerOfSomething(Matrix *restricions, Matrix *addtionalCost, Matrix *fromMatrix, Matrix* toMatrix, int amountOfFactories);
	bool addSomething(Matrix *restricions, Matrix *addtionalCost, Matrix *fromMatrix, Matrix* toMatrix);
	bool deleteSomething(Matrix *restricions, Matrix *addtionalCost, Matrix *fromMatrix, Matrix* toMatrix);
	double qualityCounter(int currentSizeA, int currentSizeB, double *solution, Matrix *additionalCost, Matrix *restrictions, Matrix *matrix, Matrix* min, Matrix* max, bool &error);
	bool containsSatisfied(double *solution);
	bool containsGoodWeights(int sizeFrom, int sizeThis, int sizeTo, double *solution);
	Matrix* createMatrix(int height, int width, FILE *file);
	bool saveMatrix(Matrix *matrix, FILE *file);
	bool restrictionsCheck(double *solution);
	bool restrictionsCheckFor(Matrix* fromRestrictions, Matrix* toRestrictions, double *solution);
	void deleteAll();
};

