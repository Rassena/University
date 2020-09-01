

#include "TreeStatic.h"

void TreeStatic::printTree() {
    root.printSub();
}

NodeStatic *TreeStatic::getRoot() {
    return &root;
}

bool TreeStatic::moveSubtree(NodeStatic *newParent, NodeStatic *nodeToMove) {
    if(newParent == NULL || nodeToMove == NULL)
        return false;
    NodeStatic *parent = nodeToMove->getParent();
    newParent->addNewChild(nodeToMove);

    if(parent!=NULL)
        parent->removeChild(nodeToMove);

    return true;
}