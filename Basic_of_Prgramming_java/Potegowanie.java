package FirstSemestr;

public class Potegowanie {
	
	public static void potega(int a, int b) {
		double p=1;
		for(int i=b; i>=1; i--)
			p=p*a;
		System.out.println(a+"^"+ b +" = " + p);
	}
	
	public static void nieDziala(int a, int b) {
		
	}
	
	public static void main(String[]args) {
		potega(2,2);
		potega(2,4);
		potega(2,8);
		potega(2,16);
		
	}
}


