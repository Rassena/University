#pragma once
#include "COptimizer.h"
#include <Windows.h>
#include <iostream>

class CTimer {

public:

	CTimer(COptimizer* pcOpt);

	void vStartSimulation(double time_s);


private:
	
	COptimizer* pOptimizer;

};