package Lab2_5;

//import Lab2_2.*;
import Lab2_4.EmptyQueueException;


public class Main {
	
	public static void main(String[]args) throws EmptyQueueException {
		
//		int size = 3;
		
//		StackUnlimited<Towar> sU = new StackUnlimited<Towar>();
//		StackDynamic<Towar> sD = new StackDynamic<Towar>(size);
		ConvertToBinary ctb = new ConvertToBinary();
//		SimpleCalculator sC = new SimpleCalculator();
/*		
		Magazyn mg = new Magazyn();
		System.out.println("Na wejsciu:");
		
		for(int i = 0;i<mg.mag.length;i++) {
			sU.add(mg.mag[i]);
			sD.add(mg.mag[i]);
			System.out.println(mg.mag[i]);
		}
		
		System.out.println("Na wyjsciu Stack Unlimited:");
		sU.ShowAll();
		System.out.println("Na wyjsciu Stack Dynamic:");
		sD.showAll();
		sD.get();
*/	
		ctb.Read();
//		sC.calculateFromFile();
		
	}
}
