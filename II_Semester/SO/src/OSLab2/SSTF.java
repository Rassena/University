package OSLab2;

import java.util.ArrayList;

public class SSTF {
	private ArrayList<Blok> ar;
	private double sredniCzas=0;
	private int droga=0;
	private int pozycja=0;
	private int rozmiarDysku;
	
	public SSTF(ArrayList<Blok> dysk, int rozm) {
		ar = dysk;
		rozmiarDysku=rozm;
	}
	
	public void obliczSSTF() {
		for(int i=0;i<ar.size();i++) {
			while(najblizszy()==-1)
				droga++;
			int index = najblizszy();
			ar.get(index).nrWykonania=i;
			if(pozycja<=ar.get(index).pozycja)
				droga+=ar.get(index).pozycja-pozycja;
			else
				droga+=pozycja-ar.get(index).pozycja;
			ar.get(index).czasOczekiwania=droga-ar.get(index).momentZgloszenia;
			pozycja=ar.get(index).pozycja;
			ar.get(index).wykonany=true;
		}
		
	}
	
	public void blokiSSTF() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaSSTF();
	}
	
	public void sredniaSSTF() {
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
	
	private int najblizszy() {
		int najblizej=rozmiarDysku;
		int odleglosc;
		int index=-1;
		for(int i =0;i<ar.size();i++) {
			if(!ar.get(i).wykonany&&ar.get(i).momentZgloszenia<=droga) {
				if(pozycja<=ar.get(i).pozycja)
					odleglosc=ar.get(i).pozycja-pozycja;
				else
					odleglosc=pozycja-ar.get(i).pozycja;
				if(odleglosc<=najblizej) {
					index=i;
					najblizej=odleglosc;
				}
			}
		}
		return index;
	}
	
	
}
