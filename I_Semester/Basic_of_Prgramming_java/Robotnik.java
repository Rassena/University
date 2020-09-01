package FirstSemestr;

public class Robotnik extends Pracownik {
	double stawka;
	double lGodzin;
	final double LIMIT=150;
	
	public Robotnik(String n, Double e, double s, double lg ) {
		super(n,e, "r");
		stawka=s;
		lGodzin= lg;
	}
	
	public double wyplata() {
		double w=stawka*lGodzin;
		if (lGodzin>LIMIT)
			w=w+ 0.5*(lGodzin-etat*LIMIT);	
		return w;
	}

	public String toString() {
		String s= nazwisko + etat + stawka + lGodzin;
		return s;
	}
		
	
}

