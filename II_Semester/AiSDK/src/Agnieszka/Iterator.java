package Agnieszka;

public interface Iterator<T>{

	public void next();
	
	public void first();
	
	public void last();
	
	public void previous();
	
	public boolean hasDone();
	
	public T current();
	
	public void remove();
	
}
