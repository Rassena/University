/*
#include "CMscnProblem.h"
#include <iostream>
#include "CDiffEvol.h"
#include "CRandomSearch.h"
#include "CTimer.h"

int main() 
{

	CMyOptimization cmo1 (new CDiffEvol(new CMscnProblem("diff")));

	CTimer timer(&cmo1);

	timer.vStartSimulation(20);

	timer.vStartSimulation(20);



	CMyOptimization cmo2 (new CRandomSearch(new CMscnProblem("diff")));

	CTimer timer2(&cmo2);

	timer2.vStartSimulation(50);



	


	CMscnProblem problem("diff");

	int error = 0;
	
	double*wsk = pdSolutionToDTable("diffsolution",error);

	double result = problem.dGetQuality(wsk, error);

	if (error == 0) std::cout << "Zysk dla takiego rozwiazania wynosi: " << result << std::endl;
	

	CDiffEvol evol (&problem);
	evol.vStart();
	double* r = evol.pdTheBest();
	std::cout << "Najlepszy zysk dla evol: " << problem.dGetQuality(r, error) << std::endl;
	



	return 0;

}






/*

	//////////////////////////////

	error = 0;

	CMscnProblem prob3 = CMscnProblem();

	prob3.bRead("drKproblem");

	wsk = pdSolutionToDTable("drKsolution", error);

	result = prob3.dGetQuality(wsk, error);

	if (error == 0) std::cout << "\nZysk dla takiego rozwiazania wynosi: " << result << '\n';

	CRandomSearch przesz(prob3);

	przesz.vStartSearching();



*/