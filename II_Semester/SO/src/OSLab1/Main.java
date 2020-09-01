package OSLab1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {

	public static void procesy(ArrayList<Proces> procesy) {
		System.out.println("Procesy na wejsciu:");
		for(int i=0; i <procesy.size();i++)
			System.out.println(i+ ". " + procesy.get(i));
		System.out.println();
	}
	
	
	public static void main(String[]args) {
		
		System.out.print("Wpisz ilosc procesow: ");
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		System.out.print("Wpisz gorna granica czasu wykonania procesu: ");
		int time = sc.nextInt();
		System.out.print("Wpisz gorna granica odstepu miedzy procesami: ");
		int odstep = sc.nextInt();
		System.out.print("Wpisz czas kwantu w rotacyjnym: ");
		int kwant = sc.nextInt();
		System.out.println();
		sc.close();
		
		ArrayList<Proces> procesy= new ArrayList<Proces>();	
		Random r = new Random();
		
		for(int i = 0; i<n;i++) {procesy.add(new Proces(r.nextInt(time+1),r.nextInt(odstep+1)));}
		for(int i =1; i<n;i++) {procesy.get(i).momentZgloszenia=procesy.get(i).odstepOdPoprzedniegoZgloszenia+procesy.get(i-1).momentZgloszenia;}

		FCFS fcfs = new FCFS(procesy);
		SJF sjf = new SJF(procesy);
		SJF sjfw = new SJF(procesy);
		Rotacyjne rot = new Rotacyjne(procesy,kwant);
			
		System.out.println("Procesy przy FCFS:");
		fcfs.obliczFCFS();
//		fcfs.procesyFCFS();
		fcfs.sredniaFCFS();
		
		System.out.println("Procesy przy SJF:");
		sjf.obliczSJF();
//		sjf.procesySJF();
		sjf.sredniaSJF();
		
		System.out.println("Procesy przy SJF z wywlaszczeniem:");
		sjfw.obliczSJFWywlaszczenie();
//		sjfw.procesySJFWywlaszczenie();
		sjfw.sredniaSJF();
		
		System.out.println("Procesy przy Rotacyjnym z kwantem czasu " + kwant + ":");
		rot.obliczRotacyjne();
//		rot.procesyRotacyjne();
		rot.sredniaRotacyjne();
	}
	
}
