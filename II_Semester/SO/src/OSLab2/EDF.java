package OSLab2;

import java.util.ArrayList;

public class EDF {

	private ArrayList<Blok> ar;
	private double sredniCzas=0;
	private double sredniaPriorytetow=0;
	private int droga=0;
	private int pozycja=0;
	private int zrobione;
	private int priorytet=-1;
	private int rozmiarDysku;
	
	public EDF(ArrayList<Blok> dysk, int diskSize) {
		ar = dysk;
		rozmiarDysku=diskSize;
	}
	
	public void obliczEDF() {
		for(int i=0;i<ar.size();i++) {
			while(droga<ar.get(i).momentZgloszenia)
				droga++;
			while(jestPriorytet()) {
				if(ar.get(priorytet).pozycja>=pozycja) {
					droga+=ar.get(priorytet).pozycja-pozycja;
				}
				else
					droga+=pozycja-ar.get(priorytet).pozycja;
				ar.get(priorytet).czasOczekiwania=droga-ar.get(priorytet).momentZgloszenia;
				pozycja=ar.get(priorytet).pozycja;
				ar.get(priorytet).wykonany=true;
				ar.get(priorytet).nrWykonania=zrobione;
				zrobione++;
			}
			if(!ar.get(i).wykonany) {
				if(ar.get(i).pozycja>=pozycja) {
					droga+=ar.get(i).pozycja-pozycja;
				}
				else
					droga+=pozycja-ar.get(i).pozycja;
				ar.get(i).czasOczekiwania=droga-ar.get(i).momentZgloszenia;
				pozycja=ar.get(i).pozycja;
				ar.get(i).wykonany=true;
				ar.get(i).nrWykonania=zrobione;
				zrobione++;
			}
		}
	}
	
	public void obliczEDFSSTF() {
		int index;
		for(int i=0;i<ar.size();i++) {
			while(najblizszy()==-1)
				droga++;
			if(jestPriorytet()) {	
				index = najblizszyPriorytet();
			}
			else 
				index = najblizszy();
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
	
	private int najblizszyPriorytet() {
		int najblizej=rozmiarDysku;
		int odleglosc;
		int index=-1;
		for(int i =0;i<ar.size();i++) {
			if(ar.get(i).priorytet&&!ar.get(i).wykonany&&ar.get(i).momentZgloszenia<=droga) {
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

	private boolean jestPriorytet() {
		for(int i=0;i<ar.size();i++) {
			if(!ar.get(i).wykonany&&ar.get(i).priorytet&&ar.get(i).momentZgloszenia<=droga) {
				priorytet=i;
				return true;
			}
		}
		priorytet=-1;
		return false;
	}
		
	public void blokiEDF() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaEDF();

	}
	
	public void sredniaEDF() {
		int prio=0;
		sredniCzas=0;
		for(int i=0;i<ar.size();i++) {
			if(ar.get(i).priorytet) {
				prio++;
				sredniaPriorytetow+=ar.get(i).czasOczekiwania;
			}
			sredniCzas+=ar.get(i).czasOczekiwania;
			ar.get(i).clear();
		}
		int suma=(int) sredniCzas;
		sredniaPriorytetow/=prio;
		sredniCzas/=ar.size();
		System.out.println("Sredni czas oczekiwania: "+ sredniCzas +
				" Suma oczekiwania: " + suma +
				" Czas dzialania: "+ droga  + 
				" Sredni czas oczekiwania priorytetu: " + sredniaPriorytetow);
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
