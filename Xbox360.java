package FirstSemestr;

public class Xbox360 extends Sklep{
	String platforma;
	
	public Xbox360(String t, double c, int i) {
		super(t,c,i);
		platforma= "Xbox360";

	}

	public String getplatforma() {
		return platforma;
	}

	public boolean zaDrogie(double c) {
		boolean x=false;
		if(c>90)
			x=true;
		return x;
	}
}
