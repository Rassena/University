package Agnieszka;

public class ArrayIterator<T> implements Iterator<T> {
	T tab[];
	int pos =0;
	
	public ArrayIterator(T tablica[]) {
		if(tablica.length!=0)
			tab = tablica;
		else
			throw new IllegalStateException();
	}
	
	public void next() {
		pos++;
	}
	
	public void first() {
		pos=0;
	}
	
	public void last() {
		pos=tab.length-1;
	}
	
	public void previous() {
		pos--;
	}
	
	public boolean hasDone() {
		if(pos<0||pos>=tab.length)
			return true;
		return false;
	}
	
	public T current() {		
		if(!hasDone())
			return tab[pos];
		throw new IllegalStateException();
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	
	
}
