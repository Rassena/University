package FirstSemestr;
 import java.util.Random;
public class Tablica {

	int []tab;
	
	public Tablica(int rozm) {
		tab=new int[rozm];
	}
	
	public void wypelnij(int zakres) {
		Random r=new Random();
		for(int i=0; i<tab.length; i++)
			tab[i]=r.nextInt(zakres);
	}
	
	public void wyswietl() {
		for(int i=0;i<tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println();
	}
	
	public void odwroc() {
		int z=tab.length - 1;
		for(int i=0;i<z;i++) {
			int c= tab[z];
			tab[z] = tab[i];
			tab[i] = c;
			z--;
		}
	}
	
	public int szukaj(int w) {
		int index=-1;
		for(int i=0;i<tab.length && index==-1;i++) {
			if(tab[i]==w)
				index = i;
		}
		return index;
	}
	
	public String max() {
		int x= tab[0];
		int index=0;
		for(int i=0; i<tab.length;i++)
			if(x<tab[i]) {
				x=tab[i];
				index=i;
			}
		return "Max " +x +" index " + index;
	}
		
	public String min() {
			int x= tab[0];
			int index=0;
			for(int i=0; i<tab.length;i++)
				if(x>tab[i]) {
					x=tab[i];
					index=i;
				}
			return "Min " +x +" index " + index;
		}
		
	public double srednia() {
			double x=0;
			for(int i=0;i<tab.length;i++){
				x=x+tab[i];
			}
			return x*1.0/tab.length*1.0;
		}
		
	public double odchylenie() {
			double x= srednia();
			double suma=0;
			for(int i = 0; i<tab.length; i++) {
				suma= suma+ (tab[i]- x)*(tab[i]-x);
			}
			return Math.sqrt(suma/tab.length);
		}
		
	public boolean czyRozna() {
			boolean rozna= true;
			int i = 0;
			int j=0;
			while(i<tab.length-1 &&rozna==true) {
				j = i + 1;
				for(;j<tab.length;j++) {
					if(tab[i]==tab[j])
						rozna = false;
				}
				i++;
			}
			return rozna;
		}
	
	public int powtorzenie() {
		boolean rozna= true;
		int i = 0;
		int index = -1;
		int w =-1;
		while(i<tab.length-1 &&rozna==true) {
			index = i + 1;
			for(;index<tab.length &&rozna==true ;index++) {
				if(tab[i]==tab[index]) {
					rozna = false;
					w=tab[i];
				}
			}
			i++;
		}
		return w;
	}
	
	public void usun(int w) {
		int[] tabz = new int[tab.length -1];
		if(szukaj(w)!=-1) {
			for(int i=0, j = 0;i<szukaj(w);i++,j++) {
				tabz[j]=tab[i];
			}
			for(int i=szukaj(w) + 1, j = szukaj(w) ;i<tab.length;i++,j++) {
				tabz[j]=tab[i];
			}
		}
		tab = tabz;
	}

	public void doRozna() {
			while(czyRozna()==false) {
				int x= powtorzenie();
				usun(x);
			}
		}
	
	public static void main(String[]args) {
		Tablica tab1=new Tablica(10);
		tab1.wypelnij(20);
		tab1.wyswietl();
		System.out.println("1. Powtarza sie " + tab1.powtorzenie());
		System.out.println("Szukana jest w " + tab1.szukaj(4));
		System.out.println(tab1.max());
		System.out.println(tab1.min());
		tab1.odwroc();
		System.out.print("Odwrocona ");
		tab1.wyswietl();
		tab1.odwroc();
		System.out.println("Srednia " + tab1.srednia());
		System.out.println("Odchylenie standardowe " + tab1.odchylenie());
		System.out.println("Czy rozna " + tab1.czyRozna());
		tab1.wyswietl();
		System.out.print("Nowa: " );
		tab1.doRozna();
		tab1.wyswietl();
		System.out.println("Czy rozna " + tab1.czyRozna());
	}
}
