

#ifndef TEP_LIST_10_RANDOM_H
#define TEP_LIST_10_RANDOM_H


#include <random>

class Random {
    std::knuth_b gen;
public:
	explicit Random();

    explicit Random(int seed);

    double operator()(double min, double max);

    double *randomArray(int n, double min, double max);

    int *randomArray(int n, int min, int max);

    double getDoubleRandom(double min, double max);

    int getIntegerRandom(int min, int max);
};


#endif //TEP_LIST_10_RANDOM_H
