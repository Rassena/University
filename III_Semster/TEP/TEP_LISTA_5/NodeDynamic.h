
#ifndef TEP_LIST_5_TREES_NODEDYNAMIC_H
#define TEP_LIST_5_TREES_NODEDYNAMIC_H


#include <vector>

class NodeDynamic {
    int val;
    NodeDynamic *parent;
    std::vector<NodeDynamic *> children;
public:
    int getNChildren();

    void addNewChild(NodeDynamic *pDynamic);

    void addNewChild();

    NodeDynamic *getChild(int offset);

    int getVal() const;

    void setVal(int val);

    void printSub();

    NodeDynamic *getParent();

    void removeChild(NodeDynamic *childToRemove);

    void printUp();

    void print();

    NodeDynamic();

    ~NodeDynamic();
};

#endif //TEP_LIST_5_TREES_NODEDYNAMIC_H
