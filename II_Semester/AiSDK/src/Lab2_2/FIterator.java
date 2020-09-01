package Lab2_2;


public class FIterator<T> {
	TIterator<T> iter;
	Predicate<T> pred;
	T nextElem;
	boolean hasNext = false;
	
	public FIterator(TIterator<T> it, Predicate<T> pr) {
		iter=it;
		pred=pr;
		findNextValid();
	}
	
	private void findNextValid() {
		while(iter.hasNext()) {
			T elem = iter.next();
			if(pred.accept(elem)) {
				nextElem=elem;
				hasNext=true;
				return;
			}
		}
		hasNext = false;
		nextElem=null;
	}
	
	public boolean hasNext() {
		return hasNext;
	}
	
	public T next() {
		T elem = nextElem;
		findNextValid();
		return elem;
	}
	
	
	

}
