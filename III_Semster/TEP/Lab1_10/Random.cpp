#include "pch.h"
#include "Random.h"
#include <random>
#include <iostream>
#include <time.h>

#define LOWER_COST_BOUND 5
#define UPPER_COST_BOUND 10
#define LOWER_RESTRICTIONS_BOUND 15
#define UPPER_RESTRICTIONS_BOUND 20
#define LOWER_ADDITIONAL_COST_BOUND 1
#define UPPER_ADDITIONAL_COST_BOUND 2
#define SHOP_PRICE_MULTIPLIER 50
#define MIN 0
#define MAX 20

Random::Random(int seed)
{
	srand(seed);
	lowerCostBound = LOWER_COST_BOUND;
	upperCostBound = UPPER_COST_BOUND;
	lowerRestrictionsBound = LOWER_RESTRICTIONS_BOUND;
	upperRestrictionsBound = UPPER_RESTRICTIONS_BOUND;
	lowerAdditionalCostBound = LOWER_ADDITIONAL_COST_BOUND;
	upperAdditionalCostBound = UPPER_ADDITIONAL_COST_BOUND;
	shopPriceMultiplier = SHOP_PRICE_MULTIPLIER;
	min = MIN;
	lowerGapBound = MIN;
	upperGapBound = MAX;
	max = MAX;
}

Random::Random(int seed, double lowerCostBound_, double upperCostBound_, double lowerRestrictionsBound_, double upperRestrictionsBound_,
	double lowerAdditionalCostBound_, double upperAdditionalCostBound_, double shopPriceMultiplier_, double min_, double lowerGapBound_, double upperGapBound_, double max_, bool &error)
{
	srand(seed);
	lowerCostBound = lowerCostBound_;
	upperCostBound = upperCostBound_;
	lowerRestrictionsBound = lowerRestrictionsBound_;
	upperRestrictionsBound = upperRestrictionsBound_;
	lowerAdditionalCostBound = lowerAdditionalCostBound_;
	upperAdditionalCostBound = upperAdditionalCostBound_;
	shopPriceMultiplier = shopPriceMultiplier_;
	min = min_;
	lowerGapBound = lowerGapBound_;
	upperGapBound = upperGapBound_;
	max = max;

	if (lowerCostBound_ > upperCostBound_ || lowerAdditionalCostBound_ > upperAdditionalCostBound_ || lowerRestrictionsBound_ > upperRestrictionsBound_ ||
		lowerAdditionalCostBound_ < 0 || upperAdditionalCostBound_ < 0 || lowerCostBound_ < 0 || upperCostBound_ < 0 || lowerRestrictionsBound_ < 0 || upperRestrictionsBound_ < 0 || shopPriceMultiplier_ < 0
		|| min >0 || lowerGapBound_ > 0 || upperGapBound_ > 0 || max_ > 0 || min_ > lowerGapBound_ || lowerGapBound_ > upperGapBound_ || upperGapBound_ > max_) error = true;
}

std::vector<Matrix*>* Random::createProblem(int suppliers, int factories, int DCs, int shops, bool &error)
{
	std::vector<Matrix*> *vector = new std::vector<Matrix*>();
	if (suppliers > 0 && factories > 0 && DCs > 0 && shops > 0)
	{
		vector->push_back(createMatrix(suppliers, factories, lowerCostBound, upperCostBound));
		vector->push_back(createMatrix(factories, DCs, lowerCostBound, upperCostBound));
		vector->push_back(createMatrix(DCs, shops, lowerCostBound, upperCostBound));

		vector->push_back(createMatrix(1, suppliers, lowerRestrictionsBound, upperRestrictionsBound));
		vector->push_back(createMatrix(1, factories, lowerRestrictionsBound, upperRestrictionsBound));
		vector->push_back(createMatrix(1, DCs, lowerRestrictionsBound, upperRestrictionsBound));
		vector->push_back(createMatrix(1, shops, lowerRestrictionsBound, upperRestrictionsBound));

		vector->push_back(createMatrix(1, suppliers, lowerAdditionalCostBound, upperAdditionalCostBound));
		vector->push_back(createMatrix(1, factories, lowerAdditionalCostBound, upperAdditionalCostBound));
		vector->push_back(createMatrix(1, DCs, lowerAdditionalCostBound, upperAdditionalCostBound));

		vector->push_back(createMatrix(1, shops, lowerCostBound*shopPriceMultiplier, upperCostBound*shopPriceMultiplier));

		vector->push_back(createMatrix(suppliers, factories, min, lowerGapBound));
		vector->push_back(createMatrix(factories, DCs, min, lowerGapBound));
		vector->push_back(createMatrix(DCs, shops, min, lowerGapBound));

		vector->push_back(createMatrix(suppliers, factories, upperGapBound, max));
		vector->push_back(createMatrix(factories, DCs, upperGapBound, max));
		vector->push_back(createMatrix(DCs, shops, upperGapBound, max));
	}
	else error = true;
	return vector;
}

