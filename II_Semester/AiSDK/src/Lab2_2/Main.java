package Lab2_2;

public class Main {
	

	public static void main(String[]args) {
		
		Magazyn mg = new Magazyn();
		mg.wyswietl();
		mg.zmianaCeny("Lampa", 60.0);
		System.out.println();
		mg.wyswietl();
		System.out.println();
		mg.wyswietyMniejsze();
		System.out.println();
		mg.braki();
	}
	
}
