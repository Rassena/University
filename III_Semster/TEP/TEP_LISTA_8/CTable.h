#pragma once
#include <iostream>
#include <string>

#define DEFAULT_NAME "c_table"
#define DEFAULT_LENGTH 10;

using std::string;

class CTable{

public:
	CTable();
	CTable(string sName, int iTabLen);
	CTable(const CTable &pcOther);
	~CTable();

	void vSetName(string sName);
	bool bSetNewSize(int iTabLen);
	bool bSetValueAt(int iIndex, int iNum);
	CTable *pcClone();
	CTable operator+(const CTable &pcOther);
	CTable operator+(int iVal);
	CTable operator=(const CTable &pcOther);
	void vPrintInfo();
	void vPrintTab();
	int iGetSize();

private:
	string s_name;
	int i_len;
	int *pi_table;

	void v_copy_values(const CTable &pcOther);
};

