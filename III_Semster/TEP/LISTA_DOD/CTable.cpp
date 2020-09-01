
#include "CTable.h"

CTable::CTable(){
	c_len = 0;
	pc_table = new char[0];
}

CTable::CTable(const CTable & pcOther){
	v_copy_values(pcOther);
}

CTable::~CTable(){
	if (pc_table != NULL)
		delete[] pc_table;
}

bool CTable::bSetNewSize(int cTabLen){
	//jezeli podana dlugosc jest identyczna lub nowa dlugosc jest niedodatnia to nic nie rob
	if (cTabLen <= 0 || cTabLen == c_len) return false;
	else{
		char *pc_tmp = new char[cTabLen];

		if (cTabLen > c_len){
			for (int i = 0; i < c_len; i++)
				pc_tmp[i] = pc_table[i];
		}
		else{
			for (int i = 0; i < cTabLen; i++)
				pc_tmp[i] = pc_table[i];
		}

		delete[] pc_table;

		pc_table = pc_tmp;
		c_len = cTabLen;

		return true;
	}
}

CTable CTable::operator=(const CTable & pcOther){
	if (this == &pcOther)
		return *this;

	if (pc_table != NULL) delete[] pc_table;
	v_copy_values(pcOther);

	return *this;
}

CTable CTable::operator=(std::string input){
	delete[] pc_table;
	pc_table = new char[input.size()];
	c_len = input.size();
			for (int i = 0; i < c_len; i++){
				pc_table[i] = input[i];
		}
	return CTable();
}

std::string CTable::sToStandard(){
	std::string s = "";
	for (int i = 0; i < c_len; i++) {
		s += pc_table[i];
	}

	return s;
}

bool CTable::operator==(char compare){
	for (int i = 0; i < c_len; i++) {
		if (pc_table[i] == compare) return true;
	}
	return false;
}


CTable::operator bool() const{
	return (c_len == 0);
}

/*
CTable  CTable::operator+(const CTable & pcOther){
	int tempI = c_len;
	bSetNewSize(c_len + pcOther.c_len);
	for (int i = tempI; i < c_len; i++) {
		pc_table[i] = pcOther.pc_table[i - tempI];
	}
	return *this;
}
CTable  CTable::operator+(std::string input) {
	int tempI = c_len;
	bSetNewSize(c_len + input.size());
	for (int i = tempI; i < c_len; i++) {
		pc_table[i] = input[i - tempI];
	}
	return *this;
}
*/

CTable  CTable::operator+=(std::string input) {
	int tempI = c_len;
	bSetNewSize(c_len + input.size());
	for (int i = tempI; i < c_len; i++) {
		pc_table[i] = input[i - tempI];
	}
	return *this;
}

void CTable::v_copy_values(const CTable & pcOther){
	c_len = pcOther.c_len;
	pc_table = new char[c_len];

	//kopiowanie zawartosci tablicy
	for (int i = 0; i < c_len; i++)
		pc_table[i] = pcOther.pc_table[i];
}
/*

CTable operator+(std::string input, const CTable & pcOther){
	CTable c_tmp;
	c_tmp.bSetNewSize(input.size() + pcOther.c_len);
	int tempI = input.size();
	for (int i = 0; i < tempI; i++) {
		c_tmp.pc_table[i] = input[i];
	}
	for (int i = tempI; i < c_tmp.c_len; i++) {
		c_tmp.pc_table[i] = pcOther.pc_table[i - tempI];
	}
	return c_tmp;

}
CTable operator+(const CTable & pcOther, std::string input){
	CTable c_tmp;
	c_tmp.bSetNewSize(input.size() + pcOther.c_len);
	int tempI = pcOther.c_len;
	for (int i = 0; i < tempI; i++) {
		c_tmp.pc_table[i] = pcOther.pc_table[i];
	}
	for (int i = tempI; i < c_tmp.c_len; i++) {
		c_tmp.pc_table[i] = input[i-tempI];
	}
	return c_tmp;
	return CTable();
}
*/