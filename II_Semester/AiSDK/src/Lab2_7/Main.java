package Lab2_7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
	static int[] unsorted;
	static int[] sortedq;
	static int[] sortedm;
	static QuickSort qs;
	static MergeSort ms;
	static BufferedWriter bw;
	
	private static void makeTable(int size, int range) {
		int[] tablica = new int[size];
		Random r = new Random();
		for(int i=0;i<size;i++) {
			tablica[i]=r.nextInt(range+1);
		}
		tablice(tablica);
	}
	
	private static void clear() {
		qs.clear();
		ms.clear();
	}
	
	private static void tablice(int[] tablica) {
		unsorted = new int[tablica.length];
		sortedq = new int[tablica.length];
		sortedm = new int[tablica.length];
		
		for(int i =0; i<tablica.length;i++) {
			unsorted[i]=tablica[i];
			sortedq[i]=tablica[i];
			sortedm[i]=tablica[i];
		}
		sortedq = qs.sort(sortedq,0,sortedq.length-1);
		sortedm = ms.sort(sortedm,0,sortedm.length-1);
		show();
	}
	
	private static void save(int number) throws IOException {
		bw.write(number+";"+qs.getCompares()+";"+qs.getChanges()+";"+
				ms.getCompares()+";"+ms.getChanges());
		bw.newLine();
	}
	
	private static void show() {
		System.out.println("Wejsciowa:");
		for(int i=0;i<unsorted.length;i++) {
			System.out.print(unsorted[i]+ " ");
		}
		System.out.println();
		System.out.println("Quicksort" + 
		" Porownania: " + qs.getCompares() +
		" Zmiany: " + qs.getChanges());
		for(int i=0;i<sortedq.length;i++) {
			System.out.print(sortedq[i]+ " ");
		}
		System.out.println();
		System.out.println("Mergesort" + 
		" Porownania: " + ms.getCompares() +
		" Zmiany: " + ms.getChanges());
		for(int i=0;i<sortedm.length;i++) {
			System.out.print(sortedm[i]+ " ");
		}
		System.out.println();	
	}
	
	public static void main(String[]args) throws IOException {
		qs = new QuickSort();
		ms = new MergeSort();
		bw = new BufferedWriter(new FileWriter("/Volumes/NO NAME/Eclipse/AiSDK/src/Documents/Sorts"));
		
		int[] przyklad1 = {3,2,8,5,9,7,1,0,4,6};
		int[] przyklad2 = {9,8,7,6,5,4,3,2,1,0};
		int[] przyklad3 = {0,1,2,3,4,5,6,7,8,9};
	
		tablice(przyklad1);
		clear();
		tablice(przyklad2);
		clear();
		tablice(przyklad3);
		clear();

		bw.write("nr;QickSort Compares;QuickSort Changes;"+
				"MergeSort Compares;MergeSort Changes");
		bw.newLine();
		for(int i=0;i<100;i++) {
			makeTable(100,10);
			save(i);
			clear();
		}
		bw.close();	
	}
}
