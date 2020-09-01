package Lab14;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Obliczenia {
	BufferedImageFilter bw;
	int[] tabx;
	int[] taby;
	
	public void tablice() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Wykres.txt"));
			String linia= br.readLine();
			String[] wartosc = linia.split(" ");
			int i = Integer.parseInt(wartosc[2]);
			tabx = new int[i];
			taby = new int[i];
			br.readLine();
			for(i=0; i<tabx.length;i++) {
				linia = br.readLine();
				wartosc = linia.split("\t");
				tabx[i]= Integer.parseInt(wartosc[0]);
				taby[i]= Integer.parseInt(wartosc[1]);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void wypisz() {
		for(int i=0; i<tabx.length;i++) {
			System.out.println(tabx[i] + "\t" + taby[i]);
		}
		System.out.println();
	}
	
	
	public int najdalszy() {
		double odleglosc=0;
		double najdalej=0;
		int nrpkt = 0;
		
		for(int i=0; i<tabx.length;i++) {
			double x = tabx[i];
			double y = taby[i];
			odleglosc = Math.hypot(x, y);
			if (odleglosc>najdalej) {
				najdalej=odleglosc;
				nrpkt =i;
			}
		}
		
		return nrpkt;	
	}
	
	public String prostokat() {
		int xmin= tabx[0];
		int xmax= tabx[0];
		int ymin= taby[0];
		int ymax= taby[0];
		for(int i=0; i<tabx.length;i++) {
			if(xmax<tabx[i])
				xmax=tabx[i];
			if(xmin>tabx[i])
				xmin=tabx[i];
			if(ymax<taby[i])
				ymax=taby[i];
			if(ymin>taby[i])
				ymin=taby[i];
		}
		return " (" + xmax + "," + ymax + ")" + " (" + xmin + "," + ymax + ")" + " (" + xmin + "," + ymin + ")" + " (" + xmax + "," + ymin + ")";
	}
	
	public double odleglosc(int x1, int y1, int x2, int y2) {
		double odleglosc = Math.hypot(x2 - x1 , y2- y1);
		return odleglosc;
	}
	
	public String odleglosci() {
		String odp ="";
		for(int i=0;i<tabx.length;i++) {
			for (int j=i+1; j<tabx.length;j++) {
				double odleglosc = odleglosc(tabx[i],taby[i],tabx[j], taby[j]);
				odp+= "\n(" + tabx[i] + "," + taby[i] + ")" + " (" + tabx[j] + "," + taby[j] + ")" + "\t" + odleglosc + "";
			}
		}
		return odp;
	}
	
	public String najdalsze() {
		int nrpkt1=0;
		int nrpkt2=0;
		double najdalej=0;
		for(int i=0;i<tabx.length;i++) {
			for (int j=i+1; j<tabx.length;j++) {
				double odleglosc = odleglosc(tabx[i],taby[i],tabx[j],taby[j]);
				if(odleglosc>najdalej) {
					najdalej =odleglosc;
					nrpkt1= i;
					nrpkt2= j;
				}
			}
		}
		return "(" + tabx[nrpkt1] + "," + taby[nrpkt1] + ")" + " (" + tabx[nrpkt2] + "," + taby[nrpkt2] + ")";
	}
	
	public String sortowanie() {
	//	ArrayList<Double> dlugosc= new ArrayList<Double>();
		int[] tabxz = new int[tabx.length];
		int[] tabyz = new int[tabx.length];
		int[] tabz2 = new int[tabx.length];
		int[] tabz1 = new int[tabx.length];
		for(int i =0; i<tabxz.length;i++) {
			tabxz[i] = 0;
			tabyz[i] = 0;
		}

		tabz2 = tabyz;
		tabz1 = tabxz;
		
		for(int i=0; i<tabx.length;i++) {
			boolean szuka = true;
			for(int j=0;j<tabxz.length&&szuka;j++) {
				if(Math.hypot(tabx[i], taby[i])>Math.hypot(tabxz[j], tabyz[j])) {
					tabz1[j] = tabx[i];
					tabz2[j] = taby[i];
					szuka = false;
					for(;j<tabxz.length - 1;j++) {
						tabz1[j+1] =  tabxz[j]; 
						tabz2[j+1] =  tabyz[j];
					}
					j++;
					tabxz = tabz1;
					tabyz = tabz2;
				}
			}
		}
		String s = "";
		for( int i=0; i<tabxz.length;i++) {
			s+= "\n(" + tabxz[i] + "," + tabyz[i] + ")\t" + Math.hypot(tabxz[i], tabyz[i]);
		}
		return s;
			
			
			
			
		
	/*	for(int i=0; i<tabx.length;i++) {
			dlugosc.add(Math.hypot(tabx[i], taby[i]));
		}
		dlugosc.sort(null);
		String s="";						*/
		
	}
	
}
