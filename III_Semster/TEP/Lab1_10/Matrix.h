#pragma once
#include <vector>
#include <algorithm>
class Matrix
{
public:
	Matrix() {};
	~Matrix() { delete matrix; }
	void initialize() { matrix = new std::vector<std::vector<double> >(); };
	void addRow() { matrix->push_back(std::vector<double>()); };
	bool addToRow(int indexOfRow, double value);
	bool deleteRow(int indexOfRow);
	bool deleteColumn(int indexOfColumn);
	int getHeight() { return matrix->size(); }
	int getWidht(int indexOfRow) { return matrix->at(indexOfRow).size(); };
	double getValueAt(int indexOfRow, int indexOfColumn);
	bool setValueAt(int indexOfRow, int indexOfColumn, double value);
	void show();
private:
	std::vector<std::vector<double> >* matrix;
};