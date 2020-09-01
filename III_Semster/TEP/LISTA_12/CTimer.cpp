#include "CTimer.h"

#define ITERATIONS 50


CTimer::CTimer(COptimizer * pcOpt) {
	pOptimizer=pcOpt;
}



void CTimer::vStartSimulation(double time_s){
	bool success = true;
	if (time_s <= 0) {
		std::cout << "Wrong time: " << time_s << std::endl;
		return;
	}

	double d_secs = 0 ;

	LARGE_INTEGER li_start, li_end, li_freq;

	QueryPerformanceFrequency(&li_freq);
	QueryPerformanceCounter(&li_start);

	while (d_secs<time_s) {
		pOptimizer->run(ITERATIONS);

		QueryPerformanceCounter(&li_end);
		d_secs = (double) (li_end.QuadPart - li_start.QuadPart);
		d_secs = d_secs / li_freq.QuadPart;
	}

	std::cout << "\n Best quality: " << pOptimizer->getBestQuality(success) << std::endl;
	std::cout << " Passed: " << d_secs << " sec" << std::endl;

	return;
}
