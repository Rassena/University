package OSLab3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	private static  ArrayList<Integer> lista;
	
	private static void test(int sizeOfMemory) {
		
		FIFO fifo= new FIFO(sizeOfMemory,lista);
		OPT opt= new OPT(sizeOfMemory,lista);
		RAND rand= new RAND(sizeOfMemory,lista);
		LRU lru= new LRU(sizeOfMemory,lista);
		ALRU alru=new ALRU(sizeOfMemory,lista);	
		
		System.out.println("Ramek: " + sizeOfMemory);
		fifo.run();
		opt.run();
		rand.run();
		lru.run();
		alru.run();
		
		System.out.println();
	}
	
	private static void czytaj() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Wpisz ilosc odwolan do stron: ");
		int ilosc = sc.nextInt();
		System.out.print("Wpisz ilosc stron: ");
		int zakres = sc.nextInt();
		System.out.print("Wpisz ilosc testow: ");
		int repeats = sc.nextInt();
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for(int i=1;i<repeats+1;i++) {
			System.out.print("Wpisz ilosc ramek w pamieci nr. " + i + ": ");
			sizes.add(sc.nextInt());
		}
		sc.close();
		lista(ilosc,zakres);
		
		System.out.println();
		
		for(int i=0;i<repeats;i++) {
			test(sizes.get(i));
		}	
	}
	
	public static void show(){
		for(int i=0;i<lista.size();i++) {
			System.out.print(lista.get(i) + " ");
		}
		System.out.println();
	}
	
	private static void lista(int ilosc, int zakres) {
		Random r = new Random();
		for(int i=0; i<ilosc;i++) {
			lista.add(r.nextInt(zakres)+1);
		}
	}
	
	public static void main(String[] args) {
		lista = new ArrayList<Integer>();

		czytaj();
	}

}
