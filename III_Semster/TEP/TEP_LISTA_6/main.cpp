
#include <iostream>
#include "TreeStatic.h"
#include "TreeDynamic.h"
#include "NodeDynamic.h"
#include "NodeStatic.h"

int main() {
	typename TreeDynamic<int> tree;
	typename TreeDynamic<int> tree2;


    //example2
    /*TreeStatic tree3;
    tree3.getRoot()->addNewChild();
    tree3.getRoot()->addNewChild();

    tree3.getRoot()->getChild(0)->setVal(1);
    tree3.getRoot()->getChild(1)->setVal(2);

    tree3.getRoot()->getChild(0)->addNewChild();
    tree3.getRoot()->getChild(0)->addNewChild();

    tree3.getRoot()->getChild(0)->getChild(0)->setVal(11);
    tree3.getRoot()->getChild(0)->getChild(1)->setVal(12);

    tree3.getRoot()->getChild(1)->addNewChild();
    tree3.getRoot()->getChild(1)->addNewChild();

    tree3.getRoot()->getChild(1)->getChild(0)->setVal(21);
    tree3.getRoot()->getChild(1)->getChild(1)->setVal(22);

    tree3.getRoot()->getChild(0)->getChild(0)->printUp();

    std::cout<<"------"<<std::endl;
    tree3.getRoot()->getChild(0)->getChild(0)->printUp();*/

	

    tree.getRoot()->addNewChild();
    tree.getRoot()->addNewChild();
    tree.getRoot()->addNewChild();
    tree.getRoot()->getChild(0)->setVal(1);
    tree.getRoot()->getChild(1)->setVal(2);
    tree.getRoot()->getChild(2)->setVal(3);
    tree.getRoot()->getChild(2)->addNewChild();
    tree.getRoot()->getChild(2)->getChild(0)->setVal(4);

    tree2.getRoot()->setVal(50);
    tree2.getRoot()->addNewChild();
    tree2.getRoot()->addNewChild();
    tree2.getRoot()->getChild(0)->setVal(54);
    tree2.getRoot()->getChild(1)->setVal(55);
    tree2.getRoot()->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->getChild(0)->setVal(56);
    tree2.getRoot()->getChild(0)->getChild(1)->setVal(57);
    tree2.getRoot()->getChild(0)->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->getChild(0)->getChild(0)->setVal(58);

	/*
    tree.moveSubtree(tree.getRoot()->getChild(2), tree2.getRoot()->getChild(0));
    tree.getRoot()->getChild(2)->getChild(1)->getChild(0)->printUp();
	*/

	std::cout << "------" << std::endl;
	tree.printTree();
	std::cout << "------" << std::endl;
	tree2.printTree();

	system("pause");
}