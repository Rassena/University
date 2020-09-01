#include "pch.h"
#include <iostream>
#include <stdlib.h>
#include "DiffEvolution.h"

#define CROSS_PATH_AMOUNT 4
#define TRIES_AMOUNT 3
#define DIFF_CHANGE_INTERVAL 4
#define DIFF_MULTIPLIER 0.6

#define RAPORTING_INTERVAL 10

DiffEvolution::DiffEvolution() 
{
	initialized = false;
	populationSize = POPULATION_SIZE;
	crossProb = CROSS_PROB;
	diffWeight = DIFF_WEIGHT;
	bestSolution = NULL;
	bestQuality = -1;
};

DiffEvolution::DiffEvolution(int populationSize_, double crossProb_, double diffWeight_)
{
	initialized = false;
	if (populationSize_ < 5) populationSize_ = 5;
	populationSize = populationSize_;
	crossProb = crossProb_;
	diffWeight = diffWeight_;
	bestSolution = NULL;
	bestQuality = -1;
}

bool DiffEvolution::initialize(MscnProblem *problem_) 
{ 
	if (!problem_->initialized) return false;
	problem = problem_;
	population = new std::vector<double*>();
	initialized = true;
	return true;
}

bool DiffEvolution::generatePopulation()
{
	if (!initialized) return false;
	for (int i = 0; i < populationSize; i++)
	{
		population->push_back(problem->getRandomSolution());
	}
	if (population->size() != populationSize) return false;
	bestSolution = population->at(0);
	bool error = false;
	bestQuality = problem->getQuality(bestSolution, error);
	if (!error) return false;
	return true;
}

double* DiffEvolution::getBestSolution(bool &error)
{
	if (!initialized)
	{
		error = true;
		return NULL;
	}
	return bestSolution;
}

double DiffEvolution::getBestQuality(bool &error)
{
	if (!initialized)
	{
		error = true;
		return -1;
	}
	return problem->getQuality(bestSolution, error);
}

double DiffEvolution::averagePopulationQuality()
{
	double sum = 0;
	bool error = false;
	for (int i = 0; i < populationSize; i++)
	{
		sum += problem->getQuality(population->at(i), error);
		error = false;
	}
	return sum / populationSize;
}

bool DiffEvolution::evolve(int evolutionAmount)
{
	if (!initialized) return false;
	bool error = false;
	double average;
	int lastBestIteration = 0;
	std::cout << "Losowe rozwiazanie poczatkowe: " << problem->getQuality(population->at(Random::getIntegerRandom(0, populationSize - 1)), error) << "\n";
	for (int i = 0; i < evolutionAmount; i++)
	{
		if (lastBestIteration > DIFF_CHANGE_INTERVAL && diffWeight > 0.1)
		{
			lastBestIteration = i - lastBestIteration;
			diffWeight *= DIFF_MULTIPLIER;
			//std::cout << "Waga roznicowa: " << diffWeight << "\n";
		}
		if (i % RAPORTING_INTERVAL == 0)
		{
			std::cout << "Iteracja " << i << "\n";
			average = averagePopulationQuality();
			std::cout << "Srednia calej populacji: <" << average << "> \n";
		}
		double *currentSolution, *baseSolution, *addSolution1, *addSolution2;
		for (int j = 0; j < populationSize; j++)
		{
			if (Random::getDoubleRandom(0, 1) < crossProb)
			{
				currentSolution = population->at(j);
				int base = 0, add1 = 0, add2 = 0;
				while (base == j) base = Random::getIntegerRandom(0, populationSize - 1);
				while (add1 == base || add1 == j) add1 = Random::getIntegerRandom(0, populationSize - 1);
				while (add2 == base || add2 == add1 || add2 == j) add2 = Random::getIntegerRandom(0, populationSize - 1);

				baseSolution = population->at(base);
				addSolution1 = population->at(add1);
				addSolution2 = population->at(add2);

				double *newSolution = new double[problem->solutionSize];
				int protector = 0;
				while (protector < TRIES_AMOUNT && crossSolution(newSolution, baseSolution, addSolution1, addSolution2)) { protector++; }
				error = false;
				double newQuality = problem->getQuality(newSolution, error);
				error = false;
				double oldQuality = problem->getQuality(currentSolution, error);
				if (newQuality > oldQuality)
				{
					delete population->at(j);
					population->at(j) = newSolution;
					if (newQuality > bestQuality)
					{
						bestSolution = newSolution;
						error = false;
						bestQuality = problem->getQuality(bestSolution, error);
						lastBestIteration = i - lastBestIteration;
						std::cout << "Nowe najlepsze rozwiaznie: " << bestQuality << " \n";
					}
				}
				else
				{
					delete newSolution;
				}
				population->at(j);
			}
		}
	}
	std::cout << "Ostateczne najlepsze rozwiazanie: " << bestQuality << "\n";
	return true;
}

