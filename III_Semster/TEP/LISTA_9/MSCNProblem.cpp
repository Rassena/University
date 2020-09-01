
#include <sstream>
#include "MSCNProblem.h"
#include "MiscTools.h"
#include "FileIO.h"

MSCNProblem::MSCNProblem() {
	cd = new Matrix<double>(0, 0);
	cf = new Matrix<double>(0, 0);
	cm = new Matrix<double>(0, 0);
	sd = new Matrix<double>(1, 0);
	sf = new Matrix<double>(1, 0);
	sm = new Matrix<double>(1, 0);
	ss = new Matrix<double>(1, 0);
	ud = new Matrix<double>(1, 0);
	uf = new Matrix<double>(1, 0);
	um = new Matrix<double>(1, 0);
	ps = new Matrix<double>(1, 0);
	xdLowerBound = new Matrix<double>(0, 0);
	xdUpperBound = new Matrix<double>(0, 0);
	xfLowerBound = new Matrix<double>(0, 0);
	xfUpperBound = new Matrix<double>(0, 0);
	xmLowerBound = new Matrix<double>(0, 0);
	xmUpperBound = new Matrix<double>(0, 0);
	d = 0;
	f = 0;
	m = 0;
	s = 0;
}

MSCNProblem::~MSCNProblem() {
	delete cd;
	delete cf;
	delete cm;
	delete sd;
	delete sf;
	delete sm;
	delete ss;
	delete ud;
	delete uf;
	delete um;
	delete ps;
	delete xdLowerBound;
	delete xdUpperBound;
	delete xfLowerBound;
	delete xfUpperBound;
	delete xmLowerBound;
	delete xmUpperBound;
}

bool MSCNProblem::setDeliverers(int newDeliverers) {
	int cmSizeX = cd->getSizeX();
	bool success = cd->setSize(newDeliverers, cmSizeX);
	if (!success) return false;
	success &= sd->setSize(1, newDeliverers);
	success &= ud->setSize(1, newDeliverers);
	success &= sd->setSize(1, newDeliverers);

	d = newDeliverers;

	int xdLowerBoundSizeX = xdLowerBound->getSizeX();
	success &= xdLowerBound->setSize(newDeliverers, xdLowerBoundSizeX);
	success &= xdUpperBound->setSize(newDeliverers, xdLowerBoundSizeX);

	return success;
}

bool MSCNProblem::setFactories(int newFactories) {
	int cdSizeY = cd->getSizeY();
	bool success = cd->setSize(cdSizeY, newFactories);

	int cfSizeX = cf->getSizeX();
	success &= cf->setSize(newFactories, cfSizeX);
	success &= sf->setSize(1, newFactories);
	success &= uf->setSize(1, newFactories);
	success &= sf->setSize(1, newFactories);

	int xfLowerBoundSizeX = xfLowerBound->getSizeX();
	success &= xfLowerBound->setSize(newFactories, xfLowerBoundSizeX);
	success &= xfUpperBound->setSize(newFactories, xfLowerBoundSizeX);

	int xdLowerBoundSizeY = xdLowerBound->getSizeY();
	success &= xdLowerBound->setSize(xdLowerBoundSizeY, newFactories);
	success &= xdUpperBound->setSize(xdLowerBoundSizeY, newFactories);


	f = newFactories;

	return success;
}

bool MSCNProblem::setDistributors(int newDistributors) {
	int cfSizeY = cf->getSizeY();
	bool success = cf->setSize(cfSizeY, newDistributors);

	int cmSizeX = cm->getSizeX();
	success &= cm->setSize(newDistributors, cmSizeX);

	success &= sm->setSize(1, newDistributors);
	success &= um->setSize(1, newDistributors);

	int xmLowerBoundSizeX = xmLowerBound->getSizeX();
	success &= xmLowerBound->setSize(newDistributors, xmLowerBoundSizeX);
	success &= xmUpperBound->setSize(newDistributors, xmLowerBoundSizeX);

	int xfLowerBoundSizeY = xfLowerBound->getSizeY();
	success &= xfLowerBound->setSize(xfLowerBoundSizeY, newDistributors);
	success &= xfUpperBound->setSize(xfLowerBoundSizeY, newDistributors);

	m = newDistributors;

	return success;
}

