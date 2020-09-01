package Lab2_4;

import Lab2_2.*;

public class Main {
	
	public static void main(String[]args) throws EmptyQueueException, FullQueueException {
	
		int size = 0;
		
//		QueueUnlimited<Towar> qU = new QueueUnlimited<Towar>();
		QueueLimited<Towar> qL = new QueueLimited<Towar>(size);
//		CycledBuffor<Towar> cb = new CycledBuffor<Towar>(size);
		Magazyn mg = new Magazyn();
		
//		for(int i = 0;i<mg.mag.length;i++) {
//			qU.add(mg.mag[i]);
//			qL.add(mg.mag[i]);
//			cb.enqueue(mg.mag[i]);
//			}
//		System.out.println();
/*		
		System.out.println("Kolejka Unlimited:");
		qU.ShowAll();
		
		System.out.println("Kolejka Limited:");
		qL.showAll();
		qL.clear();
		System.out.println("Kolejka Limited po wyczyszczeniu:");
		qL.showAll();
		
		System.out.println("Kolejka Cycled Buffor:");
		cb.showAll();
		*/		
		while(true)	{
			//		for(int i=0;i<mg.mag.length;i++)
			try {
			qL.add(mg.mag[0]);
			qL.get();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		}
	
	// TU JEST SPECJALNIE BLAD :D
	
}
