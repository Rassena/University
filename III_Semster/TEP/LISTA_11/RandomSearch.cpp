
#include "RandomSearch.h"
#include <string>

RandomSearch::RandomSearch() {
    problem = new MSCNProblem();
}


RandomSearch::~RandomSearch() {
    delete problem;
}

bool RandomSearch::setDeliverers(int n) {
    return problem->setDeliverers(n);
}

bool RandomSearch::setFactories(int n) {
    return problem->setFactories(n);
}

bool RandomSearch::setDistributors(int n) {
    return problem->setDistributors(n);
}

bool RandomSearch::setShops(int n) {
    return problem->setShops(n);
}

double *RandomSearch::generateSolution(int seed) {

    Matrix<double> xd(problem->getDeliverers(), problem->getFactories());
    Matrix<double> xf(problem->getFactories(), problem->getDistributors());
    Matrix<double> xm(problem->getDistributors(), problem->getShops());

    double *solutionxD = new double[xd.nElements()];
    double *solutionxF = new double[xf.nElements()];
    double *solutionxM = new double[xm.nElements()];

	int len = xd.nElements() + xf.nElements() + xm.nElements();
	double *solution = new double[len + 1];

    Random random;
    int i = 0;
    int tempI = 0;
    for (int row = 0; row < xd.getSizeY(); row++) {
        double capacity = problem->getSD(row);
        for (int col = 0; col < xd.getSizeX(); col++) {
            double min = problem->getMin(i);
            double max = std::min(capacity * greed, problem->getMax(i));
            double val = random.getDoubleRandom(min, max);
            solutionxD[tempI] = val;
            capacity -= val;
            i++;
            tempI++;
        }
    }

    xd.fromArray(solutionxD);
    delete[] solutionxD;

    tempI = 0;
    for (int row = 0; row < xf.getSizeY(); row++) {
        double pool = xd | row;
        double capacity = problem->getSF(row);
        for (int col = 0; col < xf.getSizeX(); col++) {
            double min = problem->getMin(i);
            double maxThrough = std::min(problem->getMax(i), pool * greed);
            double maxCapacity = capacity * greed;
            double maxTotal = std::min(maxThrough, maxCapacity);
            double val = random.getDoubleRandom(min, maxTotal);
            pool -= val;
            capacity -= val;
            solutionxF[tempI] = val;
            tempI++;
            i++;
        }
    }

    xf.fromArray(solutionxF);
    delete[] solutionxF;

    tempI = 0;
    for (int row = 0; row < xm.getSizeY(); row++) {
        double pool = xf | row;
        double capacity = problem->getSM(row);
        for (int col = 0; col < xm.getSizeX(); col++) {
            double min = problem->getMin(i);
            double maxThrough = std::min(problem->getMax(i), pool * greed);
            double maxCapacity = capacity * greed;
            double maxTotal = std::min(maxThrough, maxCapacity);
            double val = random.getDoubleRandom(min, maxTotal);
            pool -= val;
            capacity -= val;
            solutionxM[tempI] = val;
            tempI++;
            i++;
        }
    }

    xm.fromArray(solutionxM);
    delete[] solutionxM;

    i = 0;
    for (int row = 0; row < xd.getSizeY(); row++) {
        for (int column = 0; column < xd.getSizeX(); column++) {
            solution[i] = xd.getElement(row, column);
            i++;
        }
    }

    for (int row = 0; row < xf.getSizeY(); row++) {
        for (int column = 0; column < xf.getSizeX(); column++) {
            solution[i] = xf.getElement(row, column);
            i++;
        }
    }

    for (int row = 0; row < xm.getSizeY(); row++) {
        for (int column = 0; column < xm.getSizeX(); column++) {
            solution[i] = xm.getElement(row, column);
            i++;
        }
    }

    solution[len] = EOS;
    return solution;
}

///REMEMBER TO FREE MEMORY!
double *RandomSearch::findSolution(int seed) {
    int solutions = 0;
    double *bestSolution = nullptr;
    double *currentSolution = nullptr;
    double bestQuality = 0;
    double currentQuality = 0;
    bool validInput = false;
    int validSolution = 1;
    while (!validInput || validSolution!=OK) {
        bestSolution = generateSolution(seed);
        validSolution = problem->checkValidity(bestSolution, validInput);
     //   if (!validInput || validSolution!=OK) delete[] bestSolution;
    }
    //std::cout << "first solution found" << std::endl;
    bestQuality = problem->getQuality(bestSolution, validInput);

    while (solutions < solutionsToFind) {
        currentSolution = generateSolution(seed);
        validSolution = problem->checkValidity(currentSolution, validInput);
        if (validInput && validSolution==OK) {
            currentQuality = problem->getQuality(currentSolution, validInput);
            if (currentQuality > bestQuality) {
                bestQuality = currentQuality;
                delete[] bestSolution;
                bestSolution = currentSolution;
                currentSolution = nullptr;
            }
            ++solutions;
        }
        delete[] currentSolution;
    }
    //std::cout << bestQuality << std::endl;
    return bestSolution;
}

void RandomSearch::dumpData() {
    std::cout << problem->toString() << std::endl;
}

std::string RandomSearch::getProblem() {
	return problem->toString();
}

void RandomSearch::seedProblem(int seed) {
    problem->generateInstance(seed);
}
