#include "CTable.h"
#include "Procedure.h"
#include <iostream>



int main() 
{

	CTable* pcTable = new CTable[5];

	pcTable[2].vSetName("Jan");

	CTable* ct_temp = &(pcTable[2]);

	(*ct_temp).v_show();

	(*ct_temp).v_ifill(20);

	(pcTable[2]).v_show();

	CTable ct_equal = pcTable[2];

	cout << "Password: " << ct_equal.s_getPass() << endl;

	cout << (pcTable[2]).b_setPassword("Has1") << endl;
	cout << (pcTable[2]).b_setPassword("Hasl123") << endl;
	(pcTable[2]).b_changepass("Hast123");
	(pcTable[2]).b_changepass("Hast12");
	(pcTable[2]).b_changepass("Has1");
	(pcTable[2]).b_changepass("Hast1234");
	(pcTable[2]).b_changepass("Hast12");


	CTable* ptobject = new CTable(pcTable[2]);

	ptobject->bSetNewSize(5);
	ptobject->v_show();

	v_mod_tab(ptobject, 10);
	ptobject->v_show();

	v_mod_tab((*ptobject), 6);
	ptobject->v_show();

	delete[] pcTable;

	system("pause");

	return 0;
}

//Setname 
//setpassword conajmniej 5 znakow przynajmniej jedna duza , jedna mala, jedna cyfra bool
// change password roznic na 2 pozycjach
