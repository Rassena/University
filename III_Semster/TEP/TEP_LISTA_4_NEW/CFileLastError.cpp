#include <vector>
#include <stdio.h>
#include <iostream>
#include "CFileLastError.h"
#pragma warning (disable : 4996)
#pragma warning (disable : 4018)


	CFileLastError::CFileLastError() {
		file_ptr = NULL;
		last_error = false;
	}

	CFileLastError::CFileLastError(const std::string &file_name) {
		file_ptr = NULL;
		openFile(file_name);
	}
	
	CFileLastError::~CFileLastError() {
		closeFile();
	}

	void CFileLastError::openFile(const std::string &file_name) {
		if (isOpen()) {
			last_error = true;
			return;
		}
		last_error = false;
		file_ptr = fopen(file_name.c_str(), "w+");

		if (!isOpen()) {
			last_error = true;
		}
	}

	void CFileLastError::closeFile() {
		last_error = false;
		if (isOpen()) {
			fclose(file_ptr);
			file_ptr = NULL;
			if (isOpen()) { last_error = true; }
		}
		else
			last_error = true;
	}

	void CFileLastError::printLine(const std::string &line) {
		last_error = false;
		if (!isOpen()) {
			last_error = true;
			return;
		}
		if (fprintf(file_ptr, line.c_str()) == line.length()) {
			fprintf(file_ptr, "\n");
		}
		else
			last_error = true;
	}

	void CFileLastError::printManyLines(std::vector<std::string> lines) {
		for (int i = 0; i < lines.size(); i++) {
			printLine(lines[i]);
			if (last_error) return;
		}
	}

	bool CFileLastError::isOpen() {
		return file_ptr != NULL;
	}

	bool CFileLastError::getLastError() {
		return last_error;
	}