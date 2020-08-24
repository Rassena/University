package FirstSemestr;
import java.util.Random;

public class Tablica2{

	int [][]tab2;
	
	public Tablica2(int w, int k) {
		tab2=new int[w][k];
	}
	
	public void wypelnij2(int zakres) {
		Random r=new Random();
		for(int i=0; i<tab2.length; i++)
			for(int j=0; j<tab2[i].length;j++) {
				tab2[i][j]=r.nextInt(zakres);
			}
	}
	
	public void wyswietl2WxK() {
		for(int i=0; i<tab2.length; i++) {
			for(int j=0; j<tab2[i].length; j++)
				System.out.print(tab2[i][j] + " ");
		System.out.println();
		}
	}
	
	public void wyswietl2KxW() {
		int i=0;
		for(int j=0; j<tab2[i].length; j++,i=0) {
			for(i=0; i<tab2.length; i++)
				System.out.print(tab2[i][j] + " ");
		System.out.println();
		}
	}
	
	public int suma2() {
		int suma=0;
		for(int i=0; i<tab2.length; i++)
			for(int j=0; j<tab2[i].length; j++)
				suma+= tab2[i][j];
		return suma;
	}
	
	public String max2() {
		int x= tab2[0][0];
		int wiersz=0;
		int kolumna=0;
		for(int i=0; i<tab2.length;i++)
			for(int j=0;j<tab2[i].length;j++) {
				if(x<tab2[i][j]) {
					x=tab2[i][j];
					wiersz=i;
					kolumna=j;
				}
			}
		return "Max " +x +" wiersz " + wiersz + " kolumna " + kolumna;
	}
	
	public int max2K() {
		int x= tab2[0][0];
		int kolumna=0;
		for(int i=0; i<tab2.length;i++)
			for(int j=0;j<tab2[i].length;j++) {
				if(x<tab2[i][j]) {
					x=tab2[i][j];
					kolumna=j;
				}
			}
		return kolumna;
	}
	
	public int max2W() {
		int x= tab2[0][0];
		int wiersz=0;
		for(int i=0; i<tab2.length;i++)
			for(int j=0;j<tab2[i].length;j++) {
				if(x<tab2[i][j]) {
					x=tab2[i][j];
					wiersz=i;
				}
			}
		return wiersz;
	}
	
	public String min2() {
		int x= tab2[0][0];
		int wiersz=0;
		int kolumna=0;
		for(int i=0; i<tab2.length;i++)
			for(int j=0;j<tab2[i].length;j++) {
				if(x>tab2[i][j]) {
					x=tab2[i][j];
					wiersz=i;
					kolumna=j;
				}
			}
		return "Min " +x +" wiersz " + wiersz + " kolumna " + kolumna;
	}
	
	public void szukaj2(int x) {
		int z=0;
		for(int i=0;i<tab2.length;i++) {
			for(int j=0;j<tab2[i].length;j++)
			if(tab2[i][j]==x) {
				System.out.println("Szukana "+ x + " w wiersz " + i + " kolumna " + j);
				z++;
			}
		}
		if(z==0)
			System.out.println("Szukana "+ x + " w wiersz -1 kolumna -1");
	}
	
	public int[] sumaK() {
		int [] tab1=new int [tab2.length];
		int i=0;
		for(int j=0; j<tab2[i].length; j++,i=0) {
			for(i=0; i<tab2.length; i++)
				tab1[j]+= tab2[i][j];
			System.out.print(tab1[j] + " ");
		}
		System.out.println();
		return  tab1;
	}
	
	public String maxK(int[] tab){
		int x= tab[0];
		int kolumna=0;
		for(int i=0;i<tab.length;i++) {
			if(x<tab[i]) {
				x=tab[i];
				kolumna=i;
			}
		}
		return "Max " +x +" kolumna " + kolumna;

	}
	
	public void usun2Max() {
		int [][] tabz= new int[tab2.length-1][tab2[0].length-1];
		for(int i=0, w=0; i<max2W();i++, w++) {
			for(int j=0,k=0; j<max2K();j++,k++) {
				tabz[w][k]=tab2[i][j];
			}
			for(int j=max2K()+1,k=max2K(); j<tab2[i].length;j++,k++) {
				tabz[w][k]=tab2[i][j];
				}
		}
		for(int i=max2W()+1, w=max2W(); i<tab2.length;i++, w++) {
			for(int j=0,k=0; j<max2K();j++,k++) {
				tabz[w][k]=tab2[i][j];
			}
			for(int j=max2K()+1,k=max2K(); j<tab2[i].length;j++,k++) {
				tabz[w][k]=tab2[i][j];
				}
		}
		
		
		
		
		tab2 = tabz;
	}
	
	
	
	public static void main(String[]args) {
		Tablica2 tab2= new Tablica2(5,3);
		tab2.wypelnij2(10);
		System.out.println("Tablica wiersz x kolumna:");
		tab2.wyswietl2WxK();
		System.out.println();
		System.out.println("Tablica kolumna x wiersz:");
		tab2.wyswietl2KxW();
		System.out.println();
		System.out.println("Suma elementów: " + tab2.suma2());
		System.out.println(tab2.min2());
		System.out.println(tab2.max2());
		tab2.szukaj2(4);
		System.out.println();
		System.out.println("Suma w kolumnach to: ");
		System.out.println(tab2.maxK(tab2.sumaK()));
		System.out.println();
		System.out.println("Tablica po usunieciu: "); 
		tab2.usun2Max();
		tab2.wyswietl2WxK();
		
	}
}
