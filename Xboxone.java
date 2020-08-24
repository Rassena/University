package FirstSemestr;

public class Xboxone extends Sklep{
	String platforma;
	
	public Xboxone(String t, double c, int i) {
		super(t,c,i);
		platforma= "Xboxone";

	}

	public String getplatforma() {
		return platforma;
	}
	
	public boolean zaDrogie(double c) {
		boolean x=false;
		if(c>130)
			x=true;
		return x;
	}
}
