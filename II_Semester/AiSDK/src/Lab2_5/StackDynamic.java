package Lab2_5;

import Lab2_4.EmptyQueueException;

public class StackDynamic<T> {
	Object[] stack;
	int number;
	private int head=0;

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
		
		public StackDynamic(int startSize) {
			stack = new Object[startSize];
		}

		public void add(T elem) {
			Element element = new Element(elem);
			stack[head++]=element;			
			if(head>=0.8*stack.length) {
				System.out.println("Stos zostal powiekszony");
				Object[] stack2 = new Object[stack.length*2];
				for(int i=0;i<head;i++) {
					stack2[i]=stack[i];
				}
				stack=stack2;
			}
		}
		
		public T get() {
			if(stack[0]==null)
				try {
					throw new EmptyQueueException();
				} catch (EmptyQueueException e) {
					System.out.println("stos jest pusty.");
//					e.printStackTrace();
				}
			@SuppressWarnings("unchecked")
			Element x = (Element) stack[--head];
			stack[head]=null;
			if(head<0.2*stack.length) {
				System.out.println("Stos zostal pomniejszony");
				Object[] stack2 = new Object[stack.length/2];
				for(int i=0;i<head;i++) {
					stack2[i]=stack[i];
				}
				stack=stack2;
			}
			return x.value;
		}
		
		public void showAll() {
			if(head==0)
				try {
					throw new EmptyQueueException();
				} catch (EmptyQueueException e) {
					System.out.println("Stos jest pusty.");
//					e.printStackTrace();
				}
			while(head!=0)
				System.out.println(get());
			System.out.println();
		}
		
		public void clear() {
			for(int i =0; i<stack.length;i++) {
				stack[i] = null;
			}
		}
	
	
}
