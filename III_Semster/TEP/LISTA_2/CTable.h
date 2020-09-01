#pragma once
#include <string>
using namespace std;


#define DEFAULT_NAME "IMIE DOMYSLNE"
#define DEFAULT_LEN 10
#define DEFAULT_VALUE 10

//const string DEFAULT_NAME = "IMIE DOMYSLNE";
//const int DEFAULT_LEN = 10;
//const int DEFAULT_VALUE = 10;

class CTable
{

	string s_name;
	int* ip_table;
	int iTableLen;
	string s_password;

public:
	

	CTable();
	CTable(string sName, int iTableLen);
	CTable(CTable &pcOther);
	~CTable();

	void vSetName(string sName);
	bool bSetNewSize(int iTableLen);
	CTable *pcClone();
	bool b_setPassword(string password);
	bool b_changepass(string password);

	void v_ifill(int i_value);
	void v_show();
	inline string s_getName() { return s_name;}
	inline string s_getPass() { return s_password; }
	inline int  i_getLen() {return iTableLen;}

};//class CTable

