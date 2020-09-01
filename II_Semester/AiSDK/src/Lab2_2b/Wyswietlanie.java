package Lab2_2b;

import java.util.Scanner;

public class Wyswietlanie {
	
	int m;
	int n;
	LIterator<Integer> li;
	LPierwsza lp;
	FIterator<Integer> fit;
	
	public Wyswietlanie(int bottom, int upper) {
		li = new LIterator<Integer>(bottom, upper);
		lp = new LPierwsza();
		fit = new FIterator<Integer>(li, lp);
		m=bottom;
		n=upper;
	}
	
	public void Wyswietl() {
		System.out.println("Liczby pierwsze z przedzialu [" + m + " , " + n + "]:");
		while(!fit.isDone()) {
			fit.next();
		}
	}
	
	public static void main(String[]args) {
		
		System.out.print("Wpisz dolna granica: ");
		Scanner sc= new Scanner(System.in);
		int m = sc.nextInt();
		System.out.println();
		System.out.print("Wpisz gorna granica: ");
		int n = sc.nextInt();
		System.out.println();
		sc.close();
		Wyswietlanie w = new Wyswietlanie(m,n);
		w.Wyswietl();
	}
}
