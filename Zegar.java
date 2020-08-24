package FirstSemestr;

public class Zegar {
		private int hh,mm,ss;
		public static int ile=0;
		
	public Zegar() {
		hh=23;
		mm=15;
		ss=30;
		ile++;
	}

	
	public Zegar(int h, int m, int s) {
		hh=h;
		mm=m;
		ss=s;
		ile++;
		poprawa();
	}
	
	public static void ile() {
		System.out.println("iloœæ zegar: " + ile);
	}
	
	public void getGodz(){
		System.out.println(hh);
	}
	
	public void getMin(){
		System.out.println(mm);
	}
	
	public void getSek(){
		System.out.println(ss);
	}
	
	public void setGodz(int h){
		hh=h;
	}
	
	public void setMin(int m){
		mm=m;
	}
	
	public void setSek(int s){
		ss=s;
	}
	
	public String toString() {
		return "Czas: " + hh + ":" + mm + ":" + ss; 
	}
	
	public boolean equals(int h, int m, int s) {
		boolean x=true;
		if(hh==h) {
			if(mm==m)
				if(ss==s)
					x= true;
		}
		else 
			x= false;
		return x;
	}
	
	public void plusGodz() {
		hh++;
		poprawa();
	}
	
	public void minusGodz() {
		hh--;
		poprawa();
	}
	
	public void plusSek() {
		ss++;
		poprawa();
	}
	
	public void plusSek(int n) {
		ss=ss+n;
		poprawa();
	}
	
	public int compare(int h, int m, int s) {	
		int x=0;
		if(hh>h)
			x= 1;
		else
			if(hh==h) {
				if(mm>m)
					x= 1;
				else
					if(mm==m)
						if(ss>s)
							x= 1;
						else
							if(ss==s)
								x= 0;
			}
			else
				x= -1;
		return x;
	}
	
	public void poprawa(){
		while(hh>24||hh<0||mm>60||mm<0||ss>60||ss<0) {
			if(ss>60) {
				ss=ss-60;
				mm++;
			}
			if (ss<0) {
				ss=ss+60;
				mm--;
			}
			if(mm>60) {
				mm=mm-60;
				plusGodz();
			}
			if(mm<0) {
				mm=mm+60;
				minusGodz();
			}
			if(hh>24)
				hh=hh-24;
			if(hh<0)
				hh=hh+24;
		}
	}
	
	public static void main(String[] args) {
		Zegar z1=new Zegar(10,20,30);
		System.out.println(z1.toString());
		System.out.println("equals: " + z1.equals(20,30,40));
		z1.plusGodz();
		System.out.println(z1.toString());
		Zegar z2=new Zegar(15,16,17);
		z2.minusGodz();
		System.out.println(z2.toString());
		Zegar z3=new Zegar(10,18,10);
		z3.plusSek();
		System.out.println(z3.toString());
		Zegar z4=new Zegar(1,2,3);
		z4.plusSek(100);
		System.out.println(z4.toString());
		System.out.println("compare: " + z4.compare(10, 20, 40));
		ile();
		Zegar z5=new Zegar(0,0,2432432);
		System.out.println(z5.toString());
	}
	
}