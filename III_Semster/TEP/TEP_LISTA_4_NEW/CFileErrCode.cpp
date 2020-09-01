#include <vector>
#include <stdio.h>
#include <iostream>
#include "CFileErrCode.h"
#pragma warning(disable: 4996)
#pragma warning(disable: 4018)



	CFileErrCode::CFileErrCode() {
		file_ptr = NULL;
	}

	CFileErrCode::CFileErrCode(const std::string &file_name, bool &flag) {
		file_ptr = NULL;
		flag = openFile(file_name);
	}

	CFileErrCode::~CFileErrCode() {
		closeFile();
	}

	bool CFileErrCode::openFile(const std::string &file_name) {
		if (isOpen()) return false;
		file_ptr = fopen(file_name.c_str(), "w+");
		return isOpen();
	}

	bool CFileErrCode::closeFile() {
		if (isOpen()) {
			fclose(file_ptr);
			file_ptr = NULL;
			return true;
		}
		return false;
	}

	bool CFileErrCode::printLine(const std::string &line) {
		if (!isOpen()) {
			return false;
		}
		fprintf(file_ptr, "%s\n", line.c_str());
		return true;
	}

	bool CFileErrCode::printManyLines(std::vector<std::string> lines) {
		for (int i = 0; i < lines.size(); i++) {
			if (!printLine(lines[i])) return false;
		}
		return true;
	}

	bool CFileErrCode::isOpen() {
		return file_ptr != NULL;
	}
