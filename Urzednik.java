package FirstSemestr;

public class Urzednik extends Pracownik {
	double placa;
	double premia;
	String stan;
	
	public Urzednik(String n, double e, double p, double pr) {
		super(n,e,"u");
		placa=p;
		premia=pr;
	}
	
	public double wyplata() {
		double w=placa*etat;
		w= w + w* (premia/100.0);
		return w;
	}

	public String toString() {
		String s= nazwisko + etat + placa + premia;
		return s;
	}
	
	
}
