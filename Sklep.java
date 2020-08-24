package FirstSemestr;

abstract public class Sklep {
	String tytul;
	double cena;
	int ile;
	
	public Sklep(String t, double c, int i) {
		tytul=t;
		cena=c;
		ile=i;
	}
	
	public String gettytul() {
		return tytul;
	}
	
	public double getcena() {
		return cena;
	}
	
	public int getilosc() {
		return ile;
	}
	
	public void dodaj(int i) {
		ile+=i;
	}
	
	
	public String toString() {
		String s=tytul + "\t\t" + cena + "\t" + ile;
		return s;
	}
	
	abstract public boolean zaDrogie(double c);
	
	abstract public String getplatforma();

}
