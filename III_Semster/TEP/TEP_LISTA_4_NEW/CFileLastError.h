#ifndef CFILELASTERROR_H
#define	CFILELASTERROR_H

#include <vector>
#include <stdio.h>
#include <iostream>
#pragma warning (disable : 4996)
#pragma warning (disable : 4018)


class CFileLastError {
public:
	CFileLastError();

	CFileLastError(const std::string &file_name);

	~CFileLastError();

	void openFile(const std::string &file_name);
	void closeFile();

	void printLine(const std::string &line);

	void printManyLines(std::vector<std::string> lines);

	bool isOpen();

	static bool getLastError();

private:
	FILE *file_ptr;
	static bool last_error;
};

#endif 
