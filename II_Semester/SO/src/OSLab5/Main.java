package OSLab5;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Wpisz ilosc procesorow: ");
		int procesory = sc.nextInt();
		System.out.print("Wpisz ilosc procesow: ");
		int procesy = sc.nextInt();
		System.out.print("Wpisz minimalne wykorzystanie procesora: ");
		int min = sc.nextInt();
		System.out.print("Wpisz maksymalne wykorzystanie procesora: ");
		int max = sc.nextInt();
		
		sc.close();

		System1 s1 =new System1(procesory, procesy, min, max);
		
		s1.run();
		
		
		
	}
}
