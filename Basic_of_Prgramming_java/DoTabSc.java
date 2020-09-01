package FirstSemestr;
import java.util.Scanner;

public class DoTabSc {
	static Scanner sc;
	static int [][]tab;
	
	public DoTabSc(int w, int k) {
		tab=new int[w][k];
	}
	
	public void wpisz() {
		for(int i=0; i<tab.length;i++) {
			System.out.println("podaj liczby w " + i +". wierszu");
			sc = new Scanner(System.in);
			for(int n=0; n<tab[i].length;n++) {
				tab[i][n]= sc.nextInt();
			}
		}      
	}
	
	public void wyswietl() {
		System.out.println("Twoja tablica: " + "\n");
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j<tab[i].length; j++)
				System.out.print(tab[i][j] + "\t");
		System.out.println();
		}
	}
	
	public  double srednia() {
		double suma=0;
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j<tab[i].length; j++)
				suma+=tab[i][j];
		}
		double ilosc= tab.length* tab[0].length;
		return suma/ilosc;
	}

	public void transponowanie() {
		int i=0;
		for(int j=0; j<tab[i].length; j++,i=0) {
			for(i=0; i<tab.length; i++)
				System.out.print(tab[i][j] + "\t");
		System.out.println();
		}
	}

	public static void main(String[]args) {
		System.out.println("podaj liczbe wierszy i kolumn");
		sc = new Scanner(System.in);
		int w= sc.nextInt();
		int k= sc.nextInt();
		DoTabSc tab=new DoTabSc(w,k);
		tab.wpisz();
		tab.wyswietl();
		System.out.println("Srednia wszystkich elementow: " +tab.srednia());
		System.out.println(tab.srednia());
		System.out.println("Tablica Transponowana: \n");
		tab.transponowanie();
	}

}
