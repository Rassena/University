
#ifndef TEP_LIST_5_TREES_TREEDYNAMIC_H
#define TEP_LIST_5_TREES_TREEDYNAMIC_H


#include "NodeDynamic.h"

class TreeDynamic {
    NodeDynamic *root;
public:
    void printTree();

    TreeDynamic();

    ~TreeDynamic();

    NodeDynamic *getRoot();

    bool moveSubtree(NodeDynamic *newParent, NodeDynamic *nodeToMove);
};


#endif //TEP_LIST_5_TREES_TREEDYNAMIC_H
