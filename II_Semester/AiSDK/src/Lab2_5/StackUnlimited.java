package Lab2_5;

import Lab2_4.*;

public class StackUnlimited<T> {

	Element head = null;
	
	class Element{
		Element next;
		T value;
		
		Element(T elem){
			value = elem;
		}
	}
	
	public boolean isEmpty() {
		return head==null;
	}
	
	public void add(T elem) {
		Element element = new Element(elem);
		if(head==null) {
			head = element;
		}
		else {
			element.next = head;
			head=element;
		}
	}

	public T get() {
		Element current = head;
		if(head==null)
			try {
				throw new EmptyQueueException();
			} catch (EmptyQueueException e) {
				System.out.println("Stos jest pusty");
				return null;
				//e.printStackTrace();
			}
		else {
			head=head.next;
			return current.value;
		}
	}
	
	public void ShowAll() throws EmptyQueueException {
		while(head!=null)
			System.out.println(get());
	}

}
