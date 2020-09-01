
#ifndef TEP_LIST_9_MSCN_MSCNPROBLEM_H
#define TEP_LIST_9_MSCN_MSCNPROBLEM_H

#include "BoundedMatrix.h"

#define EOS -1
#define invalidLength 1
#define solutionNull 2
#define failedConstraints 3
#define failedCapacity 4
#define OK 0

class MSCNProblem {
private:
	static inline double epsylon(double value);

	Matrix<double> *cd;
	Matrix<double> *cf;
	Matrix<double> *cm;
	Matrix<double> *sd;
	Matrix<double> *sf;
	Matrix<double> *sm;
	Matrix<double> *ss;
	Matrix<double> *ud;
	Matrix<double> *uf;
	Matrix<double> *um;
	Matrix<double> *ps;
	Matrix<double> *xdLowerBound;
	Matrix<double> *xdUpperBound;
	Matrix<double> *xfLowerBound;
	Matrix<double> *xfUpperBound;
	Matrix<double> *xmLowerBound;
	Matrix<double> *xmUpperBound;
	int d;
	int f;
	int m;
	int s;
public:
	MSCNProblem();

	~MSCNProblem();

	bool setDeliverers(int newDeliverers);

	bool setFactories(int newFactories);

	bool setDistributors(int newDistributors);

	bool setShops(int newShops);

	bool setDeliveryToFactoryCost(int deliverer, int factory, double val);

	bool setFactoryToDistributorCost(int factory, int storage, double val);

	bool setDistributorToStoreCost(int storage, int store, double val);

	double getQuality(double *solution, bool &inputValid);

	int checkValidity(double *solution, bool &inputValid);

	bool setUd(int row, int column, double val);

	bool setUf(int row, int column, double val);

	bool setUm(int row, int column, double val);

	bool setSd(int row, int column, double val);

	bool setSf(int row, int column, double val);

	bool setSm(int row, int column, double val);

	bool setSs(int row, int column, double val);

	bool setPs(int row, int column, double val);

	std::string toString();

	bool loadFromString(const std::string &input);

	bool saveToFile(const char *filename);

	bool loadFromFile(const char *filename);

	double getMin(int pos);

	double getMax(int pos);
};


#endif //TEP_LIST_9_MSCN_MSCNPROBLEM_H
