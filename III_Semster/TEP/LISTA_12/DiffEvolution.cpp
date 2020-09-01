#include <stdlib.h>
#include "DiffEvolution.h"

#define CROSS_PATH_AMOUNT 4
#define TRIES_AMOUNT 3
#define DIFF_CHANGE_INTERVAL 4
#define DIFF_MULTIPLIER 0.6

#define RAPORTING_INTERVAL 10

DiffEvolution::DiffEvolution()
{
	populationSize = POPULATION_SIZE;
	crossProb = CROSS_PROB;
	diffWeight = DIFF_WEIGHT;
	bestSolution = NULL;
	bestQuality = -1;
};

DiffEvolution::DiffEvolution(int populationSize_, double crossProb_, double diffWeight_)
{
	if (populationSize_ < 5) populationSize_ = 5;
	populationSize = populationSize_;
	crossProb = crossProb_;
	diffWeight = diffWeight_;
	bestSolution = NULL;
	bestQuality = -1;
}

DiffEvolution::DiffEvolution( MSCNProblem *problem_)
{
	populationSize = POPULATION_SIZE;
	crossProb = CROSS_PROB;
	diffWeight = DIFF_WEIGHT;
	bestSolution = NULL;
	bestQuality = -1;
};

bool DiffEvolution::initialize(MSCNProblem *problem_ ,RandomSearch *search_)
{
	problem = problem_;
	search = search_;
	population = new std::vector<double*>();
	return true;
}

