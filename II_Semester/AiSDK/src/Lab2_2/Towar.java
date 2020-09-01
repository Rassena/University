package Lab2_2;

public class Towar {
	String nazwa;
	int ilosc;
	double cena;
	double wartosc;
	
	public Towar(String n, int i, double c) {
		nazwa = n;
		ilosc = i;
		cena = c;
		wartosc = cena*ilosc;
	}
	
	public String toString () {
		return nazwa + "\t" + ilosc + "\t" + cena + "\t" + wartosc;
	}

	public void setCena(double c) {
		cena = c;
		wartosc= ilosc *cena;
	}
	
	public boolean equals(Towar ob) {
		return nazwa.equals(ob.nazwa);
	}
}
