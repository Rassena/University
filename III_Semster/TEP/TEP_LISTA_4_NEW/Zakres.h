#ifndef ZAKRES_H
#define ZAKRES_H
#define ERROR 1


#include <iostream>
#include <string>

class Zakres {
public:

	int zakres(std::string text, int start, int end);

	int zakres(const char* text, int start, int end);

	Zakres& operator=(std::string text);

	bool ifnumber(const char c);
};


#endif // !ZAKRES_H
