package Lab2_8;

import java.util.Random;


public class Main {
	static int[] unsorted;
	static int[] sorted;
	
	private static void makeTables(int size, int range) {
		int[] tablica = new int[size];
		Random r = new Random();
		for(int i=0;i<size;i++) {
			tablica[i]=r.nextInt(range+1);
		}
		tablice(tablica);
	}
	
	private static void tablice(int[] tablica) {
		BubbleSort bsort = new BubbleSort();
		unsorted = new int[tablica.length];
		sorted = new int[tablica.length];
		
		for(int i =0; i<tablica.length;i++) {
			unsorted[i]=tablica[i];
			sorted[i]=tablica[i];
		}
		sorted = bsort.sort(sorted);
	}
	
/*	private static void show() {
		System.out.println("Wejsciowa:");
		for(int i=0;i<unsorted.length;i++) {
			System.out.print(unsorted[i]+ " ");
		}
		System.out.println();
		System.out.println("Bubblesort" + 
		" Porownania: " + bsort.getCompares() +
		" Zmiany: " + bsort.getChanges());
		for(int i=0;i<sorted.length;i++) {
			System.out.print(sorted[i]+ " ");
		}
		System.out.println();	
	}
*/	
	
	private static void zadanie1(int sizeOfArray, int maxValue,int amountOfRepeats) {

		BinarySearch bs = new BinarySearch();
		LineSearch ls = new LineSearch();
		int size=sizeOfArray;
		int range =maxValue;
		double proby=amountOfRepeats;
		
		makeTables(size,range);
		Random r= new Random();
		
//		show();
		
		for(int i=0;i<proby;i++) {
			int search = r.nextInt(range+1);
			ls.search(unsorted, search);
			bs.search(sorted, search);
		}
		
		double sredniaTrafionych = (double)ls.getPtr()/ls.getTrafione();
		double sredniaChybionych = ls.getPch()/(proby-ls.getTrafione());
		double srednia = (ls.getPch()+ls.getPtr())/proby;
		
		System.out.println("Proby: " + proby);
		
		System.out.println("linowy: ");
		System.out.print("Trafione: " + ls.getTrafione() + " ");
		System.out.print("Srednia: " + srednia + " ");
		System.out.print("Srednia trafionych: " + sredniaTrafionych + " ");
		System.out.println("Srednia chybionych: " + sredniaChybionych + " ");
		
		sredniaTrafionych = (double)bs.getPtr()/bs.getTrafione();
		sredniaChybionych = bs.getPch()/(proby-bs.getTrafione());
		srednia = (bs.getPch()+bs.getPtr())/proby;
		
		System.out.println("Binarny: ");
		System.out.print("Trafione: " + bs.getTrafione() + " ");
		System.out.print("Srednia: " + srednia + " ");
		System.out.print("Srednia trafionych: " + sredniaTrafionych + " ");
		System.out.println("Srednia chybionych: " + sredniaChybionych + " ");
		
	}
	
	private static void zadanie2(int sizeOfArray, int maxValue) {
		
		Heap h = new Heap(sizeOfArray);
		
		Random r = new Random();
		for(int i=0;i<sizeOfArray;i++) {
			h.enqueue(r.nextInt(maxValue));
		}
		
		h.print();
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());
		System.out.println(h.dequeue());

		h.print();
		
		h.changeValue(500, 0);		
		h.print();
	
		h.enqueue(20);
		h.print();
		
		System.out.println(h.getByIndex(5));
		h.print();
	}
		
	public static void main(String[]args) {
		
		int sizeOfArray=20;
		int maxValue=200;
		int amountOfRepeats=100;
		
		zadanie1(sizeOfArray,maxValue,amountOfRepeats);
		System.out.println();
		
		zadanie2(sizeOfArray,maxValue);
		System.out.println();		
	}
	
}
