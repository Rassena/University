package Lab2_2;

public class Wyczerpane implements Predicate<Towar> {
	public boolean accept(Towar t) {
		return t.ilosc==0;
	}

}
