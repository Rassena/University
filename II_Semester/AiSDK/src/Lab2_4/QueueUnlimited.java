package Lab2_4;

public class QueueUnlimited<T> {
	Element first = null;
	
	class Element{
		Element next;
		T value;
		
		Element(T elem){
			value = elem;
		}
	}
	
	public void add(T elem) {
		Element element = new Element(elem);
		Element current = first;
		if(first==null) {
			first = element;
		}
		else {
			while(current.next!=null) {
				current = current.next;
			}
			current.next = element;
		}
	}

	public T get() throws EmptyQueueException {
		Element current = first;
		if(first==null)
			throw new EmptyQueueException();
		else {
			first=first.next;
			return current.value;
		}

	}
	
	public void ShowAll() throws EmptyQueueException {
		while(first!=null)
			System.out.println(get());
	}

}