bool MSCNProblem::setShops(int newShops) {
	int cmSizeY = cm->getSizeY();
	bool success = cm->setSize(cmSizeY, newShops);

	s = newShops;
	success &= ss->setSize(1, newShops);

	int xmLowerBoundSizeY = xmLowerBound->getSizeY();
	success &= xmLowerBound->setSize(xmLowerBoundSizeY, newShops);
	success &= xmUpperBound->setSize(xmLowerBoundSizeY, newShops);


	ps->setSize(1, newShops);

	return success;
}

bool MSCNProblem::setDeliveryToFactoryCost(int deliverer, int factory, double val) {
	if (val < 0) return false;
	bool result = cd->setElement(deliverer, factory, val);
	return result;
}

bool MSCNProblem::setFactoryToDistributorCost(int factory, int storage, double val) {
	if (val < 0) return false;
	bool result = cf->setElement(factory, storage, val);
	return result;
}

bool MSCNProblem::setDistributorToStoreCost(int storage, int store, double val) {
	if (val < 0) return false;
	bool result = cm->setElement(storage, store, val);
	return result;
}

double MSCNProblem::getQuality(double *solution, bool &inputValid) {

//	if (checkValidity(solution, inputValid) != 0) {
//		inputValid = false;
//		return 0;
//	}

	int requiredSize = d * f + f * m + m * s;

	if (solution == nullptr) {
		inputValid = false;
		return 0;
	}

	int len = 0;
	while (solution[len] >= 0)len++;

	if (len != requiredSize) {
		inputValid = false;
		return 0;
	}


	BoundedMatrix<double> xd = BoundedMatrix<double>(d, f, xdLowerBound, xdUpperBound);
	BoundedMatrix<double> xf = BoundedMatrix<double>(f, m, xfLowerBound, xfUpperBound);
	BoundedMatrix<double> xm = BoundedMatrix<double>(m, s, xmLowerBound, xmUpperBound);


	bool result = xd.fromArray(solution, 0);
	result &= xf.fromArray(solution, f * d);
	result &= xm.fromArray(solution, f * d + m * s);

	if (!result) {
		inputValid = false;
		return 0;
	}

	Matrix<double> resCD = (*cd) * xd;
	Matrix<double> resCF = (*cf) * xf;
	Matrix<double> resCM = (*cm) * xm;
	double cost = resCD.sum() + resCF.sum() + resCM.sum();


	for (int y = 0; y < d; y++) {
		cost += epsylon(xd - y) * (ud->getElement(0, y));
	}
	for (int y = 0; y < f; y++) {
		cost += epsylon(xf - y) * (uf->getElement(0, y));
	}
	for (int y = 0; y < m; y++) {
		cost += epsylon(xm - y) * (um->getElement(0, y));
	}

	Matrix<double> resTmp(1, s);
	for (int x = 0; x < s; x++) {
		double columnVal = xm | x;
		resTmp.setElement(0, x, columnVal);
	}
	double profits = (resTmp * (*ps)).sum();

	inputValid = true;
	return profits - cost;
}

int MSCNProblem::checkValidity(double *solution, bool &inputValid) {

	int requiredSize = d * f + f * m + m * s;

	if (solution == nullptr) {
		inputValid = false;
		return solutionNull;
	}

	int len = 0;
	while (solution[len] >= 0)len++;

	if (len != requiredSize) {
		inputValid = false;
		return invalidLength;
	}

	for (int i = 0; i < requiredSize; i++) {
		if (solution[i] < 0 || solution[i] > getMax(i) || solution[i] < getMin(i)) {
			if (solution[i] != 0) {
				inputValid = false;
				return failedConstraints;
			}
		}
	}

	Matrix<double> xd = Matrix<double>(0, 0);
	Matrix<double> xf = Matrix<double>(0, 0);
	Matrix<double> xm = Matrix<double>(0, 0);

	xd.setSize(d, f);
	xd.fromArray(solution, 0);

	xf.setSize(f, m);
	xf.fromArray(solution, f * d);

	xm.setSize(m, s);
	xm.fromArray(solution, f * d + m * s);

	for (int y = 0; y < xf.getSizeY(); y++) {   
		if ((xf - y) > (xd | y)) {
			return failedConstraints;
		}
	}

	for (int y = 0; y < xm.getSizeY(); y++) {
		if ((xm - y) > (xf | y)) {
			return failedConstraints;
		}
	}

	for (int y = 0; y < d; y++) {
		if ((xd - y) > sd->getElement(0, y)) return failedCapacity;
	}

	for (int y = 0; y < f; y++) {
		if ((xf - y) > sf->getElement(0, y)) return failedCapacity;
	}

	for (int y = 0; y < m; y++) {
		if ((xm - y) > sm->getElement(0, y)) return failedCapacity;
	}

	for (int y = 0; y < s; y++) {
		if ((xm | y) > ss->getElement(0, y)) return failedCapacity;
	}

	inputValid = true;
	return OK;
}

