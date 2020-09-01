

#ifndef TEP_LIST_5_TREES_TREESTATIC_H
#define TEP_LIST_5_TREES_TREESTATIC_H


#include "NodeStatic.h"

class TreeStatic {
    NodeStatic root;
public:
    void printTree();

    NodeStatic *getRoot();

    bool moveSubtree(NodeStatic *newParent, NodeStatic *nodeToMove);
};


#endif //TEP_LIST_5_TREES_TREESTATIC_H
