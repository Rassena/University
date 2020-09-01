#include <iostream>

#include "CTab.h"


CTab::CTab(const CTab  &cOther) {
	v_copy(cOther);
	std::cout << "Copy " << std::endl;
}//CTab::CTab(const CTab  &cOther) 

CTab::CTab(CTab  &&cOther) {
	pi_tab = cOther.pi_tab;
	i_size = cOther.i_size;

	cOther.pi_tab = NULL;
	std::cout << "MOVE " << std::endl;


}//CTab::CTab(CTab  &&cOther) 

CTab::~CTab() {
	if (pi_tab != NULL)
		delete  pi_tab;
	std::cout << "Destr "<<std::endl;
}
bool CTab::bSetSize(int iNewSize){

	CTab temp;
	temp.v_copy(*this);

	pi_tab = new int[iNewSize];
	i_size = iNewSize;

	for (int i = 0; i < this->iGetSize() || i < temp.iGetSize(); i++) {
		pi_tab[i] = temp.pi_tab[i];
		i_size = temp.iGetSize();
	}

	return false;
}
//CTab::~CTab() 

CTab  CTab::operator=(const CTab  &cOther) {
	if (pi_tab != NULL)
		delete  pi_tab;
	v_copy(cOther);

	std::cout << "op= "<<std::endl;
	return(*this);
}//CTab  CTab::operator=(const CTab  &cOther) 

void  CTab::v_copy(const CTab  &cOther) {
	pi_tab = new int[cOther.i_size];
	i_size = cOther.i_size;

	for (int ii = 0; ii < cOther.i_size; ii++)
		pi_tab[ii] = cOther.pi_tab[ii];
}//void  CTab::v_copy(CTab  &cOther) 

CTab  cCreateTab() {
	CTab  c_result;
	c_result.bSetSize(5);
	return(c_result);
}//CTab  cCreateTab()  
