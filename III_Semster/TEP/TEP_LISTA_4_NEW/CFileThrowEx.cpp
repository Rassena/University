#include <vector>
#include <stdio.h>
#include <iostream>
#include "CFileThrowEx.h"
#define ERROR 1
#pragma warning(disable: 4996)
#pragma warning(disable: 4018)


	CFileThrowEx::CFileThrowEx() {
		file_ptr = NULL;
	}

	CFileThrowEx::CFileThrowEx(const std::string &file_name) {
		file_ptr = NULL;
		openFile(file_name);
	}

	CFileThrowEx::~CFileThrowEx() {
		closeFile();
	}

	void CFileThrowEx::openFile(const std::string &file_name) {
		if (isOpen()) throw ERROR;

		file_ptr = fopen(file_name.c_str(), "w+");

		if (!isOpen()) {
			throw ERROR;
		}
	}

	void CFileThrowEx::closeFile() {
		if (isOpen()) {
			fclose(file_ptr);
			file_ptr = NULL;
		}
	}

	void CFileThrowEx::printLine(const std::string &line) {
		if (!isOpen()) {
			throw ERROR;
		}
		fprintf(file_ptr, "%s\n", line.c_str());
	}

	void CFileThrowEx::printManyLines(std::vector<std::string> lines) {
		for (int i = 0; i < lines.size(); i++) {
			printLine(lines[i]);
		}
	}

	bool CFileThrowEx::isOpen() {
		return file_ptr != NULL;
	}

