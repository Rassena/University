
#include <iostream>

#include "CTab.h"
#include "CTable.h"
#include "CTableImproved.h"


void test() {

	std::cout << "test " << "no " << "1." << std::endl;

	CTab   c_tab;
	CTab  c_other;
	/*initialize c_tab, c_other*/
	std::cout << "	" << "move " << std::endl;
	c_tab = std::move(c_other);

	c_tab.bSetSize(20);

	std::cout << std::endl;
	std::cout << "test " << "no " << "2." << std::endl;
	
	CTableImproved c_tablei;
	CTableImproved c_tablei_other;
	c_tablei.bSetNewSize(20);
	std::cout << "	" << "move " << std::endl;
	c_tablei_other=std::move(c_tablei);

//	std::cout << "	" << "copy " << std::endl;
//	c_tablei_other = c_tablei;


	std::cout << std::endl;
	std::cout << "test " << "no " << "3." << std::endl;

	CTable c_table;
	CTable c_table_other;
	c_table.bSetNewSize(20);
	std::cout << "	" << "move " << std::endl;
	c_table_other = std::move(c_table);
//	std::cout << "	" << "copy " << std::endl;
//	c_table_other = c_table;

	
	std::cout << std::endl;
	std::cout << "test " << "end " << std::endl;

}

int main(){
	
	test();

	system("pause");
}