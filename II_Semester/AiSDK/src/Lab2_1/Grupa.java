package Lab2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Grupa {
	ArrayList<Student> ar;
	BufferedReader br;
	BufferedWriter bw;
	String plik;
	Iterator<Student> itr;
	
	public void czytaj(String plik) {
		this.plik =plik;
		String lina=null;
		ar = new ArrayList<Student>();
		
		try {
			br = new BufferedReader(new FileReader(plik));
			while((lina = br.readLine())!=null) {
				String[] dane = lina.split("\\s+");
				double oc = Double.parseDouble(dane[3]);
				int in = Integer.parseInt(dane[0]);
				Student a = new Student(in, dane[1],dane[2],oc);
				ar.add(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void wyswietl() {
		System.out.println("Wszyscy studenci: ");
		itr =ar.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	
	public void sredniaPozytyw() {
		double suma=0;
		double liczba=0;
		
		double srednia;
		itr =ar.iterator();
		while(itr.hasNext()) {
			double ocena= itr.next().ocena;
			if(ocena>=3.0) {
				suma+=ocena;
				liczba++;
			}
		}
		srednia=suma/liczba;
		System.out.println("Srednia ocen z pozytywnych: " + srednia);
	}
	
	public void wyswietlNieZdani() {
		System.out.println("Studenci bez zaliczenia: ");
		for(int i =0; i<ar.size();i++) {
			if(ar.get(i).ocena<3.0)
				System.out.println(ar.get(i));
		}
	}
	
	public void zmianaOceny(String nazw, String im, double oc) {
		boolean b=false;
		int j=0;
		for(int i=0;i<ar.size()&&b==false;i++) {
			if(ar.get(i).nazwisko.equals(nazw)){
				if(ar.get(i).imie.equals(im)){
						b=true;
						j=i;
				}
			}
		}
		if(b) {
			ar.get(j).setOcena(oc);
		}
	}
	
	public void zapisz() { 
		try {
			bw = new BufferedWriter(new FileWriter(plik));
			itr =ar.iterator();
			while(itr.hasNext()==true) {
				Student stud = itr.next();
				bw.write(stud.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
