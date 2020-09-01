
#ifndef TEP_LIST_7_SMARTPOINTER_H
#define TEP_LIST_7_SMARTPOINTER_H


#include <iostream>
#include "RefCounter.h"

template<typename T>
class SmartPointer {
	T *ptr;
	RefCounter *counter;
public:
	T &operator*();

	T *operator->();

	void operator=(const SmartPointer &other);

	SmartPointer(T *addr);

	SmartPointer(const SmartPointer &other);

	~SmartPointer();

};


template<typename T>
T &SmartPointer<T>::operator*() {
	return *ptr;
}

template<typename T>
T *SmartPointer<T>::operator->() {
	return ptr;
}

template<typename T>
void SmartPointer<T>::operator=(const SmartPointer<T> &other) {
	counter->decrement();
	if (!counter->hasRefs()) {
		if (ptr != NULL)
			delete ptr;
		delete counter;
	}
	ptr = other.ptr;
	counter = other.counter;
	counter->increment();
}

template<typename T>
SmartPointer<T>::SmartPointer(const SmartPointer<T> &other) {
	ptr = other.ptr;
	counter = other.counter;
	counter->increment();
}

template<typename T>
SmartPointer<T>::SmartPointer(T *addr) {
	ptr = addr;
	counter = new RefCounter();
	counter->increment();
}

template<typename T>
SmartPointer<T>::~SmartPointer() {
	counter->decrement();

	if (!counter->hasRefs()) {
		if (ptr != NULL) {
			std::cout << "removing object: " << *ptr << std::endl;
			delete ptr;
		}
		std::cout << "destroying counter" << std::endl;
		delete counter;
	}
}

#endif //TEP_LIST_7_SMARTPOINTER_H
