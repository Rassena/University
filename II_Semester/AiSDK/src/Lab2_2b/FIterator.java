package Lab2_2b;


public class FIterator<T> {
	LIterator<T> iter;
	Predicate<T> pred;
	int nextElem;
	boolean isDone = false;
	
	public FIterator(LIterator<T> it, Predicate<T> pr) {
		iter=it;
		pred=pr;
		findNextValid();
	}
	
	private void findNextValid() {
		while(!iter.isDone()) {
			if(pred.accept(iter.current())) {
				nextElem=iter.current();
				isDone=false;
				iter.next();
				return;
			}	
			iter.next();
		}
		isDone = true;
		nextElem=0;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void next() {
		System.out.println(nextElem);
		findNextValid();
	}
	
	
}