inline double MSCNProblem::epsylon(double value) {
	return (value > 0 ? 1 : 0);
}

bool MSCNProblem::setUd(int row, int column, double val) {
	return ud->setElement(row, column, val);
}

bool MSCNProblem::setUf(int row, int column, double val) {
	return ud->setElement(row, column, val);
}

bool MSCNProblem::setUm(int row, int column, double val) {
	return um->setElement(row, column, val);
}

bool MSCNProblem::setSd(int row, int column, double val) {
	return sd->setElement(row, column, val);
}

bool MSCNProblem::setSf(int row, int column, double val) {
	return sf->setElement(row, column, val);
}

bool MSCNProblem::setSm(int row, int column, double val) {
	return sm->setElement(row, column, val);
}

bool MSCNProblem::setSs(int row, int column, double val) {
	return ss->setElement(row, column, val);
}

bool MSCNProblem::setPs(int row, int column, double val) {
	return ps->setElement(row, column, val);
}

std::string MSCNProblem::toString() {

	std::string output;
	output += "D " + std::to_string(d) + '\n';
	output += "F " + std::to_string(f) + '\n';
	output += "M " + std::to_string(m) + '\n';
	output += "S " + std::to_string(s) + '\n';

	output += "sd\n";
	output += sd->toString();
	output += "sf\n";
	output += sf->toString();
	output += "sm\n";
	output += sm->toString();
	output += "ss\n";
	output += ss->toString();
	output += "cd\n";
	output += cd->toString();
	output += "cf\n";
	output += cf->toString();
	output += "cm\n";
	output += cm->toString();
	output += "ud\n";
	output += ud->toString();
	output += "uf\n";
	output += uf->toString();
	output += "um\n";
	output += um->toString();
	output += "p\n";
	output += ps->toString();
	output += "xdminmax\n";
	std::string xdMin = xdLowerBound->toString();
	std::string xdMax = xdUpperBound->toString();
	std::string xdminmax = zipString(xdMin, xdMax);
	output += xdminmax;

	output += "xfminmax\n";
	std::string xfminmax = zipString(xfLowerBound->toString(), xfUpperBound->toString());
	output += xfminmax;

	output += "xmminmax\n";
	std::string xmminmax = zipString(xmLowerBound->toString(), xmUpperBound->toString());
	output += xmminmax;
	return std::move(output);
}

