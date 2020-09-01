package OSLab1;

import java.util.ArrayList;

public class FCFS {
	private ArrayList<Proces> ar;
	private int czasDzialan=0;
	private double sredniCzas;
	
	public FCFS(ArrayList<Proces> procesy) {
		ar = procesy;
	}
	
	public void obliczFCFS() {

		for(int i=0;i<ar.size();i++) {
			while(czasDzialan<ar.get(i).momentZgloszenia)
				czasDzialan++;
			ar.get(i).czasOczekiwania=czasDzialan-ar.get(i).momentZgloszenia;
			czasDzialan+=ar.get(i).czasWykonania;		
		}
	}
	
	public void procesyFCFS() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i+ ". " + ar.get(i));
		sredniaFCFS();

	}
	
	public void sredniaFCFS() {
		sredniCzas=0;
		for(int i=0; i <ar.size();i++) {
			sredniCzas+=ar.get(i).czasOczekiwania;
			ar.get(i).clear();
		}
		int suma=(int) sredniCzas;
		sredniCzas/= ar.size();
		System.out.println("Sredni czas oczekiwania: "+ sredniCzas + "\t" + "Suma oczekiwania: " + suma + "\t" + "Czas dzialania: "+ czasDzialan);
		System.out.println();
		
	}
}
