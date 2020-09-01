#pragma once
#include "CComputingProblem.h"
#include <iostream>


class COptimizer {

public:

//	virtual bool start(bool &error) =0 ;
	virtual double* getBestSolution(bool &error) = 0;
	virtual bool run(int repeats) = 0;
	virtual double  getBestQuality(bool &error) = 0;

protected:

	CComputingProblem* pProblem;

private:

};



class CMyOptimization : public COptimizer{

public: 


	CMyOptimization(CComputingProblem* ccp) { pProblem = ccp; }


//	bool start(bool &error) { return pProblem->start(error); }

	bool run(int repeats) {return pProblem->run(repeats);}

	double* getBestSolution(bool &error) { return getBestSolution(error); }
	double  getBestQuality(bool &error) { return  pProblem->getBestQuality(error); }


};

