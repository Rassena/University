#include <iostream>
#include <time.h>
#include "Matrix.h"
#include "MscnProblem.h"
#include "DiffEvolution.h"
#include "RandomSearch.h"
#include "COptimizer.h"
#include "CTimer.h"



int main()
{
	Random randomseed;

	RandomSearch *search = new RandomSearch();


	search->setDeliverers(6);
	search->setFactories(6);
	search->setDistributors(6);
	search->setShops(6);
	int seed = randomseed.getIntegerRandom(0, 1000000);
	search->seedProblem(seed);
	bool success = true;
	
	double *solution = search->findSolution(seed);
	MSCNProblem *problem = new MSCNProblem();
	problem->loadFromString(search->getProblem());
		
	
//	std::cout << problem->getQuality(solution, success) << "\n";
	std::cout << "Ewolucja roznicowa \n";
	DiffEvolution *evolution = new DiffEvolution();
	evolution->initialize(problem,search);
	evolution->generatePopulation();
//	evolution->evolve(250);


	CMyOptimization cmo1(evolution);
	CTimer timer(&cmo1);
	timer.vStartSimulation(20);

	std::cout << "Przeszukiwanie losowe \n";

	CMyOptimization cmo2(search);
	CTimer timer2(&cmo2);
	timer2.vStartSimulation(20);

	std::cout << "\n\n\n";
	
	MSCNProblem *problem2 = new MSCNProblem();
	double *solution2 = new double;
	double *solution3 = nullptr;
	double *solution4 = search->generateSolution(6*6+6*6+6*6);


	success = true;
	problem2->loadFromString(search->getProblem());

	std::cout << "solution1: " << std::endl;
	problem2->checkValidityWithCorection(solution, success);
	std::cout << "solution2: " << std::endl;
	problem2->checkValidityWithCorection(solution2, success);
	std::cout << "solution3: " << std::endl;
	problem2->checkValidityWithCorection(solution3, success);
	std::cout << "solution4: " << std::endl;
	problem2->checkValidityWithCorection(solution4, success);

	delete problem2;
	delete solution2;
	delete solution3;
	delete solution4;

	system("pause");
}

