package Lab14;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Wspolrzedne {
	
	
	public void stworz(int dolny, int gorny, int ilosc) {
		int x;
		int y;
		Random gen = new Random();
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("Wykres.txt"));
			bw.write("Liczba punktow: " + ilosc);
			bw.newLine();
			bw.write("x" + "\t" +"y");
			bw.newLine();
			for(int i=0; i<ilosc;i++) {
				x= gorny - gen.nextInt(gorny - dolny);
				y= gorny - gen.nextInt(gorny - dolny);
				bw.write(x + "\t");
				bw.write(y + "\t");
				bw.newLine();
		}
 		bw.close();
 		
 		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
