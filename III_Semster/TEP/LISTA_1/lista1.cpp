#include "pch.h"
#include <iostream>

void v_alloc_table_add_5(int iSize){

	if (iSize < 0){
		std::cout << "Bledny rozmiar tablicy" << std::endl;
		return;
	}

	const int to_add = 5;

	int* pi_table = new int[iSize];


	for (int i = 0; i < iSize; i++){
		pi_table[i] = i + to_add;
	}

	for (int i = 0; i < iSize; i++)
	{
		std::cout << pi_table[i] << std::endl;
	}

	delete pi_table;
}

bool b_alloc_table_2_dim(int*** piTable, int iSizeX, int iSizeY){
	if (iSizeX < 0 || iSizeY < 0){
		std::cout << "Bledny rozmiar tablicy" << std::endl;
		return false;
	}

	(*piTable) = new int*[iSizeX];

	for (int i = 0; i < iSizeX; i++){
		(*piTable)[i] = new int[iSizeY];
	}

	for (int i = 0; i < iSizeX; i++){
		for (int ii = 0; ii < iSizeY; ii++){
			(*piTable)[i][ii] = i * ii;
		}
	}

	for (int i = 0; i < iSizeX; i++){
		for (int ii = 0; ii < iSizeY; ii++){
			std::cout << (*piTable)[i][ii];
		}
		std::cout << std::endl;
	}

	return true;
}

bool b_dealloc_table_2_dim(int** piTable, int iSizeX, int iSizeY){
	if (iSizeX < 0 || iSizeY < 0) {
		std::cout << "Bledny rozmiar tablicy" << std::endl;
		return false;
	}

	for (int i = 0; i < iSizeY; i++){
		delete piTable[i];
	}
	delete piTable;

	return true;
}

bool b_make_triangle_mat(double ***pdMat, int iSize){
	if (iSize < 0) 
	{
		std::cout << "Bledny rozmiar tablicy" << std::endl;
		return false;
	}

	(*pdMat) = new double*[iSize];

	int size2Dim = iSize;

	for (int i = 0; i < iSize; i++){
		(*pdMat)[i] = new double[size2Dim];
		size2Dim--;
	}

	return true;
}

bool b_remove_triangle_mat(double **pdMat, int iSize){
	if (iSize < 0) {
		std::cout << "Bledny rozmiar tablicy" << std::endl;
		return false;
	}
	

	for (int i = 0; i < iSize; i++){
		delete pdMat[i];
	}

	delete pdMat;

	return true;

}
/*
int main(){
	const int size_1 = 5;
	const int size_23_x = 5;
	const int size_23_y = 3;

	const int size_4 = 5;

	v_alloc_table_add_5(size_1);

	int** pi_table = NULL;

	double** pd_mat = NULL;



	std::cout << b_alloc_table_2_dim(&pi_table, size_23_x, size_23_y) << std::endl;

	std::cout << b_dealloc_table_2_dim(pi_table, size_23_x, size_23_y) << std::endl;

	std::cout << b_make_triangle_mat(&pd_mat, size_4) << std::endl;;

	std::cout << b_remove_triangle_mat(pd_mat, size_4);
}

*/