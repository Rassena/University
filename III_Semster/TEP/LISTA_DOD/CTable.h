#pragma once
#include <iostream>
#include <string>

#define DEFAULT_LENGTH 0;

class CTable{

public:
	CTable();
	CTable(const CTable &pcOther);
	~CTable();

//	CTable operator+(const CTable &pcOther);
//	CTable operator+(std::string input);
	CTable operator+=(std::string input);
	CTable operator=(const CTable &pcOther);
	CTable operator=(std::string input);
//	friend CTable operator+(std::string input, const CTable &pcOther);
//	friend CTable operator+(const CTable &pcOther, std::string input);
	std::string sToStandard();
	bool operator==(char compare);
	operator bool() const;

private:

	int c_len;
	char *pc_table;
	bool bSetNewSize(int iTabLen);
	void v_copy_values(const CTable & pcOther);
};