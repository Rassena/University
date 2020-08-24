package FirstSemestr;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class Macierz {
	static BufferedReader br;
	static String tab[][];
	
	
	public Macierz(int w, int k) {
		tab=new String[w][k];
	}
	
	public void wypelnij(int zakres) {
		Random r=new Random();
		for(int i=0; i<tab.length; i++)
			for(int j=0; j<tab[i].length;j++) {
				double x=r.nextDouble()*r.nextInt(zakres);
				String strDouble = String.format("%.2f", x);
				tab[i][j]=strDouble;
			}
	}

	public void zapis() throws FileNotFoundException {
		Locale.setDefault(Locale.GERMANY);
		PrintWriter zapis = new PrintWriter("Macierz.txt");
		int w =  tab.length;
		int k = tab[0].length;
		zapis.println("Macierz");
		zapis.println("Liczba wierszy: " + w);
		zapis.println("Liczba kolumn: " + k);
		for(int i=0; i<w;i++) {
			for(int j=0; j<tab[i].length;j++) {
				zapis.print(tab[i][j]+ "\t");
			}
			zapis.println();
		}
		zapis.close();
	}
	
	
	

	
	public void wyswietl() {
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j<tab[i].length; j++)
				System.out.print(tab[i][j] + "\t");
		System.out.println();
		}
	}
	
	public static void main(String[]args) throws IOException {
		System.out.println("Macierz");
		System.out.print("liczba wierszy: ");
		br = new BufferedReader(new InputStreamReader(System.in));

		String wiersz = br.readLine();
		System.out.println();
		System.out.print("liczba kolumn: ");
		wiersz+=" " +  br.readLine();
		System.out.println();
		StringTokenizer wers = new StringTokenizer(wiersz);
		int w =Integer.parseInt(wers.nextToken());
		int k=Integer.parseInt(wers.nextToken());
		Macierz tab=new Macierz(w,k);
		System.out.print("zakres wartosci: ");
		wiersz = br.readLine();
		System.out.println();
		wers = new StringTokenizer(wiersz);
		int zakres =Integer.parseInt(wers.nextToken());
		tab.wypelnij(zakres);
		tab.wyswietl();
		tab.zapis();
	}
	
}
