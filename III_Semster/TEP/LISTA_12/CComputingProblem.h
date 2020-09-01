#pragma once
#include"CProblem.h"

class CComputingProblem {

public:

//	virtual bool start(bool &error) = 0;
	virtual double* getBestSolution(bool &error) = 0;
	virtual bool run(int repeats) = 0;
	virtual double  getBestQuality(bool &error) = 0;

	inline int iGetSize() { return iSolutionSize; }

protected:

	CProblem* pProblem;
	bool bWork = true;
	int iSolutionSize;


private:

	


};




