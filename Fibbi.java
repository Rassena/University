package Cwiczenia;

public class Fibbi {

	
	
	
	
	public static void main(String[]args) {
		double a=1;
		double b=1;
		double c;
		System.out.println(1);
		System.out.println(1);
		for(int i=0;i <1000; i++) {
			c=a+b;
			a=b;
			b=c;
			String x = String.format("%.0f", c);
			System.out.println(x);
		}
		
		
	}
	
	
}
