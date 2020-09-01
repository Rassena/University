
#include "TreeDynamic.h"

TreeDynamic::TreeDynamic() {
    root = new NodeDynamic();
}

TreeDynamic::~TreeDynamic() {
    delete root;
}

NodeDynamic *TreeDynamic::getRoot() {
    return root;
}

void TreeDynamic::printTree() {
    root->printSub();
}

bool TreeDynamic::moveSubtree(NodeDynamic *newParent, NodeDynamic *nodeToMove) {
    if (newParent == NULL || nodeToMove == NULL)
        return false;

    NodeDynamic *oldParent = nodeToMove->getParent();

    if(oldParent!=NULL)
        oldParent->removeChild(nodeToMove);
    newParent->addNewChild(nodeToMove);

    return true;
}
