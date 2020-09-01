
#include <iostream>

#include "CTable.h"



int main(){

	CTable   c_str;

	c_str = "ma ";
	
	std::cout << c_str.sToStandard() << std::endl;
	c_str += "kota ";
	std::cout << c_str.sToStandard() << std::endl;
	c_str = c_str + "i psa";
	std::cout << c_str.sToStandard() << std::endl;
	c_str = "ala " + c_str;
	std::cout << c_str.sToStandard() << std::endl;
	std::cout << (c_str=='k') << std::endl;
	std::cout << (c_str == 'd') << std::endl;

	bool exist = false;
	std::cout << (exist = (bool)c_str) << std::endl;
	
	CTable c_str2;
	std::cout << (exist = (bool)c_str2) << std::endl;

	system("pause");
}