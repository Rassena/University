#ifndef CFILETHROWEX_H
#define CFILETHROWEX_H
#define ERROR 1


#include <vector>
#include <stdio.h>
#include <iostream>

class CFileThrowEx {
public:
	CFileThrowEx();

	CFileThrowEx(const std::string &file_name);

	~CFileThrowEx();

	void openFile(const std::string &file_name);

	void closeFile();

	void printLine(const std::string &line);

	void printManyLines(std::vector<std::string> lines);

	bool isOpen();

private:
	FILE *file_ptr;
};

#endif // !CFILETHROWEX_H
