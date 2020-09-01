#pragma once
#include "Matrix.h"
#include <algorithm>
class Random
{
public:
	Random(int seed);
	Random(int seed, double lowerCostBound_, double upperCostBound_, double lowerRestrictionsBound_, double upperRestrictionsBound_,
		double lowerAdditionalCostBound_, double upperAdditionalCostBound_, double shopPriceMultiplier_, double min_, double lowerGapBound_, double upperGapBound_, double max_, bool &error);

	std::vector<Matrix*>* createProblem(int suppliers, int factories, int DCs, int shops, bool &error);
	bool setMatrices(Matrix *suppliersRestrictions_, Matrix *factoriesRestrictions_, Matrix *DCsRestrictions_, Matrix *shopsRestrictions_,
		Matrix *maxSuppliers_, Matrix *maxFactories_, Matrix *maxDCs_, Matrix *minSuppliers_, Matrix *minFactories_, Matrix *minDCs_);
	double* getSolution();

	static int getIntegerRandom(int min, int max);
	static double getDoubleRandom(double min, double max);

	~Random() {};
private:
	double lowerCostBound, upperCostBound, lowerRestrictionsBound, upperRestrictionsBound, lowerAdditionalCostBound, upperAdditionalCostBound, shopPriceMultiplier;
	double min, lowerGapBound, upperGapBound, max;
	Matrix *suppliersRestrictions, *factoriesRestrictions, *DCsRestrictions, *shopsRestrictions;
	Matrix *maxSuppliers, *maxFactories, *maxDCs;
	Matrix *minSuppliers, *minFactories, *minDCs;
	Matrix* createMatrix(int height, int width, double min, double max);
	bool balanceSolution(Matrix* inputWeights, Matrix* outputRestrictions, Matrix* outputWeights, Matrix* min, Matrix* max, double* solution);
	double findMinRestriction();
	double findSumOfRestrictions(Matrix *restriction);
};

