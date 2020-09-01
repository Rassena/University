package OSLab2;

import java.util.ArrayList;

public class FDScan {

	private ArrayList<Blok> ar;
	private double sredniCzas=0;
	private double sredniaPriorytetow=0;
	private int rozmiarDysku;
	private int droga=0;
	private int pozycja=0;
	int priorytet=-1;
	private int zmianyKierunku;
	private boolean prawo=true;
	private int przejscia;
	
	public FDScan(ArrayList<Blok> dysk, int rozmiar) {
		ar = dysk;
		rozmiarDysku=rozmiar;
	}
	
	public void FD() {
		Blok current;
		while(pozycja<=rozmiarDysku) {
			while((current=getByPosition(pozycja))!=null&&current.priorytet) {
				
			}
		}
	}
	
	public void obliczFDScan() {
		Blok current;
		for(int i=0;i<ar.size();) {
			if(prawo&&jestPriorytet()) {
				int najdalszyBlok =ar.get(najdalszy()).pozycja;
				while(prawo&&najdalszyBlok>=pozycja) {
					while((current=getByPosition(pozycja))!=null&&current.priorytet) {
						current.nrWykonania=i;
						current.czasOczekiwania=droga-current.momentZgloszenia;
						current.wykonany=true;
						i++;
					}
					if(najdalszyBlok==pozycja) {
						prawo=false;
						zmianyKierunku++;
					}
					pozycja++;
					droga++;
				}
			}
			if(!prawo&&jestPriorytet()) {
				int najdalszyBlok =ar.get(najdalszy()).pozycja;
				while(!prawo&&najdalszyBlok<=pozycja) {
					while((current=getByPosition(pozycja))!=null) {
						current.nrWykonania=i;
						current.czasOczekiwania=droga-current.momentZgloszenia;
						current.wykonany=true;
						i++;
					}
					if(najdalszyBlok==pozycja)
						prawo=true;
					pozycja--;	
					droga++;
				}
			}
			if(!jestPriorytet()&&i<ar.size()) {
				while(prawo&&pozycja<=rozmiarDysku) {
					while((current=getByPosition(pozycja))!=null) {
						current.nrWykonania=i;
						current.czasOczekiwania=droga-current.momentZgloszenia;
						current.wykonany=true;
						i++;
					}
					if(pozycja==rozmiarDysku) {
						prawo=false;
						zmianyKierunku++;
					}
					pozycja++;
					droga++;
				}
				pozycja--;
				droga--;
				while(!prawo&&pozycja>=0) {	
					while((current=getByPosition(pozycja))!=null) {
						current.nrWykonania=i;
						current.czasOczekiwania=droga-current.momentZgloszenia;
						current.wykonany=true;
						i++;
					}
					if(pozycja==0) {
						prawo=true;
						zmianyKierunku++;
					}
					pozycja--;	
					droga++;
				}
				pozycja++;
			}
			droga--;
		}
		pozycja++;
		zmianyKierunku--;
	}	

	public void obliczFDCScan() {
		Blok current;
		prawo=true;	
		for(int i=0;i<ar.size();) {
			if(jestPriorytet()) {
				int najdalszyBlok =ar.get(najdalszy()).pozycja;
				while(pozycja<=najdalszyBlok) {
					while((current=getByPosition(pozycja))!=null) {
							current.nrWykonania=i;
							current.czasOczekiwania=droga-current.momentZgloszenia;
							current.wykonany=true;
							i++;
					}
					pozycja++;
					droga++;
				}
				droga+=rozmiarDysku-1;
				pozycja=0;
				przejscia++;
			}
			else {
				while(pozycja<=rozmiarDysku) {
					while((current=getByPosition(pozycja))!=null) {
						current.nrWykonania=i;
						current.czasOczekiwania=droga-current.momentZgloszenia;
						current.wykonany=true;
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
	
	private int najdalszy() {
		int odleglosc=0;
		int index=-1;
		if(jestPriorytet()) {
			if(prawo) {
				for(int i=0;i<ar.size();i++) {
					int dystans=0;
					if(!ar.get(i).wykonany&&ar.get(i).priorytet) {
						if(ar.get(i).pozycja>=pozycja)
							dystans=ar.get(i).pozycja-pozycja;
						if(dystans>=odleglosc) {
							odleglosc=dystans;
							index=i;
						}
					}
				}
			}
			else {
				for(int i=0;i<ar.size();i++) {
					int dystans=0;
					if(!ar.get(i).wykonany&&ar.get(i).priorytet) {
						if(ar.get(i).pozycja<=pozycja)
							dystans=pozycja-ar.get(i).pozycja;
						if(dystans>=odleglosc) {
							odleglosc=dystans;
							index=i;
						}
					}
				}
			}
		}
		return index;
	}		
	
	public void blokiFDScan() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaFDScan();
	}
	
	public void blokiCScan() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + getBlok(i));
		sredniaFDCScan();
	}
	
	public void sredniaFDCScan() {
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
		sredniCzas/=ar.size();
		sredniaPriorytetow/=prio;
		System.out.println("Sredni czas oczekiwania: " + sredniCzas +
				" Suma oczekiwania: " + suma +
				" Czas dzialania: "+ droga + 
				" Sredni czas oczekiwania prirytetu: " + sredniaPriorytetow + 
				" Ilosc przejsc: " + przejscia);
		System.out.println();
	}
	
	public void sredniaFDScan() {
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
				" Sredni czas oczekiwania prirytetu: " + sredniaPriorytetow + 
				" Ilosc Zmian kierunku: "+ zmianyKierunku);
		System.out.println();
	}
	
	private Blok getByPosition(int polozenie) {
		for(int i=0;i<ar.size();i++) {
			if(!ar.get(i).wykonany&&droga>=ar.get(i).momentZgloszenia&&ar.get(i).pozycja==pozycja)
				return ar.get(i);	
		}
		return null;
	}
			
	private Blok getBlok(int nrWykonania) {
		for(int i=0;i<ar.size();i++) {
			if(nrWykonania==ar.get(i).nrWykonania)
				return ar.get(i);
		}
		return null;
	}
	
}
