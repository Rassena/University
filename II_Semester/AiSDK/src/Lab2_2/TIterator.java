package Lab2_2;

public class TIterator <T> {
	T[] tab;
	int pos =0;
	
	public TIterator(T tablica[]) {
		tab = tablica;
	}
	
	public boolean hasNext() {
		if(pos<tab.length)
			return true;
		return false;
	}

	public T next() {
		return tab[pos++];
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
