package Lab2_2;


public class Magazyn {

	public Towar[] mag;
	Towar[] brakTowaru;
	
	public Magazyn() {	
		Towar t1 = new Towar("Szafa", 3, 100.0);
		Towar t2 = new Towar("Stol", 2, 200.0);
		Towar t3 = new Towar("Lampa", 1, 50.0);
		Towar t4 = new Towar("Biurko", 6, 150.0);
		Towar t5 = new Towar("Lozko", 0, 300.0);
		Towar t6 = new Towar("Komoda", 5, 90.0);
		
		mag= new Towar[6];
		
		mag[0] = t1;
		mag[1] = t2;
		mag[2] = t3;
		mag[3] = t4;
		mag[4] = t5;
		mag[5] = t6;
	}
	
	public void wyswietl() {
		TIterator<Towar>  tit= new TIterator<Towar>(mag);
		
		System.out.println("Nazwa: " + "\t" + "Ilosc: " + "\t" + "Cena: " + "\t" + "Wartosc: ");
		while(tit.hasNext()) {
			System.out.println(tit.next());
		}
	}
	
	public void zmianaCeny(String towar, double nowaC) {
		for(int i=0;i<mag.length;i++) {
			if(mag[i].nazwa.equals(towar)) {
				mag[i].setCena(nowaC);
				return;
			}
		}
	}
	
	public void braki() {
		int i=0;
		TIterator<Towar>  it= new TIterator<Towar>(mag);
		Wyczerpane wy= new Wyczerpane();
		FIterator<Towar> fit = new FIterator<Towar>(it,wy);
		while(it.hasNext()) {
			if(it.next().ilosc==0) {
				i++;
			}
		}
		i=0;
		brakTowaru =new Towar[i];
		it= new TIterator<Towar>(mag);
		System.out.println("Wyczerpane Towary:");
		System.out.println("Nazwa: " + "\t" + "Ilosc: " + "\t" + "Cena: " + "\t" + "Wartosc: ");
		
		while(fit.hasNext()) {
			System.out.println(fit.next());
		}
	}
	
	public void wyswietyMniejsze() {
		TIterator<Towar> it = new TIterator<Towar> (mag);
		Cena c= new Cena();
		
		FIterator<Towar> fit = new FIterator<Towar>(it,c);
		System.out.println("Nie drozsze niz 100:");
		System.out.println("Nazwa: " + "\t" + "Ilosc: " + "\t" + "Cena: " + "\t" + "Wartosc: ");
		
		while(fit.hasNext())
			System.out.println(fit.next());
	}
	
	
}
