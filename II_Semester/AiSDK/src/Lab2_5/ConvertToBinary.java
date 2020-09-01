package Lab2_5;

import java.util.Scanner;

public class ConvertToBinary {
	Scanner sc;
	private int number=0;
	
	
	public ConvertToBinary() {
	}
	
	public void Read() {
		boolean end=false;
		while(!end) {
			System.out.print("Liczba naturalna: ");
			sc = new Scanner(System.in);
			if(sc.hasNextInt()) {
				number = sc.nextInt();
				convert();
			}
			else {
				end=true;
				System.out.println("END");
			}
		}
		sc.close();
	}
	
	private void convert() {
		StackUnlimited<Integer> sU = new StackUnlimited<Integer>();
		System.out.print("Binarnie: ");
		do {
			sU.add(number%2);
			number/=2;	
		} while(number!=0);
		
		while(!sU.isEmpty()) {
			System.out.print(sU.get());
		}
		System.out.println();
	}
	
	

}
