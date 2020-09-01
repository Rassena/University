package OSLab1;

import java.util.ArrayList;

public class Rotacyjne {
	private int czasDzialan;
	private int czasKwantu;
	private ArrayList<Proces> ar;
	private int konteksty;
	private double sredniCzas=0;
	
	public Rotacyjne(ArrayList<Proces> procesy,int ck) {
		ar=procesy;
		czasKwantu=ck;
	}
	
	public void obliczRotacyjne() {
		czasDzialan=0;
		boolean znaleziono=false;
		for(int index=0;index<ar.size();) {
			znaleziono=false;
			for(int i=0;i<ar.size();i++) {
				if(!ar.get(i).wykonany&&ar.get(i).momentZgloszenia<=czasDzialan) {
					if((ar.get(i).czasWykonania-ar.get(i).czasPracy)<=czasKwantu) {
						czasDzialan+=ar.get(i).czasWykonania-ar.get(i).czasPracy;
						ar.get(i).wykonany=true;
						ar.get(i).nrWykonania=index;
						index++;
						konteksty++;
						for(int j =0;j<ar.size();j++) {
							if(!ar.get(j).wykonany&&ar.get(j).momentZgloszenia<=czasDzialan&&j!=i) {
								if(ar.get(j).momentZgloszenia<=czasDzialan-(ar.get(i).czasWykonania-ar.get(i).czasPracy)) {
									ar.get(j).czasOczekiwania+=(ar.get(i).czasWykonania-ar.get(i).czasPracy);
								}
								else {
									ar.get(j).czasOczekiwania=czasDzialan-ar.get(j).momentZgloszenia;
								}
							}
						}
						ar.get(i).czasPracy=ar.get(i).czasWykonania;
					}
					else {
						ar.get(i).czasPracy+=czasKwantu;
						czasDzialan+=czasKwantu;
						konteksty++;
						for(int j =0;j<ar.size();j++) {
							if(!ar.get(j).wykonany&&ar.get(j).momentZgloszenia<=czasDzialan&&j!=i) {
								if(ar.get(j).momentZgloszenia<=czasDzialan-czasKwantu) {
									ar.get(j).czasOczekiwania+=czasKwantu;
								}
								else {
									ar.get(j).czasOczekiwania=czasDzialan-ar.get(j).momentZgloszenia;
								}
							}
						}
					}
					znaleziono=true;
				}
			}
			if(!znaleziono) {
				czasDzialan++;
			}
			
		}
	}
	
	private Proces getProces(int nrZrobienia) {
		for(int i=0;i<ar.size();i++) {
			if(nrZrobienia==ar.get(i).nrWykonania)
				return ar.get(i);
		}
		return null;
	}

	public void procesyRotacyjne() {
		for(int i=0; i <ar.size();i++) {
			System.out.println(i + ". " + getProces(i));
		}
		sredniaRotacyjne();
	}
	
	public void sredniaRotacyjne() {
		sredniCzas=0;
		for(int i =0; i<ar.size();i++) {
			sredniCzas+=getProces(i).czasOczekiwania;
			getProces(i).clear();
		}
		int suma=(int) sredniCzas;
		sredniCzas/= ar.size();
		System.out.println("Sredni czas oczekiwania: "+ sredniCzas + "\t" + 
		"Suma oczekiwania: " + suma + "\t" +
		"Czas dzialania: "+ czasDzialan + "\t" +
		"Konteksty: " + konteksty);
		System.out.println();
		
	}
	
}
