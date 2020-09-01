#include <iostream>
#include "CFileLastError.h"
#include "CFileThrowEx.h"
#include "CFileErrCode.h"
#include "Zakres.h"
#include <string>
#pragma warning (disable : 4101)


bool CFileLastError::last_error = false;

void getLastErrorTest() {
	CFileLastError file;
	std::cout << "getLastError tests:" << "\n";
	file.openFile("../text.txt");
	std::cout << "\tlast error after open: " << file.getLastError() << "\n";
	file.printLine("testowa linia 1");
	std::cout << "\tlast error after printLine: " << file.getLastError() << "\n";
	file.printLine("testowa linia 2");
	std::cout << "\tlast error after printLine: " << file.getLastError() << "\n";
	file.closeFile();
	std::cout << "\tlast error after close a file: " << file.getLastError() << "\n";
}

void throwExTest() {
	CFileThrowEx file;
	std::cout << "Exceptrions tests:" << "\n";
	try { file.openFile("../text.txt"); }
	catch (int ex) { std::cout << "Can't open a file" << "\n"; }
	try { file.printLine("test linia 1"); }
	catch (int ex) { std::cout << "File is not opened" << "\n"; }
	try { file.printLine("test linia 2"); }
	catch (int ex) { std::cout << "File is not opened" << "\n"; }
	try { file.closeFile(); }
	catch (int ex) { std::cout << "Can't close a file" << "\n"; }
}

std::string printSucc(bool val) {
	return val ? "success" : "failure";
}

void errCodeTest() {
	std::cout << "ErrCode tests:" << "\n";
	CFileErrCode file;
	std::cout << "\topenFile - " << printSucc(file.openFile("../text.txt")) << "\n";
	std::cout << "\tprintLine - " << printSucc(file.printLine("testowa linia numer 1")) << "\n";
	std::cout << "\tprintLine - " << printSucc(file.printLine("testowa linia numer 2")) << "\n";
	std::cout << "\tcloseFile - " << printSucc(file.closeFile()) << "\n";
}

void zakresTest() {
	Zakres test;
	std::cout << "Zakres tests: " << "\n";
	std::cout << "ifnumber(a): " << test.ifnumber('a') << "\n";
	std::cout << "ifnumber(1): " << test.ifnumber('1') << "\n";
	char a = 'a';
	char b = '1';
	char d = '9';
	std::cout << (int) a << "\n";
	std::cout << (int) b << "\n";
	std::cout << (int) d << "\n";
	std::cout << (int) '9' << "\n";
}

int main() {
	getLastErrorTest();
	throwExTest();
	errCodeTest();
	//zakresTest();
	system("pause");
	return 0;
}
