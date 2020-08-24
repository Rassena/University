package FirstSemestr;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Macierz2 {
	static BufferedReader br;
	static FileReader fr;
	static double[][] tab;
	private BufferedReader br2;
	
	public Macierz2(int w, int k) {
		tab=new double[w][k];
	}
	
	public void wypelnij2() throws IOException{
		br2 = new BufferedReader(new FileReader("Macierz.txt"));
			br2.readLine();
			br2.readLine();
			br2.readLine();
		for(int i=0;i<tab.length;i++) {
			String s= br2.readLine();
			StringTokenizer wers = new StringTokenizer(s);
			for(int j=0;j<tab[i].length;j++) {
		//		String[] liczba= s.split(" ");
				double d = Double.parseDouble(wers.nextToken().replace(",", "."));
				tab[i][j]= d;
				System.out.print(tab[i][j] + "\t");
			}
			System.out.println();
		}
		br2.close();
	}
	
	public void wyswietl2() {
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j<tab[i].length; j++)
				System.out.print(tab[i][j] + "\t");
		System.out.println();
		}
	}
	

	
	public String max2() {
		double x= tab[0][0];
		int wiersz=0;
		int kolumna=0;
		for(int i=0; i<tab.length;i++)
			for(int j=0;j<tab[i].length;j++) {
				if(x<tab[i][j]) {
					x=tab[i][j];
					wiersz=i;
					kolumna=j;
				}
			}
		return "Max " +x +" wiersz " + wiersz + " kolumna " + kolumna;
	}
	
	
	
	public static void main(String[]args) throws IOException {
		BufferedReader br= new BufferedReader(new FileReader("Macierz.txt"));
		String s= null;
		s= br.readLine();
		System.out.println(s);
		System.out.println(s=br.readLine());
		StringTokenizer wiersze = new StringTokenizer(s);
		wiersze.nextToken();
		wiersze.nextToken();
		int w=Integer.parseInt(wiersze.nextToken());
		System.out.println(s=br.readLine());
		StringTokenizer kolumny = new StringTokenizer(s);
		kolumny.nextToken();
		kolumny.nextToken();
		int k=Integer.parseInt(kolumny.nextToken());
		Macierz2 tab=new Macierz2(w,k);
		br.close();
		tab.wypelnij2();
		System.out.println();
		System.out.println(tab.max2());
	}
}
