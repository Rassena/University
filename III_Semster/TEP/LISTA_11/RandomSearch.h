

#ifndef TEP_LIST_10_RANDOMSEARCH_H
#define TEP_LIST_10_RANDOMSEARCH_H


#include "MSCNProblem.h"
#include "Random.h"
#include <string>

#define solutionsToFind 2
#define greed 0.1
#define EOS -1

class RandomSearch {
    MSCNProblem *problem;

    double *generateSolution(int len);

public:
    bool setDeliverers(int n);

    bool setFactories(int n);

    bool setDistributors(int n);

    bool setShops(int n);

	double * findSolution(int seed);

    RandomSearch();

    ~RandomSearch();

	void dumpData();

    void seedProblem(int seed);

	std::string getProblem();
};


#endif //TEP_LIST_10_RANDOMSEARCH_H
