package OSLab2;

import java.util.ArrayList;

public class FCFS {
	private ArrayList<Blok> ar;
	private double sredniCzas=0;
	private int droga=0;
	private int pozycja=0;
	
	public FCFS(ArrayList<Blok> dysk) {
		ar = dysk;
	}
	
	public void obliczFCFS(){
		for(int i=0;i<ar.size();i++) { 
			while(droga<ar.get(i).momentZgloszenia) 
				droga++;
			if(!ar.get(i).wykonany) {
				if(ar.get(i).pozycja>=pozycja) {
					droga+=ar.get(i).pozycja-pozycja;
				}
				else
					droga+=pozycja-ar.get(i).pozycja;
				ar.get(i).czasOczekiwania=droga-ar.get(i).momentZgloszenia;
				pozycja=ar.get(i).pozycja;
				ar.get(i).wykonany=true;
				ar.get(i).nrWykonania=i;
			}
		}
	}
	
	public void blokiFCFS() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaFCFS();

	}
	
	public void sredniaFCFS() {
		sredniCzas=0;
		for(int i=0;i<ar.size();i++) {
			sredniCzas+=ar.get(i).czasOczekiwania;
			ar.get(i).clear();
		}
		int suma=(int) sredniCzas;
		sredniCzas/=ar.size();
		System.out.println("Sredni czas oczekiwania: "+ sredniCzas + "\t" + "Suma oczekiwania: " + suma + "\t" + "Czas dzialania: "+ droga);
		System.out.println();
	}
	
	private Blok getBlok(int nrWykonania) {
		for(int i=0;i<ar.size();i++) {
			if(nrWykonania==ar.get(i).nrWykonania)
				return ar.get(i);
		}
		return null;
	}
	
}
