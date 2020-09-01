package OSLab2;

import java.util.ArrayList;

public class Scan {
	private ArrayList<Blok> ar;
	private double sredniCzas=0;
	private int droga=0;
	private int pozycja=0;
	private int rozmiarDysku;
	private int zmianyKierunku;
	private int przejscia;
	
	public Scan(ArrayList<Blok> dysk,int rozm) {
		ar=dysk;
		rozmiarDysku=rozm;
	}
	
	public void obliczScan() {
		Blok current;
		for(int i=0;i<ar.size();) {
			while(pozycja<=rozmiarDysku) {
				while((current=getByPosition(pozycja))!=null) {
					current.nrWykonania=i;
					current.czasOczekiwania=droga-current.momentZgloszenia;
					current.wykonany=true;
					current.nrWykonania=i;
					i++;
				}
				pozycja++;
				droga++;
			}
			zmianyKierunku++;
			pozycja--;
			while(pozycja>=0) {
				while((current=getByPosition(pozycja))!=null) {
					current.nrWykonania=i;
					current.czasOczekiwania=droga-current.momentZgloszenia;
					current.wykonany=true;
					current.nrWykonania=i;
					i++;
				}
				pozycja--;	
				droga++;
			}
			zmianyKierunku++;
			pozycja++;
			droga-=2;
		}

	}
	
	public void obliczCScan() {
		Blok current;
		for(int i=0;i<ar.size();) {
			while(pozycja<=rozmiarDysku) {
				while((current=getByPosition(pozycja))!=null) {
					current.nrWykonania=i;
					current.czasOczekiwania=droga-current.momentZgloszenia;
					current.wykonany=true;
					current.nrWykonania=i;
					i++;
				}
				pozycja++;
				droga++;
			}
			droga+=rozmiarDysku-1;
			pozycja=0;
			przejscia++;
		}
	}
	
	private Blok getByPosition(int polozenie) {
		for(int i=0;i<ar.size();i++) {
			if(!ar.get(i).wykonany&&droga>=ar.get(i).momentZgloszenia&&ar.get(i).pozycja==pozycja)
				return ar.get(i);	
		}
		return null;
	}
	
	public void blokiScan() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaScan();

	}
	
	public void blokiCScan() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaCScan();

	}
	
	public void sredniaCScan() {
		sredniCzas=0;
		for(int i=0;i<ar.size();i++) {
			sredniCzas+=ar.get(i).czasOczekiwania;
			ar.get(i).clear();
		}
		int suma=(int) sredniCzas;
		zmianyKierunku--;
		sredniCzas/=ar.size();
		System.out.println("Sredni czas oczekiwania: " + sredniCzas + "\t" +
				"Suma oczekiwania: " + suma + "\t" + 
				"Czas dzialania: "+ droga + "\t" + 
				"Ilosc przejsc: " + przejscia);
		System.out.println();
	}
	
	public void sredniaScan() {
		sredniCzas=0;
		for(int i=0;i<ar.size();i++) {
			sredniCzas+=ar.get(i).czasOczekiwania;
			ar.get(i).clear();
		}
		int suma=(int) sredniCzas;
		zmianyKierunku--;
		sredniCzas/=ar.size();
		System.out.println("Sredni czas oczekiwania: " + sredniCzas + "\t" +
				"Suma oczekiwania: " + suma + "\t" + 
				"Czas dzialania: "+ droga + "\t" + 
				"Zmian kierunku: " + zmianyKierunku);
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
