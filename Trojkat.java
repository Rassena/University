package FirstSemestr;

public class Trojkat {
	int bok1;
	int bok2;
	int bok3;
	boolean mozliwy;
	
	public Trojkat(int a, int b, int c) {
		if (a>b) {
			if (a>c) {
				if (b>c) {
					bok1 =a;
					bok2 =b;
					bok3 =c;
				}
				else {
					bok1 =a;
					bok2 =c;
					bok3 =b;
				}
			}
			else {
				bok1 =c;
				bok2 =a;
				bok3 =b;
			}
		}
		else {
			if (a>c) {
					bok1 =b;
					bok2 =a;
					bok3 =c;
			}
			else {
				if (b>c) {
					bok1 =b;
					bok2 =c;
					bok3 =a;
				}
				else {
					bok1 =c;
					bok2 =b;
					bok3 =a;
				}
			}
		}
		
	}
	
	public void mozliwy() {
		if (bok3 + bok2 > bok1) {
			System.out.println("trojkat jest mozliwy");
			mozliwy = true;
		}
		else {
			System.out.println("trojkat nie jest mozliwy");
			mozliwy = false;
		}
		
	}

	public void obwod() {
		if (mozliwy== true) {
			int ob = bok1+bok2+bok3;
			System.out.println("Obwod = "+ ob);
		}
		else 
			System.out.println("Brak");
	}
	
	
	public void rodzaj() {
		if (mozliwy== true) {
			if (bok1 == bok2) {
				if (bok1 == bok3)
					System.out.println("Trojkat rownoboczny");
				else
					System.out.println("Trojkat rownoramienny");
			}
			else
			if (bok3*bok3 +bok2*bok2 == bok1*bok1)
				System.out.println("Trojkat prostokaty");
			else
				System.out.println("Trojkat dowolny");
		}
		else 
			System.out.println("Brak");
	}	
	
	
	public void pole() {
		if (mozliwy == true) {
			double p = 0.5 * (bok1 + bok2 + bok3);
			double P = Math.sqrt(p*(p- bok1)*(p-bok2)*(p-bok3));
			System.out.println("Pole = " + P);
		}
		else 
			System.out.println("Brak");
	}

	public void boki() {
		System.out.print("Boki trojkata to: " + bok1 + " " + bok2 + " " + bok3);
	}
	
	public static void main(String[] args) {
		Trojkat f1= new Trojkat(5,6,6);
		f1.mozliwy();
		f1.obwod();
		f1.rodzaj();
		f1.pole();
		f1.boki();
	}
}
