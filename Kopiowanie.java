package FirstSemestr;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Kopiowanie {
	static BufferedReader br;
	static FileReader fr;
	static BufferedWriter bw;
	static int liczbaZnakow;
	static int liczbaCzarnych;
	static int liczbaWyrazow;
	static int liczbaWierszy;
	static int najdluzszyWiersz;
	static int najdluzszyWyraz;
	static int ile;
	
	public static int getliczbaZnakow() {
		return liczbaZnakow;
	}
	
	public static int getliczbaCzarnych() {
		return liczbaCzarnych;
	}
	
	public static int getliczbaWyrazow() {
		return liczbaWyrazow;
	}
	
	public static int getliczbaWierszy() {
		return liczbaWierszy;
	}
	
	public static int getnajdluzszyWiersz() {
		return najdluzszyWiersz;
	}
	public static int getnajdluzszyWyraz() {
		return najdluzszyWyraz;
	}
	
	public static int getile() {
		return ile;
	}
	
	public static void kopiowanie(String plik, String szukana) throws IOException {
		br= new BufferedReader(new FileReader(plik));
		PrintWriter zapis = new PrintWriter("Kopia"+plik+".txt");
		String line;
		while((line= br.readLine())!=null) {
			zapis.println(line);
			liczbaZnakow+= line.length();
			if(najdluzszyWiersz< line.length())
				najdluzszyWiersz= line.length();
			StringTokenizer wers = new StringTokenizer(line);
			liczbaWyrazow+= wers.countTokens();
			String slowo=null;
			while(wers.hasMoreTokens()) {
				slowo=wers.nextToken();
				if(slowo.equalsIgnoreCase(szukana))
					ile++;
				if(slowo.length()>najdluzszyWyraz)
					najdluzszyWyraz=slowo.length();
				liczbaCzarnych+=slowo.length();
			}
			liczbaWierszy++;
				
		}
		zapis.close();
	}
	
	
	
	public static void main(String[]args) throws IOException {
		System.out.print("Ktory plik mam kopiowac: ");
		br = new BufferedReader(new InputStreamReader(System.in));
		String plik= br.readLine();
		System.out.println();
		System.out.print("Co mam liczyc: ");
		br = new BufferedReader(new InputStreamReader(System.in));
		String szukaj= br.readLine();
		System.out.println();
		kopiowanie(plik,szukaj);
		System.out.println("Wszystkich znakow: "+ getliczbaZnakow());
		System.out.println("Znakow Czarnych: "+ getliczbaCzarnych());
		System.out.println("Wyrazow: "+ getliczbaWyrazow());
		System.out.println("Wierszy: "+ getliczbaWierszy());
		System.out.println("Dlugosc najdluzszego wyrazu: "+ getnajdluzszyWyraz());
		System.out.println("Dlugosc najdluzszego wiersza: "+ getnajdluzszyWiersz());
		System.out.println("Szukanych " + szukaj + " jest: "+ getile());
	}

}
