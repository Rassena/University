package OSLab2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Main {
	
	public static void dysk(ArrayList<Blok> procesy) {
		System.out.println("Procesy na wejsciu:");
		for(int i=0; i <procesy.size();i++)
			System.out.println(i+ ". " + procesy.get(i));
		System.out.println();
	}

	public static void main(String[]args) {
		
		System.out.print("Wpisz ilosc blokow: ");
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		System.out.print("Wpisz maksymalna wielkosc dysku: ");
		int diskSize = sc.nextInt();
		System.out.print("wpisz maksymalny odstep miedzy zgloszeniami: ");
		int odstep = sc.nextInt();
		System.out.print("Wpisz procentowe prawdopodobiensto bloku z priorytetem: ");
		int priorytet = sc.nextInt();
		System.out.println();
		
		sc.close();

		ArrayList<Blok> dysk= new ArrayList<Blok>();	
		Random r = new Random();
		
		for(int i = 0; i<n;i++) {dysk.add(new Blok(r.nextInt(diskSize+1),r.nextInt(odstep+1),r.nextInt(100)>=100-priorytet));}
		for(int i =1; i<n;i++) {dysk.get(i).momentZgloszenia=dysk.get(i).odstepOdPoprzedniegoZgloszenia+dysk.get(i-1).momentZgloszenia;}
		
		FCFS fcfs = new FCFS(dysk);
		EDF edf = new EDF(dysk,diskSize);
		SSTF sstf = new SSTF(dysk,diskSize);
		EDF edfsstf = new EDF(dysk,diskSize);
		Scan scan = new Scan(dysk, diskSize);
		Scan cscan = new Scan(dysk, diskSize);
		FDScan fdscan = new FDScan(dysk, diskSize);
		FDScan fdcscan = new FDScan(dysk, diskSize);


		System.out.println("Bloki przy FCFS:");
		fcfs.obliczFCFS();
		fcfs.sredniaFCFS();
		
		System.out.println("Bloki przy EDF FCFS:");
		edf.obliczEDF();
		edf.sredniaEDF();
		
		System.out.println("Bloki przy SSTF:");
		sstf.obliczSSTF();
		sstf.sredniaSSTF();
		
		System.out.println("Bloki przy EDF SSTF:");
		edfsstf.obliczEDFSSTF();
		edfsstf.sredniaEDF();

		System.out.println("Bloki przy Scan:");
		scan.obliczScan();
		scan.sredniaScan();
		
		System.out.println("Bloki przy FDScan:");
		fdscan.obliczFDScan();
		fdscan.sredniaFDScan();
		
		System.out.println("Bloki przy CScan:");
		cscan.obliczCScan();
		cscan.sredniaCScan();
		
		System.out.println("Bloki przy FDCScan:");
		fdcscan.obliczFDCScan();
		fdcscan.sredniaFDCScan();
	}
}
