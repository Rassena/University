#pragma once
#include <string>

#define NOOFLEVELS			4


class CProblem {

public:

	virtual double dGetQuality(double* pdSolution, int& error) = 0 ;
	virtual bool bWriteProblem(std::string sFileName) = 0;
	virtual double* pdGenerateSolution() = 0;
	virtual int iSolutionSize() = 0;


private:





};