
#include "CTable.h"

CTable::CTable(){
	s_name = DEFAULT_NAME;
	i_len = DEFAULT_LENGTH;
	pi_table = new int[i_len];

	std::cout << "bezp: " << s_name << std::endl;
}

CTable::CTable(string sName, int iTabLen){
	s_name = sName;
	i_len = iTabLen;
	pi_table = new int[i_len];

	std::cout << "parametr: " << s_name << std::endl;
}

CTable::CTable(const CTable & pcOther){
	v_copy_values(pcOther);

	std::cout << "copy: " << s_name << std::endl;
}

CTable::~CTable(){
	if (pi_table != NULL)
		delete[] pi_table;

	std::cout << "Dstr: " << s_name << std::endl;
}

void CTable::vSetName(string sName){
	s_name = sName;
}

bool CTable::bSetNewSize(int iTabLen){
	//jezeli podana dlugosc jest identyczna lub nowa dlugosc jest niedodatnia to nic nie rob
	if (iTabLen <= 0 || iTabLen == i_len) return false;
	else{
		int *pi_tmp = new int[iTabLen];

		if (iTabLen > i_len){
			for (int i = 0; i < i_len; i++)
				pi_tmp[i] = pi_table[i];
		}
		else{
			for (int i = 0; i < iTabLen; i++)
				pi_tmp[i] = pi_table[i];
		}

		delete[] pi_table;

		pi_table = pi_tmp;
		i_len = iTabLen;

		return true;
	}
}

//wystarczy u¿yæ konkstruktora kopiuj¹cego klasy kopiowanej zamiast powielac kod z tego konstruktora
CTable * CTable::pcClone(){
	CTable *pc_clone = new CTable(*this);

	return pc_clone;
}

CTable CTable::operator=(const CTable & pcOther){
	if (this == &pcOther)
		return *this;

	std::cout << "copy op= " << s_name << " <- " << pcOther.s_name << std::endl;

	if (pi_table != NULL) delete[] pi_table;
	v_copy_values(pcOther);

	return *this;
}

CTable  CTable::operator+(const CTable & pcOther){
	CTable c_tmp(s_name + " + " + pcOther.s_name, i_len + pcOther.i_len);

	for (int i = 0; i < c_tmp.i_len; i++){
		if (i < i_len)
			c_tmp.pi_table[i] = pi_table[i];
		else
			c_tmp.pi_table[i] = pcOther.pi_table[i - (i_len)];
	}

	return c_tmp;
}

CTable CTable::operator+(int iVal){
	CTable c_tmp(DEFAULT_NAME, 1);
	c_tmp.bSetValueAt(0, iVal);

	return *this + c_tmp;
}

void CTable::vPrintInfo(){
	std::cout << "\ncTable-info\n\n";
	std::cout << "s_name = " << s_name << std::endl;
	std::cout << "i_len = " << i_len << "\n\n";
	std::cout << "/cTable-info\n\n";
}

void CTable::vPrintTab(){
	std::cout << "\n" << s_name << "-content\n";
	for (int i = 0; i < i_len; i++)
		std::cout << "T[" << i << "] = " << pi_table[i] << std::endl;
	std::cout << "/" << s_name << "-content\n\n";
}

bool CTable::bSetValueAt(int iIndex, int iNum){
	if (iIndex < 0 || iIndex >= i_len)
		return false;
	else
		pi_table[iIndex] = iNum;

	return true;
}

int CTable::iGetSize(){
	return i_len;
}

void CTable::v_copy_values(const CTable & pcOther){
	s_name = pcOther.s_name + "_copy";
	i_len = pcOther.i_len;
	pi_table = new int[i_len];

	//kopiowanie zawartosci tablicy
	for (int i = 0; i < i_len; i++)
		pi_table[i] = pcOther.pi_table[i];
}
