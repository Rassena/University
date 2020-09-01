package Lab2_2;

public class Cena implements Predicate<Towar>{
	public boolean accept(Towar t) {
		return t.cena<=100;
	}

}
