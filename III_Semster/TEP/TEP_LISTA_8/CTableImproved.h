#pragma once
#include <iostream>
#include <string>

#define DEFAULT_NAME "i_table"
#define DEFAULT_LENGTH 10;

using std::string;

class CTableImproved{

public:
	CTableImproved();
	CTableImproved(string sName, int iTabLen);
	CTableImproved(const CTableImproved &pcOther);
	CTableImproved(CTableImproved &&pcOther);
	~CTableImproved();

	void vSetName(string sName);
	bool bSetNewSize(int iTabLen);
	bool bSetValueAt(int iIndex, int iNum);
	CTableImproved *pcClone();
	CTableImproved operator+(const CTableImproved &pcOther);
	CTableImproved operator+(int iVal);
	CTableImproved operator=(const CTableImproved &pcOther);
	CTableImproved operator=(CTableImproved &&pcOther);
	void vPrintInfo();
	void vPrintTab();
	int iGetSize();

private:
	string s_name;
	int i_len;
	int *pi_table;

	void v_copy_values(const CTableImproved &pcOther);
};

