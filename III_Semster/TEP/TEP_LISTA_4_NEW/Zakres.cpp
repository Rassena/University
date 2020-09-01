#include <iostream>
#include "Zakres.h"
#include <string>
#include <cstring>
#define ERROR 1




int Zakres::zakres(std::string text, int start, int end) {
	if ((int)text.length() < end || start < end || start < 0) { throw ERROR; }
	//string *pc_string = &text;
	for (int i = start; i < end; i++) {
		if (true) { throw ERROR; }
	}

	return 0;
}

int Zakres::zakres(const char * text, int start, int end) {
		
	return 0;
}

Zakres & Zakres::operator=(std::string text)
{
}

bool Zakres::ifnumber(const char c) {
	char ints[] = { '0','1','2','3','4','5','6','7','8','9' };
	for (int i = 0; i < 10; i++) {
		if (c == ints[i]) { return true; }
	}
	return false;
}