DiffEvolution::~DiffEvolution()
{
	delete problem;
	for (int i = 0; i < population->size(); i++)
	{
		delete population->at(i);
	}
	delete population;
}

bool DiffEvolution::crossSolution(double *newSolution, double *baseSolution, double *addSolution1, double *addSolution2)
{
	for (int i = 0; i < problem->solutionSize; i++)
	{
		newSolution[i] = baseSolution[i];
	}
	for (int i = 0; i < CROSS_PATH_AMOUNT; i++)
	{
		int *path = new int[4];
		double weight1, weight2;
		takePathWeight(addSolution1, addSolution2, path, weight1, weight2);
		double value = (weight1 - weight2) * diffWeight;

		newSolution[path[0] * problem->getSizeofFactories() + path[1]] += value;
		newSolution += problem->getSizeofSuppliers() * problem->getSizeofFactories();
		newSolution[path[1] * problem->getSizeofDCs() + path[2]] += value;
		newSolution += problem->getSizeofFactories() * problem->getSizeofDCs();
		newSolution[path[2] * problem->getSizeofShops() + path[3]] += value;
		newSolution -= problem->getSizeofFactories() * problem->getSizeofDCs() + problem->getSizeofSuppliers() * problem->getSizeofFactories();
		delete path;
	}
	bool error = false;
	return problem->getQuality(newSolution, error) == -1;
}

bool DiffEvolution::takePathWeight(double *solution1, double *solution2, int *path, double &weight1, double &weight2)
{
	int sup = Random::getIntegerRandom(0, problem->getSizeofSuppliers() - 1);
	int fac = 0;
	while (solution1[sup * problem->getSizeofFactories() + fac] == 0 && solution2[sup * problem->getSizeofFactories() + fac] == 0)
	{
		fac = Random::getIntegerRandom(0, problem->getSizeofFactories() - 1);
	}
	solution1 += problem->getSizeofSuppliers() * problem->getSizeofFactories();
	solution2 += problem->getSizeofSuppliers() * problem->getSizeofFactories();
	int DC = 0;
	while (solution1[fac * problem->getSizeofDCs() + DC] == 0 && solution2[fac * problem->getSizeofDCs() + DC] == 0)
	{
		DC = Random::getIntegerRandom(0, problem->getSizeofDCs() - 1);
	}
	solution1 += problem->getSizeofFactories() * problem->getSizeofDCs();
	solution2 += problem->getSizeofFactories() * problem->getSizeofDCs();
	int sh = 0;
	while (solution1[DC * problem->getSizeofShops() + sh] == 0 && solution2[DC * problem->getSizeofShops() + sh] == 0)
	{
		sh = Random::getIntegerRandom(0, problem->getSizeofShops() - 1);
	}
	solution1 -= problem->getSizeofFactories() * problem->getSizeofDCs() + problem->getSizeofSuppliers() * problem->getSizeofFactories();
	solution2 -= problem->getSizeofFactories() * problem->getSizeofDCs() + problem->getSizeofSuppliers() * problem->getSizeofFactories();
	path[0] = sup;
	path[1] = fac;
	path[2] = DC;
	path[3] = sh;

	double weight = solution1[sup * problem->getSizeofFactories() + fac];
	solution1 += problem->getSizeofSuppliers() * problem->getSizeofFactories();
	if (weight > solution1[fac * problem->getSizeofDCs() + DC]) weight = solution1[fac * problem->getSizeofDCs() + DC];
	solution1 += problem->getSizeofFactories() * problem->getSizeofDCs();
	if (weight > solution1[DC * problem->getSizeofShops() + sh]) weight = solution1[DC * problem->getSizeofShops() + sh];
	weight1 = weight;

	weight = solution2[sup * problem->getSizeofFactories() + fac];
	solution2 += problem->getSizeofSuppliers() * problem->getSizeofFactories();
	if (weight > solution2[fac * problem->getSizeofDCs() + DC]) weight = solution2[fac * problem->getSizeofDCs() + DC];
	solution2 += problem->getSizeofFactories() * problem->getSizeofDCs();
	if (weight > solution2[DC * problem->getSizeofShops() + sh]) weight = solution2[DC * problem->getSizeofShops() + sh];
	weight2 = weight;
	return true;
}