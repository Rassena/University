
#include <iostream>

#ifndef TEP_LIST_9_MSCN_MATRIX_H
#define TEP_LIST_9_MSCN_MATRIX_H


template<typename T>
class Matrix {
protected:
	T **mat;
	int sizex;
	int sizey;
public:
	Matrix(int sizey, int sizex);

	Matrix(Matrix &&other) noexcept;

	Matrix &operator=(Matrix &&other) noexcept;

	T operator-(int nRow);

	T operator|(int nColumn);

	~Matrix();

	bool setSize(int newSizey, int newSizex);

	virtual bool setElement(int y, int x, T val);

	int getSizeX();

	int getSizeY();

	T sum();

	T getElement(int row, int column);

	void print();

	Matrix operator*(Matrix &other);

	virtual bool fromArray(T *arr, int offset);

	std::string toString();

	int nElements();

	T getAbsoluteElement(int n);
};

template<typename T>
Matrix<T>::Matrix(int sizey, int sizex) {
	this->sizex = sizex;
	this->sizey = sizey;

	mat = new T *[sizey];

	for (int i = 0; i < sizey; i++) {
		mat[i] = new T[sizex];
	}
}

template<typename T>
Matrix<T>::~Matrix() {
	if (mat != nullptr) {
		for (int i = 0; i < sizey; ++i) {
			delete mat[i];
		}
		delete mat;
	}
}

template<typename T>
bool Matrix<T>::setSize(int newSizey, int newSizex) {

	T **newMat = new T *[newSizey];

	int minX = std::min(newSizex, sizex);
	int minY = std::min(newSizey, sizey);

	for (int y = 0; y < newSizey; y++) {
		newMat[y] = new T[newSizex];
	}

	for (int y = 0; y < minY; y++) {
		for (int x = 0; x < minX; x++) {
			newMat[y][x] = mat[y][x];
		}
	}

	for (int i = 0; i < sizey; ++i) {
		delete[] mat[i];
	}

	delete[] mat;
	mat = newMat;

	sizey = newSizey;
	sizex = newSizex;

	return true;
}

template<typename T>
bool Matrix<T>::setElement(int y, int x, T val) {
	if (y >= this->sizey || x >= this->sizex)
		return false;
	mat[y][x] = val;
	return true;
}

template<typename T>
void Matrix<T>::print() {
	for (int y = 0; y < sizey; y++) {
		for (int x = 0; x < sizex; x++) {
			std::cout << mat[y][x] << " ";
		}
		std::cout << std::endl;
	}
}

template<typename T>
Matrix<T>::Matrix(Matrix &&other) noexcept {
	mat = other.mat;
	sizex = other.sizex;
	sizey = other.sizey;
	other.mat = nullptr;
}

template<typename T>
Matrix<T> Matrix<T>::operator*(Matrix &other) {
	if (sizex != other.sizex || sizey != other.sizey)
		return std::move(*this);
	Matrix result(sizey, sizex);

	for (int y = 0; y < sizey; y++) {
		for (int x = 0; x < sizex; x++) {
			result.mat[y][x] = mat[y][x] * other.mat[y][x];
		}
	}

	return std::move(result);
}

template<typename T>
Matrix<T> &Matrix<T>::operator=(Matrix &&other) noexcept {

	for (int i = 0; i < sizey; ++i) {
		delete mat[i];
	}
	delete[] mat;

	mat = other.mat;
	sizex = other.sizex;
	sizey = other.sizey;
	other.mat = nullptr;
	//std::cout << "move operator" << std::endl;

	return *this;
}

template<typename T>
int Matrix<T>::getSizeX() {
	return sizex;
}

template<typename T>
int Matrix<T>::getSizeY() {
	return sizey;
}

template<typename T>
T Matrix<T>::sum() {
	T total = T();
	for (int y = 0; y < sizey; y++) {
		for (int x = 0; x < sizex; x++) {
			total += mat[y][x];
		}
	}
	return std::move(total);
}

template<typename T>
bool Matrix<T>::fromArray(T *arr, int offset) {
	for (int y = 0; y < sizey; y++) {
		for (int x = 0; x < sizex; x++) {
			mat[y][x] = arr[x + y * sizex + offset];
		}
	}
	return true;
}

template<typename T>
T Matrix<T>::operator-(int nRow) {
	T result = T();
	for (int i = 0; i < sizex; i++) {
		result = result + mat[nRow][i];
	}
	return std::move(result);
}

template<typename T>
T Matrix<T>::operator|(int nColumn) {
	T result = T();
	for (int i = 0; i < sizey; i++) {
		result = result + mat[i][nColumn];
	}
	return std::move(result);
}

template<typename T>
T Matrix<T>::getElement(int row, int column) {
	if (row >= sizey || column >= sizex)
		return std::move(T());
	return mat[row][column];
}

template<typename T>
std::string Matrix<T>::toString() {
	std::string tmp;
	for (int y = 0; y < sizey; y++) {
		for (int x = 0; x < sizex; x++) {
			tmp += std::to_string(mat[y][x]);
			tmp += '\n';
		}
	}
	return std::move(tmp);
}

template<typename T>
int Matrix<T>::nElements() {
	return sizex * sizey;
}

template<typename T>
T Matrix<T>::getAbsoluteElement(int n) {
	int y = n / sizex;
	int x = n % sizex;
	return mat[y][x];
}


#endif //TEP_LIST_9_MSCN_MATRIX_H
