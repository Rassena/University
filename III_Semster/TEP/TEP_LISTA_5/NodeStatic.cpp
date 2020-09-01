

#include "NodeStatic.h"
#include "TreeStatic.h"
#include <iostream>

int NodeStatic::getNChildren() {
    return children.size();
}

NodeStatic *NodeStatic::getChild(int x) {
    return &children[x];
}

void NodeStatic::print() {
    std::cout << val << std::endl;
}

void NodeStatic::printSub() {
    int nChildren = getNChildren();
    std::cout << val << std::endl;
    for (int i = 0; i < nChildren; i++) {
        children[i].printSub();
    }
}

void NodeStatic::addNewChild() {
    NodeStatic nodeStatic;
    nodeStatic.parent = this;
    children.push_back(nodeStatic);
}

int NodeStatic::getVal() const {
    return val;
}

void NodeStatic::setVal(int newVal) {
    val = newVal;
}

void NodeStatic::printUp() {
    std::cout << val << std::endl;
    if (parent != NULL)
        parent->printUp();
}

NodeStatic::NodeStatic() {
    parent = NULL;
    val = 0;
}

NodeStatic::NodeStatic(const NodeStatic &other) {
    children = other.children;
    parent = other.parent;
    val = other.val;
    int size = children.size();
    for (int i = 0; i < size; i++) {
        if (children[i].parent != this)
            children[i].parent = this;
    }
}

bool NodeStatic::operator==(const NodeStatic &other) {
    return (this == &other);
}

NodeStatic::~NodeStatic() {
   // std::cout << "removing node:" << val << std::endl;
}

NodeStatic *NodeStatic::getParent() {
    return parent;
}

void NodeStatic::addNewChild(NodeStatic *newChild) {
    newChild->parent = this;
    children.push_back(*newChild);
}

void NodeStatic::removeChild(NodeStatic *nodeToRemove) {
    std::vector<NodeStatic>::iterator it = std::find(children.begin(), children.end(), *nodeToRemove);
    children.erase(it);
}
