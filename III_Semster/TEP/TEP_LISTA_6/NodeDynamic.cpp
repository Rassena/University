

#include <iostream>
#include "NodeDynamic.h"
#include <vector>


template <typename T> T NodeDynamic<T>::getVal()const{
    return i_val;
}

template <typename T>void NodeDynamic<T>::setVal(T newVal) {
    T = newVal;
}

template <typename T>void NodeDynamic<T>::addNewChild(NodeDynamic *pDynamic) {
    children.push_back(pDynamic);
    pDynamic->parent = this;
}

template <typename T> NodeDynamic<T> *NodeDynamic<T>::getChild(int offset) {
    return children[offset];
}

template <typename T> int NodeDynamic<T>::getNChildren() {
    return children.size();
}

template <typename T> NodeDynamic<T>::NodeDynamic() {
    parent = NULL;
    i_val = 0;
}

template <typename T>NodeDynamic<T>::~NodeDynamic<T>() {
    int size = children.size();
    for (int i = 0; i < size; i++) {
        delete children[i];
    }
}

template <typename T> void NodeDynamic<T>::printSub() {
    std::cout << i_val << std::endl;
    int nChildren = children.size();
    for (int i = 0; i < nChildren; i++) {
        children[i]->printSub();
    }
}

template <typename T> NodeDynamic<T> *NodeDynamic<T>::getParent() {
    return parent;
}

template <typename T>void NodeDynamic<T>::removeChild(NodeDynamic<T> *childToRemove) {
	typename vector<NodeDynamic<T> *>::iterator iterator = std::find(children.begin(), children.end(), childToRemove);
	children.erase(iterator);
   // children.erase(std::find(children.begin(), children.end(), childToRemove), children.end());
}

template <typename T>void NodeDynamic<T>::addNewChild() {
    NodeDynamic<T> *child = new NodeDynamic<T>();
    child->parent = this;
    children.push_back(child);
}

template <typename T> void NodeDynamic<T>::printUp() {
    std::cout << i_val << std::endl;
    if (parent != NULL)
        parent->printUp();
}

template <typename T>void NodeDynamic<T>::print() {
    std::cout << i_val << std::endl;
}