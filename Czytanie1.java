package Cwiczenia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Czytanie1 {
	
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String w;
		int suma = 0;
		while((w=br.readLine())!=null&&((w.length())!=0)) {
			suma+=Integer.parseInt(w);
		}
		System.out.println(suma);
		
	}

}
