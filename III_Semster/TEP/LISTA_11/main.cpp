#include <iostream>
#include <time.h>
#include "Matrix.h"
#include "MscnProblem.h"
#include "DiffEvolution.h"
#include "RandomSearch.h"


int main()
{
	Random randomseed;

	RandomSearch *search = new RandomSearch();

	std::cout << "Przeszukiwanie losowe \n";

	search->setDeliverers(6);
	search->setFactories(6);
	search->setDistributors(6);
	search->setShops(6);
	int seed = randomseed.getIntegerRandom(0, 1000000);
	search->seedProblem(seed);
	double *solution = search->findSolution(seed);
	MSCNProblem *problem = new MSCNProblem();
	bool success = true;
	problem->loadFromString(search->getProblem());
	std::cout << problem->getQuality(solution, success) << "\n";

	std::cout << "Ewolucja roznicowa \n";
	DiffEvolution *evolution = new DiffEvolution();
	evolution->initialize(problem,search);
	evolution->generatePopulation();
	evolution->evolve(250);
	system("pause");
}

