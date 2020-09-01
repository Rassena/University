
#include "Matrix.h"

#ifndef TEP_LIST_9_MSCN_BOUNDEDMATRIX_H
#define TEP_LIST_9_MSCN_BOUNDEDMATRIX_H

#endif //TEP_LIST_9_MSCN_BOUNDEDMATRIX_H

template<typename T>
class BoundedMatrix : public Matrix<T> {
    Matrix<T> *lowerBound;
    Matrix<T> *upperBound;
public:
    bool setElement(int y, int x, T val);

    void setUpperBound(Matrix<T> &bound);

    void setLowerBound(Matrix<T> &bound);

    T getLowerBoundFor(int y, int x);

    T getUpperBoundFor(int y, int x);

    BoundedMatrix(int sizey, int sizex);

    BoundedMatrix(int sizey, int sizex, Matrix<T> *lowerBound, Matrix<T> *upperBound);

    bool fromArray(T *arr, int offset);
};

template<typename T>
bool BoundedMatrix<T>::setElement(int y, int x, T val) {
    if (val > upperBound->getElement(y, x) || val < lowerBound->getElement(y, x)) return false;
    return Matrix<T>::setElement(y, x, val);
}

template<typename T>
void BoundedMatrix<T>::setLowerBound(Matrix<T> &bound) {
    lowerBound = bound;
}

template<typename T>
void BoundedMatrix<T>::setUpperBound(Matrix<T> &bound) {
    upperBound = bound;
}

template<typename T>
BoundedMatrix<T>::BoundedMatrix(int sizey, int sizex)
        :Matrix<T>(sizey, sizex) {}

template<typename T>
BoundedMatrix<T>::BoundedMatrix(int sizey, int sizex, Matrix<T> *lowerBound, Matrix<T> *upperBound)
        :Matrix<T>(sizey, sizex) {
    this->lowerBound = lowerBound;
    this->upperBound = upperBound;
}

template<typename T>
bool BoundedMatrix<T>::fromArray(T *arr, int offset) {
    for (int y = 0; y < this->sizey; y++) {
        for (int x = 0; x < this->sizex; x++) {
            T val = arr[x + y * this->sizex + offset];
            if (val != 0 && (val < lowerBound->getElement(y, x) || val > upperBound->getElement(y, x))) {
                return false;
            } else this->mat[y][x] = val;
        }
    }
    return true;
}

template<typename T>
T BoundedMatrix<T>::getLowerBoundFor(int y, int x) {
    return lowerBound->getElement(y, x);
}

template<typename T>
T BoundedMatrix<T>::getUpperBoundFor(int y, int x) {
    return upperBound->getElement(y, x);
}



