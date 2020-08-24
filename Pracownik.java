package FirstSemestr;

abstract public class Pracownik {
	String nazwisko;
	double etat;
	String stan;
	
	public Pracownik(String n, double e, String s) {
		nazwisko=n;
		etat=e;
		stan=s;
	}
	
	abstract public double wyplata();
	
	public String getNazwisko() {
		String n= nazwisko;
		return n;
	}
	
	public double getEtat() {

		double e=etat;
		return e;
	}
	
	public String getZawod() {
		String z= stan;
		return z;
	}
	
	abstract public String toString();

}
