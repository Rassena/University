package Lab2_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimpleCalculator {
	Scanner sc;
	private String mark;
	
	public SimpleCalculator() {}
	
	public void calculateFromFile() {
		Scanner read = new Scanner(System.in);
		boolean fileFound=false;
		do {
		try {
			System.out.print("File Name (Sample Calculate): ");
			sc = new Scanner(new File("/Volumes/NO NAME/Eclipse/AiSDK/src/Documents/"+ read.nextLine()));
			fileFound=true;} 
		catch (FileNotFoundException e) {System.out.println("File Not Found");} 
		} while(!fileFound);
		read.close();
		calculate();
	}
	
	private void calculate() {
		
		StackUnlimited<Integer> sU = new StackUnlimited<Integer>();
		while(sc.hasNext()) {
			while(sc.hasNextInt())
				sU.add(sc.nextInt());
			mark = sc.next();
			if(mark.equals("+"))
				sU.add(sU.get() + sU.get());
			if(mark.equals("*"))
				sU.add(sU.get()*sU.get());
		}
		System.out.println("Wynik: " + sU.get());
		sc.close();
	}
	
}
