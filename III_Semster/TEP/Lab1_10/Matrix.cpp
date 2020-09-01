#include "pch.h"
#include <iostream>
#include "Matrix.h"

bool Matrix::addToRow(int indexOfRow, double value)
{
	if (0 > indexOfRow || indexOfRow > matrix->size()) return false;
	matrix->at(indexOfRow).push_back(value);
	return true;
}

bool Matrix::deleteRow(int indexOfRow)
{
	if (0 > indexOfRow || indexOfRow > matrix->size()) return false;
	matrix->erase(matrix->begin() + indexOfRow);
	return true;
}

bool Matrix::deleteColumn(int indexOfColumn)
{
	if (indexOfColumn < 0) return false;
	for (int i = 0; i < matrix->size(); i++)
	{
		if (indexOfColumn > matrix->at(i).size()) return false;
		matrix->at(i).erase(matrix->at(i).begin() + indexOfColumn);
	}
	return true;
}

void Matrix::show()
{
	for (int i = 0; i < matrix->size(); i++)
	{
		for (int j = 0; j < matrix->at(i).size(); j++)
		{
			std::cout << matrix->at(i).at(j) << " ";
		}
		std::cout << "\n";
	}
}

double Matrix::getValueAt(int indexOfRow, int indexOfColumn)
{
	if (indexOfRow < 0 || indexOfColumn < 0 || indexOfRow > getHeight() || indexOfColumn > getWidht(indexOfRow)) return -1;
	return matrix->at(indexOfRow).at(indexOfColumn);
}

bool Matrix::setValueAt(int indexOfRow, int indexOfColumn, double value)
{
	if (indexOfRow < 0 || indexOfColumn < 0 || indexOfRow > getHeight() || indexOfColumn > getWidht(indexOfRow)) return false;
	matrix->at(indexOfRow).at(indexOfColumn) = value;
	return true;
}