package OSLab1;

import java.util.ArrayList;

public class SJF {
	private ArrayList<Proces> ar;
	private int czasDzialan=0;
	private double sredniCzas=0;
	
	public SJF(ArrayList<Proces> procesy) {
		ar = procesy;
	}
	
	public void obliczSJF() {
		czasDzialan=0;
		for(int i=0;i<ar.size();i++) {
			int index =najkrotszy();
			ar.get(index).czasOczekiwania=czasDzialan-ar.get(index).momentZgloszenia;
			ar.get(index).nrWykonania = i;
			ar.get(index).wykonany = true;
			czasDzialan+=ar.get(index).czasWykonania;
		}
		
	}
	
	public void obliczSJFWywlaszczenie() {
		czasDzialan=0;
		boolean end=false;
		int index;
		for(int i=0;i<ar.size();i++) {
			
			while(!end) {
				index=najkrotszy();
				while(index==najkrotszy()&&!end) {

					if(ar.get(index).czasWykonania==ar.get(index).czasPracy&&!end) {
						ar.get(index).wykonany=true;
						ar.get(index).nrWykonania=i;
						end = true;
					}
					else {
						ar.get(index).czasPracy++;
						for(int j=0;j<ar.size();j++) {
							if(j!=index)
								if(ar.get(j).momentZgloszenia<=czasDzialan&&!ar.get(j).wykonany)
									ar.get(j).czasOczekiwania++;
						}
					}
					index=najkrotszy();
					if(!end)
						czasDzialan++;
				}
			}	
			
			end=false;	
			
		}

	}
	
	private int najkrotszy() {
		int najmniej = najdluzej();
		int index=-1;
		while(index==-1) {
			int licznik=0;
			for(int i=0;i<ar.size();i++) {
				if(!ar.get(i).wykonany) {
					if(ar.get(i).momentZgloszenia<=czasDzialan) {
						if(najmniej>ar.get(i).czasWykonania-ar.get(i).czasPracy) {
							najmniej=ar.get(i).czasWykonania-ar.get(i).czasPracy;
							index=i;
						}
					}
				}
				else {
					licznik++;
				}
			}
			if(index==-1&&licznik==ar.size()) {
				break;
			}
			if(index==-1)
				czasDzialan++;

		}
		return index;
	}
	
	private int najdluzej() {
		int najdluzej=0;
		for(int i=0;i<ar.size();i++) {
			if(ar.get(i).czasWykonania-ar.get(i).czasPracy>najdluzej)
				najdluzej=ar.get(i).czasWykonania-ar.get(i).czasPracy;
		}
		return najdluzej+1;
	}
	
	public void procesySJF() {
		for(int i=0; i <ar.size();i++)
			System.out.println(i + ". " + getProces(i));
		sredniaSJF();
	}
	
	public void sredniaSJF() {
		sredniCzas=0;
		for(int i=0;i<ar.size();i++) {
			sredniCzas+=getProces(i).czasOczekiwania;
			getProces(i).clear();
		}
		int suma=(int) sredniCzas;
		sredniCzas/= ar.size();
		System.out.println("Sredni czas oczekiwania: "+ sredniCzas + "\t" +
		"Suma oczekiwania: " + suma + "\t" + 
		"Czas dzialania: "+ czasDzialan);
		System.out.println();
	}
	
	public void procesySJFWywlaszczenie() {
		sredniCzas=0;
		for(int i=0; i <ar.size();i++) {
			System.out.println(i + ". " + getProces(i));
			sredniCzas+=getProces(i).czasOczekiwania;
			
		}
		sredniaSJF();
	}
	
	private Proces getProces(int nrZrobienia) {
		for(int i=0;i<ar.size();i++) {
			if(nrZrobienia==ar.get(i).nrWykonania)
				return ar.get(i);
		}
		return null;
	}

}