bool Random::setMatrices(Matrix *suppliersRestrictions_, Matrix *factoriesRestrictions_, Matrix *DCsRestrictions_, Matrix *shopsRestrictions_,
	Matrix *maxSuppliers_, Matrix *maxFactories_, Matrix *maxDCs_, Matrix *minSuppliers_, Matrix *minFactories_, Matrix *minDCs_)
{
	suppliersRestrictions = suppliersRestrictions_;
	factoriesRestrictions = factoriesRestrictions_;
	DCsRestrictions = DCsRestrictions_;
	shopsRestrictions = shopsRestrictions_;
	maxSuppliers = maxSuppliers_;
	maxFactories = maxFactories_;
	maxDCs = maxDCs_;
	minSuppliers = minSuppliers_;
	minFactories = minFactories_;
	minDCs = minDCs_;
	return true;
}

double* Random::getSolution()
{
	int supAmount = suppliersRestrictions->getWidht(0);
	int facAmount = factoriesRestrictions->getWidht(0);
	int DCsAmount = DCsRestrictions->getWidht(0);
	int shopsAmount = shopsRestrictions->getWidht(0);
	double *solution = new double[supAmount*facAmount + facAmount * DCsAmount + DCsAmount * shopsAmount];
	for (int i = 0; i < supAmount*facAmount + facAmount * DCsAmount + DCsAmount * shopsAmount; i++) solution[i] = 0;
	double maxRestriction = getDoubleRandom(0, findMinRestriction());
	Matrix* suppliersWeights = new Matrix();
	suppliersWeights->initialize();
	suppliersWeights->addRow();
	for (int i = 0; i < supAmount; i++)
	{
		suppliersWeights->addToRow(0, getDoubleRandom(0, std::min(maxRestriction/supAmount, max*supAmount)));
	}

	Matrix* factoriesWeights = createMatrix(1, facAmount, 0, 0);
	Matrix* DCsWeights = createMatrix(1, DCsAmount, 0, 0);
	Matrix* shopsWeights = createMatrix(1, shopsAmount, 0, 0);

	balanceSolution(suppliersWeights, factoriesRestrictions, factoriesWeights, minSuppliers, maxSuppliers, solution);
	balanceSolution(factoriesWeights, DCsRestrictions, DCsWeights, minFactories, maxFactories, solution + supAmount * facAmount);
	balanceSolution(DCsWeights, shopsRestrictions, shopsWeights, minDCs, maxDCs, solution + supAmount * facAmount + facAmount * DCsAmount);

	delete suppliersWeights, factoriesWeights, DCsWeights, shopsWeights;

	return solution;
}

int Random::getIntegerRandom(int min, int max)
{
	return rand() % (max - min + 1) + min;
}

double Random::getDoubleRandom(double min, double max)
{
	return min + ((double)rand() / RAND_MAX) * (max - min);
}

Matrix* Random::createMatrix(int height, int width, double min, double max)
{
	Matrix *current = new Matrix();
	current->initialize();
	if (min <= max)
	{
		for (int i = 0; i < height; i++)
		{
			current->addRow();
			for (int j = 0; j < width; j++)
			{
				if (min == max) current->addToRow(i, min);
				else current->addToRow(i, getDoubleRandom(min, max));
			}
		}
	}
	return current;
}

bool Random::balanceSolution(Matrix* inputWeights, Matrix* outputRestrictions, Matrix* outputWeights, Matrix* min, Matrix* max, double* solution)
{
	int inputAmount = inputWeights->getWidht(0);
	int outputAmount = outputRestrictions->getWidht(0);
	for (int i = 0; i < inputAmount; i++)
	{
		double currentInputWeight = inputWeights->getValueAt(0, i);
		double cargo = -1;
		int destination = 0;
		while (currentInputWeight > lowerGapBound && cargo != 0)
		{
			destination = getIntegerRandom(0, outputAmount - 1);
			double upperBound = std::min(max->getValueAt(i, destination),
				outputRestrictions->getValueAt(0, destination) - outputWeights->getValueAt(0, destination)) - solution[i*outputAmount + destination];
			if (upperBound < min->getValueAt(i, destination))
			{
				cargo = 0;
				currentInputWeight = 0;
			}
			else cargo = getDoubleRandom(min->getValueAt(i, destination), upperBound);
			if (cargo > currentInputWeight) cargo = currentInputWeight;
			currentInputWeight -= cargo;
			outputWeights->setValueAt(0, destination, outputWeights->getValueAt(0, destination) + cargo);
			solution[i*outputAmount + destination] += cargo;
		}
	}
	return true;
}

double Random::findMinRestriction()
{
	double min = findSumOfRestrictions(suppliersRestrictions);
	double current = findSumOfRestrictions(factoriesRestrictions);
	if (min > current) min = current;
	current = findSumOfRestrictions(DCsRestrictions);
	if (min > current) min = current;
	current = findSumOfRestrictions(shopsRestrictions);
	if (min > current) min = current;
	return min;
}

double Random::findSumOfRestrictions(Matrix *restriction)
{
	double sum = 0;
	int length = restriction->getWidht(0);
	for (int i = 0; i < length; i++)
	{
		sum += restriction->getValueAt(0, i);
	}
	return sum;
}