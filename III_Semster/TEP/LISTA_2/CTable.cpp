#include "CTable.h"
#include <iostream>


CTable::CTable()
{
	s_name = DEFAULT_NAME;
	ip_table = new int[DEFAULT_LEN];
	iTableLen = DEFAULT_LEN;
	cout << "bezp: '" << s_name << "'"<<endl;
}//CTable::CTable()

CTable::CTable(string sName, int iTabLen)
{
	s_name = sName;
	ip_table = new int[iTabLen];
	iTableLen = iTabLen;
	cout << "parametr: '" << s_name << "'" << endl;
}//CTable::CTable(string sName, int iTableLen)


CTable::CTable(CTable & pcOther)
{
	s_name = pcOther.s_name + "_copy";
	ip_table = new int[pcOther.iTableLen];
	iTableLen = pcOther.iTableLen;
	for (int i = 0; i < iTableLen; i++) 
	{
		ip_table[i] = *(pcOther.ip_table + i);
	}//for (int i = 0; i < iTableLen; i++) 
	cout << "kopiuj:'" << s_name << "'" << endl;
}//CTable::CTable(CTable & pcOther)


CTable::~CTable()
{
	delete ip_table;
	ip_table = NULL;
	cout << "usuwam: '" << s_name << "'"<<endl;
}//CTable::~CTable()

void CTable::vSetName(string sName)
{
	s_name = sName;
}//void CTable::vSetName(string sName)

bool CTable::bSetNewSize(int iTabNewLen)
{
	if(iTabNewLen>0)
	{
		int* ip_newtable = new int[iTabNewLen];

		if (iTabNewLen > iTableLen)
		{
			for (int i = 0; i < iTableLen; i++)
			{
				ip_newtable[i] = ip_table[i];
			}//for (int i = 0; i < iTableLen; i++)

			//for (int i = 0; i < (iTabNewLen - iTableLen); i++)	ip_newtable[iTableLen+i] = DEFAULT_VALUE;			

		}//if (iTabNewLen > iTableLen)
		else
		{
			for (int i = 0; i < iTabNewLen; i++)
			{
				ip_newtable[i] = ip_table[i];
			}//for (int i = 0; i < iTabNewLen; i++)

		}//else
		delete ip_table;
		ip_table = ip_newtable;
		iTableLen = iTabNewLen;
		return true;

	}//if(iTabNewLen>0)
	else 
	{
		//cout << "Podano zla dlugosc tablicy" << endl;
		return false;
	}

}//bool CTable::bSetNewSize(int iTableLen)

CTable * CTable::pcClone()
{
	return new CTable(*this);
}//CTable * CTable::pcClone()


bool CTable::b_setPassword(string password)
{
	int i_passlen = password.length();
	if (i_passlen != 0 && i_passlen < 5 ) 
	{
		//cout << "za krotkie haslo, nie mozna ustawic" << endl;
		return false;
	}
	bool b_High = false;
	bool b_Small = false;
	bool b_number = false;

	//char* ch_tab = new char[i_passlen];
	for (int i = 0; i < i_passlen ; i++) {
		if (isdigit(password[i])) b_number = true;
		if (iswupper(password[i])) b_High = true;
		if (iswlower(password[i])) b_Small = true;
	}
	if (b_High && b_Small && b_number) {
		s_password = password;
		return true;
	}
	else return false;
}//bool CTable::b_setPassword(string password)

bool CTable::b_changepass(string password)
{
	int i_newpasslen = password.length();

	int i_oldpasslen = s_password.length();

	if (!i_oldpasslen) { return false; }


	int i_differ = i_newpasslen < i_oldpasslen ? (i_oldpasslen - i_newpasslen) : (i_newpasslen - i_oldpasslen);

	if ( i_differ >= 2) 
	{
		return b_setPassword(password);
	}//if ( i_differ >= 2) 

	for (int i = 0; i < (i_newpasslen < i_oldpasslen ? i_newpasslen : i_oldpasslen); i++)
	{
		if (password[i] != s_password[i]) i_differ++;
	}
	
	if (i_differ >= 2) return b_setPassword(password);
	else return false;

}//bool CTable::b_changepass(string password)







void CTable::v_ifill(int i_value)
{	
	for (int i = 0; i < iTableLen; i++) 
	{
		*(ip_table + i) = i_value;
	}
}//void CTable::v_ifill(int i_value)

void CTable::v_show()
{
	cout << "Obiekt CTable:" << endl << "Name: " << s_name << endl << "Dlugosc tablicy: " << iTableLen << endl;
	for (int i = 0; i < iTableLen; i++)
	{
		cout << "[" << i << "]=" << *((*this).ip_table+i) << "\n\n";
	}//for (int i = 0; i < iTableLen; i++)
}//void CTable::v_show()

