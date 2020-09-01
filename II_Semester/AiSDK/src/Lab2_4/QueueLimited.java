package Lab2_4;


public class QueueLimited<T> {
Object[] queue;
int number;

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
	
	public QueueLimited(int size) {
		queue = new Object[size];
	}

	public void add(T elem) {
		Element element = new Element(elem);
		boolean added = false;
		for(int i = 0 ; i<queue.length&&!added;i++) {
			if (queue[i]==null) {
				queue[i] = element;
				added = true;
			}
		}
		if(!added)
			try {
				throw new FullQueueException();
			} catch (FullQueueException e) {
//				System.out.println("Nie mozna dodac elementu, pelna kolejka ograniczona.");
				e.printStackTrace();
			}			
	}
	
	public T get() {
		if(queue[0]==null)
			try {
				throw new EmptyQueueException();
			} catch (EmptyQueueException e) {
				System.out.println("Kolejka jest pusta.");
//				e.printStackTrace();
			}
		@SuppressWarnings("unchecked")
		Element x = (Element) queue[0];
		for(int i =1;i<queue.length&&queue[i]!=null;i++) {
			queue[i-1]=queue[i];
		}
		return x.value;
	}
	
	public void showAll() {
		if(queue[0]==null)
			try {
				throw new EmptyQueueException();
			} catch (EmptyQueueException e) {
				System.out.println("Kolejka jest pusta.");
//				e.printStackTrace();
			}
		for(int i= 0; i<queue.length&&queue[0]!=null;i++) {
			System.out.println(get());
		}
		System.out.println();
	}
	
	public void clear() {
		for(int i =0; i<queue.length;i++) {
			queue[i] = null;
		}
	}
	
}
