#include <iostream>
#include "Random.h"

Random::Random() {
    std::random_device device;
    gen = std::knuth_b(device());
}

Random::Random(int seed) {
	gen = std::knuth_b(seed);
}

double Random::operator()(double min, double max) {
    std::uniform_real_distribution<> dist = std::uniform_real_distribution<>(min, max);
    return dist(gen);
}

int *Random::randomArray(int n, int min, int max) {
    std::uniform_int_distribution<> dist = std::uniform_int_distribution<>(min, max);
    int *res = new int[n];
    for (int i = 0; i < n; i++) {
        res[i] = dist(gen);
    }
    return res;
}

double *Random::randomArray(int n, double min, double max) {
    std::uniform_real_distribution<> dist = std::uniform_real_distribution<>(min, max);
    double *res = new double[n];
    for (int i = 0; i < n; i++) {
        res[i] = dist(gen);
    }
    return res;
}

double Random::random(double min, double max) {
    if (max < min) return 0;
    std::uniform_real_distribution<> dst = std::uniform_real_distribution<>(min, max);
    return dst(gen);
}

int Random::random(int min, int max) {
    if (max < min) return 0;
    std::uniform_int_distribution<> dst = std::uniform_int_distribution<>(min, max);
    return dst(gen);
}
