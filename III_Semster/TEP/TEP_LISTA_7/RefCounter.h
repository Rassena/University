
#ifndef TEP_LIST_7_REFCOUNTER_H
#define TEP_LIST_7_REFCOUNTER_H


class RefCounter {
	int refs;
public:
	void increment();

	void decrement();

	bool hasRefs();

	RefCounter();

};


#endif //TEP_LIST_7_REFCOUNTER_H
