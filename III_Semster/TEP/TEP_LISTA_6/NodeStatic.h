

#ifndef TEP_LIST_5_TREES_NODESTATIC_H
#define TEP_LIST_5_TREES_NODESTATIC_H

#include <vector>

class NodeStatic {
    int val;
    NodeStatic *parent;
    std::vector<NodeStatic> children;
public:
    int getNChildren();

    void addNewChild();

    void addNewChild(NodeStatic *newChild);

    void printSub();

    void print();

    void printUp();

    int getVal() const;

    void setVal(int val);

    void removeChild(NodeStatic *nodeToRemove);

    NodeStatic *getChild(int offset);

    NodeStatic *getParent();

    NodeStatic();

    NodeStatic(const NodeStatic &other);

    ~NodeStatic();

    bool operator==(const NodeStatic &other);
};


#endif //TEP_LIST_5_TREES_NODESTATIC_H
