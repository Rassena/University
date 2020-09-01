#include "MSCNProblem.h"
#include "FileIO.h"

int main() {

	MSCNProblem *problem = new MSCNProblem();

	/*
	double solution[] = { 136.99, 29.689, 40.775,
	//	1700.9,	848.95, 225.568, 900.983, 460.027, 
	//	848.94, 225.152, 769.998, 
	//	36, 82, 32, 71, 4.2, 54.7, 8.0, 15.5,
		EOS };

	double solutionDK[] = { 1, 1, 1, EOS };

	std::string prob = "D 1\n"
		"F 1\n"
		"M 1\n"
		"S 1\n"
		"sd\n"
		"100\n"
		"sf\n"
		"50\n"
		"sm\n"
		"10.5\n"
		"ss\n"
		"100\n"
		"cd\n"
		"3 \n"
		"cf\n"
		"4\n"
		"cm\n"
		"1\n"
		"ud\n"
		"10\n"
		"uf\n"
		"10\n"
		"um\n"
		"10\n"
		"p\n"
		"100\n"
		"xdminmax\n"
		"0\n"
		"100\n"
		"xfminmax\n"
		"0\n"
		"100\n"
		"xmminmax\n"
		"0\n"
		"100\n";

	std::string test = "D 3\n"
		"F 1\n"
		"M 4\n"
		"S 1\n"
		"sd\n"
		"081.62\n"
		"873.908\n"
		"665.266\n"
		"sf\n"
		"3979.87\n"
		"sm\n"
		"475.308\n"
		"954.766\n"
		"859.654\n"
		"988.114\n"
		"ss\n"
		"3000\n"
		"cd\n"
		"688.743\n"
		"77.587\n"
		"477.15\n"
		"cf\n"
		"264.319\n"
		"323.29\n"
		"280.501\n"
		"563.945\n"
		"cm\n"
		"19.155\n"
		"21.511\n"
		"19.783\n"
		"448.445\n"
		"ud\n"
		"126.424 \n"
		"797.443 \n"
		"968.385\n"
		"uf\n"
		"60.001\n"
		"um\n"
		"47.5839 \n"
		"775.875 \n"
		"784.693 \n"
		"376.309\n"
		"p\n"
		"556.855\n"
		"xdminmax\n"
		"30.8261\n"
		"4000\n"
		"103.683\n"
		"671.198\n"
		"304.109\n"
		"828.039\n"
		"xfminmax\n"
		"185.817\n"
		"2000\n"
		"253.947\n"
		"2000\n"
		"131.641\n"
		"2000\n"
		"690.481\n"
		"2000\n"
		"xmminmax\n"
		"322.536\n"
		"552.102\n"
		"735.512\n"
		"904.909\n"
		"54.2136\n"
		"280.717\n"
		"600.012\n"
		"986.023\n";


	problem->loadFromString(test);
	problem->loadFromString(prob);
	problem->saveToFile("problem.txt");
	MSCNProblem problem2;
	problem2.loadFromFile("problem.txt");
	bool success = true;
	std::cout << problem->getQuality(solution, success) << std::endl;
	std::cout << problem2.getQuality(solution, success) << std::endl;
	std::cout << problem->checkValidity(solution, success) << std::endl;
	std::cout << "valid input " << success << std::endl;
	delete problem;

	problem->setDeliverers(10);
	problem->setDistributors(10);
	problem->setFactories(10);
	problem->setShops(10);
	*/



	bool success = false;
	double solution[] = { 100, 100, 100, EOS };
	problem->loadFromFile("problem.txt");

	std::cout << problem->checkValidity(solution, success) << std::endl;
	std::cout << "valid input " << success << std::endl;

	std::cout << problem->getQuality(solution, success) << std::endl;

	system("pause");
	return 0;
}
