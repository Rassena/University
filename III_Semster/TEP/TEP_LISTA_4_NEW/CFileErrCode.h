#ifndef CFILEERRCODE_H
#define CFILEERRCODE_H

#include <vector>
#include <stdio.h>
#include <iostream>

class CFileErrCode {
public:
	CFileErrCode();

	CFileErrCode(const std::string &file_name, bool &flag);

	~CFileErrCode();

	bool openFile(const std::string &file_name);

	bool closeFile();

	bool printLine(const std::string &line);

	bool printManyLines(std::vector<std::string> lines);

	bool isOpen();

private:
	FILE *file_ptr;
};

#endif // !CFILEERRCODE_H