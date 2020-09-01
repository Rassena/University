#pragma once
#include "MSCNProblem.h"
#include "Random.h"
#include "RandomSearch.h"
#include "CComputingProblem.h"

#define POPULATION_SIZE 100
#define CROSS_PROB 0.8
#define DIFF_WEIGHT 2.4

class DiffEvolution : public CComputingProblem
{
public:
	DiffEvolution();
	DiffEvolution(int populationSize_, double crossProb_, double diffWeight_);
	DiffEvolution(MSCNProblem * problem_);
	bool initialize(MSCNProblem *problem_, RandomSearch *search_);
	bool generatePopulation();
	double* getBestSolution(bool &error);
	double getBestQuality(bool &error);
	double averagePopulationQuality();
//	bool start(bool &error);

	bool evolve(int evolutionAmount);
	bool run(int evolutionAmount);

	~DiffEvolution();
private:
	MSCNProblem *problem;
	RandomSearch *search;
	bool initialized;

	int populationSize;
	double crossProb, diffWeight;
	std::vector<double*> *population;
	double* bestSolution;
	double bestQuality;
	bool crossSolution(double *newSolution, double *baseSolution, double *addSolution1, double *addSolution2);
	bool takePathWeight(double *solution1, double *solution2, int *path, double &weight1, double &weight2);
};

