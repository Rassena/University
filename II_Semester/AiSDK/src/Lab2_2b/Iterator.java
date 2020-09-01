package Lab2_2b;

public interface Iterator<T> {
	
	void first();
	
	void last();
	
	void next();
	
	boolean isDone();
	
	int current();
	
}
