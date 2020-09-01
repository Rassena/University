package Lab2_4;

public class CycledBuffor<T> {
	private Object[] buffor;
	private int deafultSize=10;
	private int begin;
	private int end;
	
	class Element{
		Element next;
		T value;
		
		Element(T elem){
			value = elem;
		}
		public T getValue() {
			return value;
		}
	}
	
	public CycledBuffor(int size) {
		buffor = new Object[size+1];
		end=size;
		begin=size;
	}
	
	public CycledBuffor() {
		buffor = new Object[deafultSize];
		begin=deafultSize;
		end=deafultSize;
	}
	
	public boolean isEmpty() {
		return begin==end;
	}
	
	public boolean isFull() {
		return begin==(end+1)%buffor.length;
	}
	
	public T dequeue() {
		if(isEmpty()) {
			try {
				throw new EmptyQueueException();
			} catch (EmptyQueueException e) {
				System.out.println("nie mozna pobrac elementu, kolejka Cycled Buffor jest pusta");
//				e.printStackTrace();
			}
		}
		else {
		@SuppressWarnings("unchecked")
		Element x = (Element) buffor[begin++];
		T retValue=x.getValue();
		begin%=buffor.length;
		return retValue;
		}
		return null;
	}
	public void enqueue(T val){
		if(isFull()) {
			try {
				throw new FullQueueException();
			} catch (FullQueueException e) {
				System.out.println("nie mozna dodac elementu, kolejka Cycled Buffor jest pelna");
//				e.printStackTrace();
			}
		}
		else {
		buffor[end++]=new Element(val);
		end%=buffor.length;
		}
	}
	
	public int size() {
		return (end+buffor.length-begin);
	}
	
	public void showAll() {
		while(!isEmpty()) {
			System.out.println(dequeue());
		}
		System.out.println();
	}
	
}
