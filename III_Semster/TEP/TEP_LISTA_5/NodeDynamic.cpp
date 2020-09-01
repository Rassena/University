

#include <iostream>
#include "NodeDynamic.h"

int NodeDynamic::getVal() const {
    return val;
}

void NodeDynamic::setVal(int newVal) {
    val = newVal;
}

void NodeDynamic::addNewChild(NodeDynamic *pDynamic) {
    children.push_back(pDynamic);
    pDynamic->parent = this;
}

NodeDynamic *NodeDynamic::getChild(int offset) {
    return children[offset];
}

int NodeDynamic::getNChildren() {
    return children.size();
}

NodeDynamic::NodeDynamic() {
    parent = NULL;
    val = 0;
}

NodeDynamic::~NodeDynamic() {
    int size = children.size();
    for (int i = 0; i < size; i++) {
        delete children[i];
    }
}

void NodeDynamic::printSub() {
    std::cout << val << std::endl;
    int nChildren = children.size();
    for (int i = 0; i < nChildren; i++) {
        children[i]->printSub();
    }
}

NodeDynamic *NodeDynamic::getParent() {
    return parent;
}

void NodeDynamic::removeChild(NodeDynamic *childToRemove) {
    std::vector<NodeDynamic *>::iterator iterator = std::find(children.begin(), children.end(), childToRemove);
    children.erase(iterator);
}

void NodeDynamic::addNewChild() {
    NodeDynamic *child = new NodeDynamic();
    child->parent = this;
    children.push_back(child);
}

void NodeDynamic::printUp() {
    std::cout << val << std::endl;
    if (parent != NULL)
        parent->printUp();
}

void NodeDynamic::print() {
    std::cout << val << std::endl;
}