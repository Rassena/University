
#include "RefCounter.h"

void RefCounter::decrement() {
	refs -= 1;
}

void RefCounter::increment() {
	refs += 1;
}

bool RefCounter::hasRefs() {
	return refs > 0;
}

RefCounter::RefCounter() {
	refs=0;
}
