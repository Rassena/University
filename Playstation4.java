package FirstSemestr;

public class Playstation4 extends Sklep{
	String platforma;
	
	public Playstation4(String t, double c, int i) {
		super(t,c,i);
		platforma= "PS4";

	}

	public String getplatforma() {
		return platforma;
	}
	
	public boolean zaDrogie(double c) {
		boolean x=false;
		if(c>140)
			x=true;
		return x;
	}
}
