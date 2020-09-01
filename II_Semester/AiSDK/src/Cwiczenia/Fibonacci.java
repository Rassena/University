package Cwiczenia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci {
	
	BufferedReader br;
	BufferedWriter bw1;
	BufferedWriter bw2;
	double a;
	double b;
	double c;
	
	public void odczyt() {
		try {
			bw2 = new BufferedWriter(new FileWriter("CiagFinacciegoCaly.txt"));
			
			for(int i=0;i <10000; i++) {
				
				br = new BufferedReader(new FileReader("CiagFibonacciegoOstatnie.txt"));
				
				String pierwsza = br.readLine();
				String druga = br.readLine();
				a= Double.parseDouble(pierwsza);
				b= Double.parseDouble(druga);
				c=a+b;
				a=b;
				b=c;
				br.close();
				bw1 = new BufferedWriter(new FileWriter("CiagFibonacciegoOstatnie.txt"));
				String x= String.format("%.0f", a);
				String y= String.format("%.0f", b);
				bw1.write(x);
				bw1.newLine();
				bw1.write(y);
				bw1.newLine();
				bw2.write(y);
				bw2.newLine();
				bw1.close();
			}
			bw2.close();
			
		}catch(IOException e) {
			e.printStackTrace();}
	
		
		
	}
		
	
	
	
	
	

	public static void main(String[]args) {
		Fibonacci f=new Fibonacci();
		f.odczyt();
		
		
		
	}
	
}

