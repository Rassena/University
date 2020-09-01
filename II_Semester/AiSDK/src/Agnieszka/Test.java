package Agnieszka;

public class Test{
	
	Worker[] tab;
	
	public Test() {
		tab = new Worker[6];
		Worker w1 = new Worker(11, "Josh", 8.0);
		Worker w2 = new Worker(21, "Anna", 7.0);
		Worker w3 = new Worker(14, "Michael", 9.5);
		Worker w4 = new Worker(40, "Tom", 12.0);
		Worker w5 = new Worker(87, "Sasha", 4.5);
		Worker w6 = new Worker(53, "Nikola", 6.5);
		
		tab[0] = w1;
		tab[1] = w2;
		tab[2] = w3;
		tab[3] = w4;
		tab[4] = w5;
		tab[5] = w6;
	}
	
	public void showAll() {
		Iterator<Worker> ai = new ArrayIterator<Worker>(tab);
		ai.first();
		while(!ai.hasDone()) {
			System.out.println(ai.current());
			ai.next();
		}
	}
	
	public void showAllReverse() {
		Iterator<Worker> ai = new ArrayIterator<Worker>(tab);
		ai.last();
		while(!ai.hasDone()) {
			System.out.println(ai.current());
			ai.previous();
		}
	}
	
	public void showTab() {
		for(int i =0; i<tab.length;i++)
			System.out.println(tab[i]);
	}
	
	
	public static void main(String[]args) {
		Test t1 = new Test();
		t1.showAll();
		System.out.println();
		t1.showAllReverse();
	}
	
	
}
