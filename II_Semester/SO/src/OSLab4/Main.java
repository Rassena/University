package OSLab4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	private static  ArrayList<Integer> lista;
	private static ArrayList<ArrayList<Integer>> procesy;
	private static int ramki;
	private static int wielkoscZbioruRoboczego;
	
	private static void test(ArrayList<ArrayList<Integer>> procesy,int ramki, int okres) {
		
		Equal eq = new Equal(procesy, ramki);
		Ration ra = new Ration(procesy, ramki);
		Sector se = new Sector(procesy, ramki, okres);
		
		eq.proceses();
		ra.proceses();
		se.proceses();
		
		eq.show();
		ra.show();
		se.show();
		
/*
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
		*/
		
	}
	
	private static void czytaj() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Wpisz ilosc procesow: ");
		int proceses = sc.nextInt();
		System.out.print("Wpisz ilosc stron: ");
		int zakres = sc.nextInt();
		System.out.print("Wpisz ilosc ramek: ");
		ramki = sc.nextInt();
		System.out.print("Wpisz rozmiar zbioru roboczego: ");
		wielkoscZbioruRoboczego = sc.nextInt();
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		Random r= new Random();
		int odwolania;
		int wszystkichOdwolan=0;
		for(int i=0;i<proceses;i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			System.out.print("Wpisz ilosc odwolan do stron w procesie nr. " + i + ": ");
			odwolania = sc.nextInt();
			sizes.add(odwolania);
			wszystkichOdwolan+=odwolania;
			for(int j=0;j<odwolania;j++) {
				temp.add(r.nextInt(zakres)+1);	
			}
			procesy.add(temp);

			
			
		}
		
		sc.close();
		
		
		/*
		for(int i=0;i<procesy.size();i++) {
			System.out.println(procesy.get(i));
			for(int j=0;j<procesy.get(i).size();j++) {
				System.out.print(procesy.get(i).get(j));
			}
			System.out.println();
		}
		*/

			

	}
	
	public static void show(){
		for(int i=0;i<lista.size();i++) {
			System.out.print(lista.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void procesy(int ilosc, ArrayList<Integer> sizes, int zakres) {
		Random r= new Random();
		for(int i=0;i<ilosc;i++) {
			System.out.print("Wpisz ilosc odwolan do stron w procesie nr. " + i + ": ");
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int j=0;j<ilosc;j++) {
				temp.add(r.nextInt(zakres)+1);
			}
			procesy.add(temp);
		}
	}
	
	public static void main(String[] args) {
		lista = new ArrayList<Integer>();
		procesy = new ArrayList<ArrayList<Integer>>();

		czytaj();
	//	show();
		test(procesy,ramki,wielkoscZbioruRoboczego);
	}

}
