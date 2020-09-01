package Cwiczenia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Collatz {
	
	static BufferedReader br;
	static InputStreamReader isr;
	
	public static void czytanie() {
		int kroki= 0;
		double liczba =1;
		double wartosc =1;
		boolean koniec =false;
		String wiersz = null;
		double najwiecej= 1;
		int najwyzsza=1;
			while(!koniec) {
				br=new BufferedReader (new InputStreamReader(System.in));
				try {
					wiersz = br.readLine();
					wartosc = Double.parseDouble(wiersz);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!wiersz.equals("END")) {
					for(int i= 1 ;i <=wartosc;i++) {
						liczba=i;
						while (liczba !=1.0) {
							if((liczba%2)==0) {
								liczba=liczba/2;
								}
							else {
								liczba=liczba*3 +1;	
								
							}
							kroki++;
							//System.out.println((int) liczba);
							
						}
					System.out.println(i + " ma kroki: "  + kroki);
					if(kroki>najwiecej) {
						najwyzsza =i;
						najwiecej = kroki;
					}
					kroki =0;
				}
					System.out.println("Najwyzsza: " + najwyzsza + " kroki: " + najwiecej);
				}		
				else
					koniec =true;
			}
		} 

	
	public static void main(String[]args) {
		System.out.println("Wpisz liczbe do ktorej liczyc :" );
		czytanie();
	}

}
