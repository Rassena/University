package Lab2_6;

public class Person implements Comparable<Person>{
	private String nazwisko;
	private String imie;
	private int wiek;
	
	public Person(String nazw, String im, int w) {
		nazwisko=nazw;
		imie = im;
		wiek = w;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	
	public String getImie() {
		return imie;
	}
	
	public int getWiek() {
		return wiek;
	}
	
	public String toString() {
		return getNazwisko() +"\t"+ getImie() +"\t"+ getWiek();
	}
	
//	public String toString(){
//		return String.format("%6d %8.2f\n",getImie(),getWiek());
//	}

	
	@Override
	public int compareTo(Person o) {
		return this.getNazwisko().compareTo(o.getNazwisko());
	}
	
	
}
