

#include "FileIO.h"

#include <utility>
#include <sstream>
#pragma warning(disable: 4996)

bool FileIO::getAllLines(std::string &buf) {
    if (file == NULL) return false;
    fseek(file, 0, SEEK_END);
    long fsize = ftell(file);
    fseek(file, 0, SEEK_SET);

    if (fsize == -1) return false;

    char *string = new char[fsize + 1];
    fread(string, 1, fsize, file);
    buf.append(string);
    delete[] string;
    return true;
}

bool FileIO::writeLine(std::string text) {
    if (file == NULL) return false;
    int written = fprintf(file, "%s\n", text.c_str());
    return written == text.size();
}

bool FileIO::writeLines(std::string text) {
    if (file == NULL) return false;
    std::stringstream stream(text);
    bool result = true;
    std::string tmp;
    while (!stream.eof()) {
        std::getline(stream, tmp);
        result &= writeLine(tmp);
    }
    return result;
}

bool FileIO::getLine(std::string &buf) {
    return false;
}

bool FileIO::openFile(const char *filename, const char *mode) {
    if (file != NULL) {
        bool result = closeFile();
        if (!result) return false;
        file = NULL;
    }

    file = fopen(filename, mode);

    return file != NULL;
}

bool FileIO::closeFile() {
    if (file == NULL) return false;
    int result = fclose(file);
    file = NULL;
    return result != EOF;
}

FileIO::FileIO() {
    file = NULL;
}

FileIO::~FileIO() {
    if (file != NULL)
        closeFile();
}


