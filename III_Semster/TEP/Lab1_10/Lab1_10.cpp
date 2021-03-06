#include "pch.h"
#include <iostream>
#include <time.h>
#include "Matrix.h"
#include "MscnProblem.h"
#include "DiffEvolution.h"

int main()
{
	MscnProblem *problem = new MscnProblem();
	bool error = false;
	problem->initialize(5, 5, 4, 5, 6, error);
	problem->initalizeSolutionGenerator();
	std::cout << "Przeszukiwanie losowe \n";
	std::cout << "Najlepsze zyski: " << problem->getBestQuality(100, error) << "\n";

	std::cout << "Ewolucja roznicowa \n";
	DiffEvolution *evolution = new DiffEvolution();
	evolution->initialize(problem);
	evolution->generatePopulation();
	evolution->evolve(250);	
}

