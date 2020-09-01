
#ifndef TEP_LIST_9_MSCN_MISCTOOLS_H
#define TEP_LIST_9_MSCN_MISCTOOLS_H

#include <string>
#include <utility>
#include <vector>
#include <sstream>
#include <algorithm>
#pragma warning (disable : 4018)

std::vector<std::string> split(const std::string &stringToSplit) {
	std::stringstream stream(stringToSplit);
	std::string tmp;
	std::vector<std::string> result;

	std::getline(stream, tmp);
	while (!tmp.empty()) {
		result.push_back(tmp);
		std::getline(stream, tmp);
	}

	return std::move(result);
}

std::string join(std::vector<std::string> stringList) {
	std::string result;
	for (int i = 0; i < stringList.size(); i++) {
		result += stringList[i];
	}
	return std::move(result);
}

std::string zipStringList(std::vector<std::string> stringList1, std::vector<std::string> stringList2) {
	std::vector<std::string> result;
	std::string resultString;
	int len = std::min(stringList1.size(), stringList2.size());
	for (int i = 0; i < len; i++) {
		result.push_back(stringList1[i]);
		result.emplace_back("\n");
		result.push_back(stringList2[i]);
		result.emplace_back("\n");
	}

	if (stringList1.size() > stringList2.size()) {
		for (int i = len; i < stringList1.size(); i++) {
			result.push_back(stringList1[i]);
			result.push_back("\n");
		}
	}
	else {
		for (int i = len; i < stringList2.size(); i++) {
			result.push_back(stringList2[i]);
			result.emplace_back("\n");
		}
	}

	resultString = join(result);
	return std::move(resultString);
}

std::string zipString(std::string string1, std::string string2) {
	std::vector<std::string> stringList1 = split(std::move(string1));
	std::vector<std::string> stringList2 = split(std::move(string2));

	std::string result = zipStringList(stringList1, stringList2);

	return std::move(result);
}

double *readVal(std::stringstream &stream, int n) {
	double *tmp = new double[n];
	std::string tmpString;
	for (int i = 0; i < n; i++) {
		std::getline(stream, tmpString);
		tmp[i] = std::stod(tmpString);
	}
	return tmp;
}

#endif //TEP_LIST_9_MSCN_MISCTOOLS_H
