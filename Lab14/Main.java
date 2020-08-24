package Lab14;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static void main(String[]args) {
		
		Wspolrzedne ws = new Wspolrzedne();
		ws.stworz(-10, 10, 20);
		
		Obliczenia ob = new Obliczenia();
		ob.tablice();
		ob.wypisz();
		int najdalszy = ob.najdalszy(); 
		System.out.println("Najdalszy pkt to: (" + ob.tabx[najdalszy] + "," + ob.taby[najdalszy]+ ")");
		System.out.println("Krawedzie prostokata to: " + ob.prostokat());
		System.out.println("odleglosci to: " + ob.odleglosci());
		System.out.println("Najdalej sa: " + ob.najdalsze());
		System.out.println("Po sortowaniu: " + ob.sortowanie());		
		
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("obliczenia.txt"));
			bw.write("1. Najdalszy pkt to: (" + ob.tabx[ob.najdalszy()] + "," + ob.taby[ob.najdalszy()] + ")");
			bw.newLine();
			bw.write("2. Krawedzie prostokata to: " + ob.prostokat());
			bw.newLine();
			bw.write("3. odleglosci to: " + ob.odleglosci());
			bw.newLine();
			bw.write("4. Najdalej sa: " + ob.najdalsze());
			bw.newLine();
			
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	


	
	
}