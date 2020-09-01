package Lab11;
import java.io.BufferedReader;
import java.util.Random;
//import java.util.Scanner;

public class TabBr {
	static BufferedReader br;
	static int tab[][];
	
	public TabBr(int w, int k) {
		tab=new int[w][k];
	}
	
	public void wypelnij(int zakres) {
		Random r=new Random();
		for(int i=0; i<tab.length; i++)
			for(int j=0; j<tab[i].length;j++) {
				tab[i][j]=r.nextInt(zakres);
			}
	}

	
	
	
	
/*	public static void main(String[]args) {
		System.out.println("podaj liczbe wierszy i kolumn");
		sc = new Scanner(System.in);
		int w= sc.nextInt();
		int k= sc.nextInt();
		TabSc tab=new TabSc(w,k);
		sc= new Scanner(System.in);
		tab.wypelnij(sc.nextInt());
		
		
	}
	*/
}
