package Lab2_1;

public class Main {
	
	public static void main(String[] args) {
		
		String plik1 = "Studenci.txt";
		Grupa gr = new Grupa();
		gr.czytaj(plik1);
		gr.wyswietl();
		System.out.println();
		gr.wyswietlNieZdani();
		System.out.println();
		gr.sredniaPozytyw();
		gr.zmianaOceny("Nowak", "Jan", 4.0);
		gr.zapisz();
	}

}
