package OSLab2;

public class Blok {
	int odstepOdPoprzedniegoZgloszenia;
	int pozycja;
	int momentZgloszenia;
	boolean wykonany=false;
	int nrWykonania = 0;
	boolean priorytet;
	int czasOczekiwania;
	
	public Blok(int pozycja, int oopz, boolean priorytet) {
		this.pozycja=pozycja;
		this.odstepOdPoprzedniegoZgloszenia = oopz;
		this.priorytet = priorytet;
	}
	
	public String toString() {
		return "Pozycja: " + pozycja + "\t" +
//				"Odstep od poprzedniego: " + odstepOdPoprzedniegoZgloszenia + "\t" + 
//				wykonany + "\t" +
//				"Czas pracy: " + czasPracy + "\t" +
				"Moment Zgloszenia: " + momentZgloszenia +"\t" + 
				"Priorytet: " + priorytet + " \t" + 
				"Czas Oczekiwania: " + czasOczekiwania; 
	}
	
	public void clear() {
		wykonany = false;
		nrWykonania = 0;
		czasOczekiwania=0;
	}
}
