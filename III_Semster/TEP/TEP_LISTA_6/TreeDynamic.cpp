
#include "TreeDynamic.h"

template <typename T> TreeDynamic<T>::TreeDynamic() {
    root = new NodeDynamic<T>();
}

template <typename T>TreeDynamic<T>::~TreeDynamic<T>() {
    delete root;
}

template <typename T>NodeDynamic<T> *TreeDynamic<T>::getRoot() {
    return root;
}

template <typename T>void TreeDynamic<T>::printTree() {
    root->printSub();
}

template <typename T>bool TreeDynamic<T>::moveSubtree(NodeDynamic<T> *newParent, NodeDynamic<T> *nodeToMove) {
    if (newParent == NULL || nodeToMove == NULL)
        return false;

    NodeDynamic<T> *oldParent = nodeToMove->getParent();

    if(oldParent!=NULL)
        oldParent->removeChild(nodeToMove);
    newParent->addNewChild(nodeToMove);

    return true;
}
