package FirstSemestr;

public class Book {
	String autor;
	String tytul;
	int strony;
	boolean wypozyczona;
	
	
	public Book(String a, String t, int s, boolean w ) {
			autor = a;
			tytul = t;
			strony =s;
			wypozyczona = w;		
	}
	
	public void wyswietl() {
		System.out.println(autor);
		System.out.println(tytul);
		System.out.println(strony);
		
	}
	
	public boolean czyDostepna() {
		if (wypozyczona == false)
			 return true;
		else
			 return false;
	}
	
	public void wypozycz() {
		if (wypozyczona == true )
			System.out.println("Ksiazka jest wypozyczona");
		else {
			System.out.println("OK");
			wypozyczona = true;
		}
	}
	
	public void zwrot() {
		wypozyczona = false;
		System.out.println("Ksiazka zwrocona");
	}
	
	public static void main(String[] args) {
		Book ks1=new Book("Mickiewicz","Dziady",300, false);
		ks1.wyswietl();
		ks1.czyDostepna();
		ks1.wypozycz();
		ks1.wypozycz();
		ks1.zwrot();
		
	}
}
