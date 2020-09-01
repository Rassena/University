
#ifndef TEP_LIST_5_TREES_TREEDYNAMIC_H
#define TEP_LIST_5_TREES_TREEDYNAMIC_H


#include "NodeDynamic.h"

template < typename T> class TreeDynamic {
    NodeDynamic<T> *root;
public:
    void printTree();

    TreeDynamic<T>();

    ~TreeDynamic<T>();

    NodeDynamic<T> *getRoot();

    bool moveSubtree(NodeDynamic<T> *newParent, NodeDynamic<T> *nodeToMove);
};


#endif //TEP_LIST_5_TREES_TREEDYNAMIC_H
