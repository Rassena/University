
#include "CTableImproved.h"

CTableImproved::CTableImproved() {
	s_name = DEFAULT_NAME;
	i_len = DEFAULT_LENGTH;
	pi_table = new int[i_len];

	std::cout << "bezp: " << s_name << std::endl;
}

CTableImproved::CTableImproved(string sName, int iTabLen){
	s_name = sName;
	i_len = iTabLen;
	pi_table = new int[i_len];

	std::cout << "parametr: " << s_name << std::endl;
}

CTableImproved::CTableImproved(const CTableImproved & pcOther){
	v_copy_values(pcOther);

	std::cout << "copy: " << s_name << std::endl;
}

CTableImproved::CTableImproved(CTableImproved && pcOther){
	s_name = pcOther.s_name + "_MOVED";
	i_len = pcOther.i_len;
	pi_table = pcOther.pi_table;

	pcOther.pi_table = NULL;
	pcOther.i_len = 0;

	std::cout << "MOVE: " << pcOther.s_name << std::endl;
}

CTableImproved::~CTableImproved(){
	if (pi_table != NULL)
		delete[] pi_table;

	std::cout << "Destr: " << s_name << std::endl;
}

void CTableImproved::vSetName(string sName){
	s_name = sName;
}

bool CTableImproved::bSetNewSize(int iTabLen){
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

		if (pi_table != NULL)
			delete[] pi_table;

		pi_table = pi_tmp;
		i_len = iTabLen;

		return true;
	}
}

CTableImproved * CTableImproved::pcClone(){
	CTableImproved *pc_clone = new CTableImproved(*this);

	return pc_clone;
}

CTableImproved CTableImproved::operator=(const CTableImproved & pcOther){
	if (this == &pcOther)
		return *this;

	std::cout << "copy op= " << s_name << " <- " << pcOther.s_name << std::endl;

	if (pi_table != NULL)
		delete[] pi_table;
	v_copy_values(pcOther);

	return *this;
}

CTableImproved CTableImproved::operator=(CTableImproved && pcOther){
	if (this == &pcOther)
		return *this;

	std::cout << "move op= " << s_name << " <- " << pcOther.s_name << std::endl;

	if (pi_table != NULL)
		delete[] pi_table;

	s_name = pcOther.s_name;
	i_len = pcOther.i_len;
	pi_table = pcOther.pi_table;

	pcOther.pi_table = NULL;
	pcOther.i_len=0;

	return std::move(*this);
}

CTableImproved  CTableImproved::operator+(const CTableImproved & pcOther){
	CTableImproved c_tmp(s_name + " + " + pcOther.s_name, i_len + pcOther.i_len);

	for (int i = 0; i < c_tmp.i_len; i++){
		if (i < i_len)
			c_tmp.pi_table[i] = pi_table[i];
		else
			c_tmp.pi_table[i] = pcOther.pi_table[i - (i_len)];
	}

	return c_tmp;
}

CTableImproved CTableImproved::operator+(int iVal){
	CTableImproved c_tmp(DEFAULT_NAME, 1);
	c_tmp.bSetValueAt(0, iVal);

	return *this + c_tmp;
}

void CTableImproved::vPrintInfo(){
	std::cout << "\nCTableImproved-info\n\n";
	std::cout << "s_name = " << s_name << std::endl;
	std::cout << "i_len = " << i_len << "\n\n";
	std::cout << "/CTableImproved-info\n\n";
}

void CTableImproved::vPrintTab(){
	std::cout << "\n" << s_name << "-content\n";
	for (int i = 0; i < i_len; i++)
		std::cout << "T[" << i << "] = " << pi_table[i] << std::endl;
	std::cout << "/" << s_name << "-content\n\n";
}

bool CTableImproved::bSetValueAt(int iIndex, int iNum){
	if (iIndex < 0 || iIndex >= i_len)
		return false;
	else
		pi_table[iIndex] = iNum;

	return true;
}

int CTableImproved::iGetSize(){
	return i_len;
}

void CTableImproved::v_copy_values(const CTableImproved & pcOther){
	s_name = pcOther.s_name + "_copy";
	i_len = pcOther.i_len;
	pi_table = new int[i_len];

	//kopiowanie zawartosci tablicy
	for (int i = 0; i < i_len; i++)
		pi_table[i] = pcOther.pi_table[i];
}