bool MSCNProblem::loadFromString(const std::string &input) {

	std::stringstream stream(input);
	std::string tmp;

	std::getline(stream, tmp);
	tmp = tmp.substr(1, tmp.length() - 1);
	int td = std::stoi(tmp);

	std::getline(stream, tmp);
	tmp = tmp.substr(1, tmp.length() - 1);
	int tf = std::stoi(tmp);

	std::getline(stream, tmp);
	tmp = tmp.substr(1, tmp.length() - 1);
	int tm = std::stoi(tmp);

	std::getline(stream, tmp);
	tmp = tmp.substr(1, tmp.length() - 1);
	int ts = std::stoi(tmp);

	bool result = setDistributors(tm);
	result &= setFactories(tf);
	result &= setDeliverers(td);
	result &= setShops(ts);

	if (!result) {
		return false;
	}

	std::getline(stream, tmp);
	double *sdTmp = readVal(stream, d);
	sd->fromArray(sdTmp, 0);
	delete[] sdTmp;

	std::getline(stream, tmp);
	double *sfTmp = readVal(stream, f);
	sf->fromArray(sfTmp, 0);
	delete[] sfTmp;

	std::getline(stream, tmp);
	double *smTmp = readVal(stream, m);
	sm->fromArray(smTmp, 0);
	delete[] smTmp;

	std::getline(stream, tmp);
	double *ssTmp = readVal(stream, s);
	ss->fromArray(ssTmp, 0);
	delete[] ssTmp;


	std::getline(stream, tmp);
	double *cdTmp = readVal(stream, d * f);
	cd->fromArray(cdTmp, 0);
	delete[] cdTmp;

	std::getline(stream, tmp);
	double *cfTmp = readVal(stream, f * m);
	cf->fromArray(cfTmp, 0);
	delete[] cfTmp;

	std::getline(stream, tmp);
	double *cmTmp = readVal(stream, s * m);
	cm->fromArray(cmTmp, 0);
	delete[] cmTmp;


	std::getline(stream, tmp);
	double *udTmp = readVal(stream, d);
	ud->fromArray(udTmp, 0);
	delete[] udTmp;

	std::getline(stream, tmp);
	double *ufTmp = readVal(stream, f);
	uf->fromArray(ufTmp, 0);
	delete[] ufTmp;

	std::getline(stream, tmp);
	double *umTmp = readVal(stream, m);
	um->fromArray(umTmp, 0);
	delete[] umTmp;

	std::getline(stream, tmp);
	double *psTmp = readVal(stream, s);
	ps->fromArray(psTmp, 0);
	delete[] psTmp;

	std::getline(stream, tmp);
	double *xdminTmp = new double[d * f];
	double *xdmaxTmp = new double[d * f];
	for (int i = 0; i < d * f; i++) {
		std::getline(stream, tmp);
		xdminTmp[i] = std::stod(tmp);
		std::getline(stream, tmp);
		xdmaxTmp[i] = std::stod(tmp);
	}
	xdLowerBound->fromArray(xdminTmp, 0);
	delete[] xdminTmp;
	xdUpperBound->fromArray(xdmaxTmp, 0);
	delete[] xdmaxTmp;


	std::getline(stream, tmp);
	double *xfminTmp = new double[f * m];
	double *xfmaxTmp = new double[f * m];
	for (int i = 0; i < f * m; i++) {
		std::getline(stream, tmp);
		xfminTmp[i] = std::stod(tmp);
		std::getline(stream, tmp);
		xfmaxTmp[i] = std::stod(tmp);
	}
	xfLowerBound->fromArray(xfminTmp, 0);
	delete[] xfminTmp;
	xfUpperBound->fromArray(xfmaxTmp, 0);
	delete[] xfmaxTmp;

	std::getline(stream, tmp);
	double *xmminTmp = new double[s * m];
	double *xmmaxTmp = new double[s * m];
	for (int i = 0; i < s * m; i++) {
		std::getline(stream, tmp);
		xmminTmp[i] = std::stod(tmp);
		std::getline(stream, tmp);
		xmmaxTmp[i] = std::stod(tmp);
	}
	xmLowerBound->fromArray(xmminTmp, 0);
	delete[] xmminTmp;
	xmUpperBound->fromArray(xmmaxTmp, 0);
	delete[] xmmaxTmp;

	return true;
}

bool MSCNProblem::saveToFile(const char *filename) {
	FileIO fileIO;
	bool result = fileIO.openFile(filename, "w");
	if (!result) return false;

	std::string thisString = this->toString();
	result = fileIO.writeLines(thisString);
	if (!result) return false;

	result = fileIO.closeFile();
	return result;
}

bool MSCNProblem::loadFromFile(const char *filename) {
	FileIO fileIO;

	bool result = fileIO.openFile(filename, "rb");
	if (!result) return false;

	std::string thisStr;

	result = fileIO.getAllLines(thisStr);
	if (!result) return false;

	this->loadFromString(thisStr);
	result = fileIO.closeFile();

	return result;
}

double MSCNProblem::getMin(int pos) {
	if (pos < d * f) return xdLowerBound->getAbsoluteElement(pos);
	if (pos >= d * f && pos < d * f + f * m) return xfLowerBound->getAbsoluteElement(pos - d * f);
	if (pos >= d * f + f * m && pos < d * f + f * m + m * s)
		return xmLowerBound->getAbsoluteElement(pos - d * f - f * m);
	return -1;
}

double MSCNProblem::getMax(int pos) {
	if (pos < d * f) return xdUpperBound->getAbsoluteElement(pos);
	if (pos >= d * f && pos < d * f + f * m) return xfUpperBound->getAbsoluteElement(pos - d * f);
	if (pos >= d * f + f * m && pos < d * f + f * m + m * s)
		return xmUpperBound->getAbsoluteElement(pos - d * f - f * m);
	return -1;
}



