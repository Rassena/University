package FirstSemestr;

public class Playstation3 extends Sklep{
	String platforma;
	
	public Playstation3(String t, double c, int i) {
		super(t,c,i);
		platforma= "PS3";

	}

	public String getplatforma() {
		return platforma;
	}
	
	public boolean zaDrogie(double c) {
		boolean x=false;
		if(c>100)
			x=true;
		return x;
	}

}