bool DiffEvolution::generatePopulation()
{
	for (int i = 0; i < populationSize; i++)
	{
		population->push_back(search->findSolution(10));
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
	return bestSolution;
}

double DiffEvolution::getBestQuality(bool &error)
{
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
	Random random;

	bool error = false;
	//	double average;
	int lastBestIteration = 0;
	//	std::cout << "Losowe rozwiazanie poczatkowe: " << problem->getQuality(population->at(random.getIntegerRandom(0, populationSize - 1)), error) << "\n";
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
			//			std::cout << "Iteracja " << i << "\n";
			//			average = averagePopulationQuality();
			//			std::cout << "Srednia calej populacji: <" << average << "> \n";
		}
		double *currentSolution, *baseSolution, *addSolution1, *addSolution2;
		for (int j = 0; j < populationSize; j++)
		{
			if (random.getDoubleRandom(0, 1) < crossProb)
			{
				currentSolution = population->at(j);
				int base = 0, add1 = 0, add2 = 0;
				while (base == j) base = random.getIntegerRandom(0, populationSize - 1);
				while (add1 == base || add1 == j) add1 = random.getIntegerRandom(0, populationSize - 1);
				while (add2 == base || add2 == add1 || add2 == j) add2 = random.getIntegerRandom(0, populationSize - 1);

				baseSolution = population->at(base);
				addSolution1 = population->at(add1);
				addSolution2 = population->at(add2);

				double *newSolution = new double[problem->getSolutionSize()];
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
	//	std::cout << "Ostateczne najlepsze rozwiazanie: " << bestQuality << "\n";
	std::cout << "Srednia calej populacji: <" << averagePopulationQuality() << "> \n";

	return true;
}

bool DiffEvolution::run(int repeats)
{
	Random random;

	bool error = false;
	//	double average;
	int lastBestIteration = 0;
	//	std::cout << "Losowe rozwiazanie poczatkowe: " << problem->getQuality(population->at(random.getIntegerRandom(0, populationSize - 1)), error) << "\n";
	for (int i = 0; i < repeats; i++)
	{
		if (lastBestIteration > DIFF_CHANGE_INTERVAL && diffWeight > 0.1)
		{
			lastBestIteration = i - lastBestIteration;
			diffWeight *= DIFF_MULTIPLIER;
			//std::cout << "Waga roznicowa: " << diffWeight << "\n";
		}
		if (i % RAPORTING_INTERVAL == 0)
		{
			//			std::cout << "Iteracja " << i << "\n";
			//			average = averagePopulationQuality();
			//			std::cout << "Srednia calej populacji: <" << average << "> \n";
		}
		double *currentSolution, *baseSolution, *addSolution1, *addSolution2;
		for (int j = 0; j < populationSize; j++)
		{
			if (random.getDoubleRandom(0, 1) < crossProb)
			{
				currentSolution = population->at(j);
				int base = 0, add1 = 0, add2 = 0;
				while (base == j) base = random.getIntegerRandom(0, populationSize - 1);
				while (add1 == base || add1 == j) add1 = random.getIntegerRandom(0, populationSize - 1);
				while (add2 == base || add2 == add1 || add2 == j) add2 = random.getIntegerRandom(0, populationSize - 1);

				baseSolution = population->at(base);
				addSolution1 = population->at(add1);
				addSolution2 = population->at(add2);

				double *newSolution = new double[problem->getSolutionSize()];
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
	//	std::cout << "Ostateczne najlepsze rozwiazanie: " << bestQuality << "\n";
	std::cout << "Srednia calej populacji: <" << averagePopulationQuality() << "> \n";

	return true;
}

DiffEvolution::~DiffEvolution()
{
	delete problem;
	for (int i = 0; i < (int)population->size(); i++)
	{
		delete population->at(i);
	}
	delete population;
}

bool DiffEvolution::crossSolution(double *newSolution, double *baseSolution, double *addSolution1, double *addSolution2)
{

	for (int i = 0; i < problem->getSolutionSize(); i++)
	{
		newSolution[i] = baseSolution[i];
	}
	for (int i = 0; i < CROSS_PATH_AMOUNT; i++)
	{
		int *path = new int[4];
		double weight1, weight2;
		takePathWeight(addSolution1, addSolution2, path, weight1, weight2);
		double value = (weight1 - weight2) * diffWeight;

		newSolution[path[0] * problem->getFactories() + path[1]] += value;
		newSolution += problem->getDeliverers() * problem->getFactories();
		newSolution[path[1] * problem->getDistributors() + path[2]] += value;
		newSolution += problem->getFactories() * problem->getDistributors();
		newSolution[path[2] * problem->getShops() + path[3]] += value;
		newSolution -= problem->getFactories() * problem->getDistributors() + problem->getDeliverers() * problem->getFactories();
		delete path;
	}
	bool error = false;
	return problem->getQuality(newSolution, error) == -1;
}

bool DiffEvolution::takePathWeight(double *solution1, double *solution2, int *path, double &weight1, double &weight2)
{

	Random random;

	int sup = random.getIntegerRandom(0, problem->getDeliverers() - 1);
	int fac = 0;
	while (solution1[sup * problem->getFactories() + fac] == 0 && solution2[sup * problem->getFactories() + fac] == 0)
	{
		fac = random.getIntegerRandom(0, problem->getFactories() - 1);
	}
	solution1 += problem->getDeliverers() * problem->getFactories();
	solution2 += problem->getDeliverers() * problem->getFactories();
	int DC = 0;
	while (solution1[fac * problem->getDistributors() + DC] == 0 && solution2[fac * problem->getDistributors() + DC] == 0)
	{
		DC = random.getIntegerRandom(0, problem->getDistributors() - 1);
	}
	solution1 += problem->getFactories() * problem->getDistributors();
	solution2 += problem->getFactories() * problem->getDistributors();
	int sh = 0;
	while (solution1[DC * problem->getShops() + sh] == 0 && solution2[DC * problem->getShops() + sh] == 0)
	{
		sh = random.getIntegerRandom(0, problem->getShops() - 1);
	}
	solution1 -= problem->getFactories() * problem->getDistributors() + problem->getDeliverers() * problem->getFactories();
	solution2 -= problem->getFactories() * problem->getDistributors() + problem->getDeliverers() * problem->getFactories();
	path[0] = sup;
	path[1] = fac;
	path[2] = DC;
	path[3] = sh;

	double weight = solution1[sup * problem->getFactories() + fac];
	solution1 += problem->getDeliverers() * problem->getFactories();
	if (weight > solution1[fac * problem->getDistributors() + DC]) weight = solution1[fac * problem->getDistributors() + DC];
	solution1 += problem->getFactories() * problem->getDistributors();
	if (weight > solution1[DC * problem->getShops() + sh]) weight = solution1[DC * problem->getShops() + sh];
	weight1 = weight;

	weight = solution2[sup * problem->getFactories() + fac];
	solution2 += problem->getDeliverers() * problem->getFactories();
	if (weight > solution2[fac * problem->getDistributors() + DC]) weight = solution2[fac * problem->getDistributors() + DC];
	solution2 += problem->getFactories() * problem->getDistributors();
	if (weight > solution2[DC * problem->getShops() + sh]) weight = solution2[DC * problem->getShops() + sh];
	weight2 = weight;
	return true;
}