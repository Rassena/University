package Lab2_2b;

public class LIterator<T> implements Iterator<T> {

	int m;
	int n;
	int current;
	
	public LIterator(int bottom, int upper) {
		m=bottom;
		n=upper;
		current = m;
	}
	
	
	public void first() {
		current = m;
	}

	
	public void last() {
		current = n;
	}

	
	public void next() {
		current++;
	}


	public boolean isDone() {
		if(current<m||current>n)
			return true;
		return false;
	}


	public int current() {
		return current;
	}
}
