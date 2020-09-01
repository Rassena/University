#include <iostream>
#include "SmartPointer.h"

void test() {
	int* i = new int(3);
	SmartPointer<int> ptri1(i);
	SmartPointer<int> ptri2(ptri1);
	SmartPointer<int> ptri3(ptri2);
	std::cout << *i << std::endl;
	std::cout << *ptri1 << std::endl;
	*ptri1 = 10;
	std::cout << *ptri2 << std::endl;
	std::cout << "-------" << std::endl;

	double* dub = new double(10.5);
	SmartPointer<double> ptrd(dub);
	std::cout << *ptrd << std::endl;
	*ptrd = 3.3;
	std::cout << *dub << std::endl;
	std::cout << "-------" << std::endl;

	int* temp = new int(5);
	SmartPointer<int> ptemp(temp);
	std::cout << *ptemp << std::endl;
	ptri2 = ptemp;
	std::cout << *ptri2 << std::endl;

}

int main() {

	test();

	system("pause");
}