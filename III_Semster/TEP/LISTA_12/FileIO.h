

#ifndef TEP_LIST_9_MSCN_FILEIO_H
#define TEP_LIST_9_MSCN_FILEIO_H


#include <cstdio>
#include <string>

class FileIO {
private:
    FILE *file;
public:
    bool getAllLines(std::string &buf);

    bool getLine(std::string &buf);

    bool writeLine(std::string text);

    bool writeLines(std::string text);

    bool openFile(const char *filename, const char *mode);

    bool closeFile();

    FileIO();

    ~FileIO();
};


#endif //TEP_LIST_9_MSCN_FILEIO_H
