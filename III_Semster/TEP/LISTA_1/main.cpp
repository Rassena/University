#include "pch.h"
#include <iostream>
#include "lista1.cpp"
#include "Header.h"




int main() {
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