package FirstSemestr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DoTabBr {
	static BufferedReader br;
	static int [][]tab;

	public DoTabBr(int w, int k) {
		tab=new int[w][k];
	}
	
	public void wpisz() throws IOException {

		for(int i=0; i<tab.length;i++) {
			System.out.println("podaj liczby w " + i +". wierszu");
			br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer wers = new StringTokenizer(br.readLine());
			for(int n=0; n<tab[i].length;n++) {
				tab[i][n]=Integer.parseInt(wers.nextToken());
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

	public static void main(String[]args) throws IOException {
		System.out.println("podaj liczbe wierszy i kolumn");
		br = new BufferedReader(new InputStreamReader(System.in));
		String wiersz = br.readLine();
		StringTokenizer wers = new StringTokenizer(wiersz);
		int w =Integer.parseInt(wers.nextToken());
		int k=Integer.parseInt(wers.nextToken());
		DoTabBr tab=new DoTabBr(w,k);
		tab.wpisz();
		tab.wyswietl();
		System.out.println(tab.srednia());
		System.out.println("Tablica Transponowana: \n");
		tab.transponowanie();
	}
}