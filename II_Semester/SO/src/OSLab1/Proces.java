package OSLab1;

public class Proces {
	int czasWykonania;
	int odstepOdPoprzedniegoZgloszenia;
	int czasOczekiwania=0;
	int momentZgloszenia;
	boolean wykonany=false;
	int nrWykonania = 0;
	int czasPracy=0;
	
	public Proces(int cw, int oopz){
		czasWykonania = cw;
		odstepOdPoprzedniegoZgloszenia = oopz;
	}

	public String toString() {
		return "Czas wykonania: " + czasWykonania + "\t" +
//				"Odstep od poprzedniego: " + odstepOdPoprzedniegoZgloszenia + "\t" + 
//				wykonany + "\t" +
//				"Czas pracy: " + czasPracy + "\t" +
				"Moment Zgloszenia: " + momentZgloszenia +"\t" + 
				"czas oczekiwania: " + czasOczekiwania; 
	}
	
	public void clear() {
		czasOczekiwania = 0;
		wykonany = false;
		nrWykonania = 0;
		czasPracy = 0;
	}
}