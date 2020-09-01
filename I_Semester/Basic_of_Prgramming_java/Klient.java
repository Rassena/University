package FirstSemestr;
import java.util.ArrayList;

public class Klient {
	String platforma;
	double bilans;
	private ArrayList<Sklep> gry;
	double portfel=0;

	public Klient() {
		gry = new ArrayList<Sklep>();
	}
	
	public boolean czyjest(String t) {
		boolean x=false;
		for(int i=0; i<gry.size()&&x==false;i++) {
			if(gry.get(i).gettytul()==t)
				x=true;
		}
		return x;
	}
	
	public boolean zaDrogie(String pl, double c) {
		boolean x= false;
		for(int i=0; i<gry.size();i++)
			if(gry.get(i).getplatforma()==pl)
				if(gry.get(i).zaDrogie(c)==true)
					x=true;
		return x;
	}
	
	public int index(String t) {
		int index =-1;
		boolean zn=false;
		for(int i=0; i<gry.size()&&zn==false;i++) {
			if(gry.get(i).gettytul()==t){
				zn=true;
				index=i;				
			}
		}
		return index;
	}
	
	public void dodajPS4(String t, double c, int ilosc) {
		Playstation4 ps4 = new Playstation4(t,c,ilosc);
		gry.add(ps4);
	}
	
	public void dodajPS3(String t, double c, int ilosc) {
		Playstation3 ps3 = new Playstation3(t,c,ilosc);
		gry.add(ps3);
	}
	
	public void dodajXbox360(String t, double c, int ilosc) {
		Xbox360 xbox360 = new Xbox360(t,c,ilosc);
		gry.add(xbox360);
	}
	
	public void dodajXboxone(String t, double c, int ilosc) {
		Xboxone xboxone = new Xboxone(t,c,ilosc);
		gry.add(xboxone);
	}
	
	public void sprzedaj(String t, double c, String pl, int ilosc) {
		double wartosc=c*1.2;
		int i;
		if (czyjest(t)==true) {
			i= index(t);
			if(gry.get(i).getplatforma()==pl) {
				c= gry.get(i).getcena();
				c=c*0.8;
				System.out.println("Mamy juz " + t + ", kupimy kazda za: " + c);
				gry.get(i).dodaj(ilosc);
			}
			else {
				System.out.println("Mamy juz " + t + " na inna konsole, nie kupimy jej");
				c=0;
				ilosc=0;
			}
		}
		else
			if (zaDrogie(pl,c)==true) {
				c=60;
				System.out.println("Za drogo chcesz sprzedac " + t + ", kupimy za " + c + " za szt.");
				wartosc=c;
			}
			else
				if(pl=="PS3") {
					dodajPS3(t,wartosc,ilosc);
				}
				else
					if(pl=="PS4") {
						dodajPS4(t,wartosc,ilosc);
					}
					else
						if(pl=="Xbox360") {
							dodajXbox360(t,wartosc,ilosc);
						}
						else
							if(pl=="Xboxone") {
								dodajXboxone(t,wartosc,ilosc);
							}
							else {
								System.out.println("Nie sprzedajemy i nie kupujemy gier na: " + pl);	
								c=0;
								ilosc=0;
							}
		
			portfel+=c*ilosc;
			System.out.println("Sprzedales " + ilosc + " " + t + " w sumie za " + c*ilosc);
	}
	
	public void kup(String t, int ile) {
		int ilosc;
		int i;
		double wydatek=0;
		if(czyjest(t)==true) {
			i=index(t);
			ilosc=gry.get(i).getilosc();
			if(ilosc>ile) {
				wydatek =ile*gry.get(i).getcena();
				gry.get(i).dodaj(-ile);			
			}
			else
				if(ilosc==ile) {
					wydatek =ile*gry.get(i).getcena();
					gry.remove(i);
				}
				else
					if(ilosc<ile) {
						System.out.print("Nie ma "+ ile + " " + t + ". ");
						ile=ilosc;
						wydatek =ile*gry.get(i).getcena();
						gry.remove(i);
					}
			portfel-=wydatek;
			System.out.println("Kupiles " + ile + "  " + t + " za " + wydatek);
		}
		else
			System.out.println("Nie mamy " + t +" w sklepie");
		
	}
	
	public void pokaz() {
		String s="0";
		System.out.println(".Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			String pl=gry.get(i).getplatforma();
			if(pl=="PS3") {
				s=i +". " +  gry.get(i).toString() + "\t" + "Playstation3";
			}
			else
				if(pl=="PS4") {
					s=i +". " +   gry.get(i).toString() + "\t" + "Playstation4";
				}
				else
					if(pl=="Xbox360") {
						s=i +". " +   gry.get(i).toString() + "\t" + "Xbox360";
					}
					else
						if(pl=="Xboxone") {
							s=i +". " +   gry.get(i).toString() + "\t" + "Xboxone";
						}
			System.out.println(s);
		}
	}
	
	public void pokazPS3() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			String pl=gry.get(i).getplatforma();
			if(pl=="PS3") {
				s=i +". " +  gry.get(i).toString() + "\t" + "Playstation3";
				System.out.println(s);
			}
		}
	}
	
	public void pokazPS4() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			String pl=gry.get(i).getplatforma();
			if(pl=="PS4") {
				s=i +". " +  gry.get(i).toString() + "\t" + "Playstation4";
				System.out.println(s);
			}
		}
	}
	
	public void pokazXbox360() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			String pl=gry.get(i).getplatforma();
			if(pl=="Xbox360") {
				s=i +". " +  gry.get(i).toString() + "\t" + "Xbox360";
				System.out.println(s);
			}
		}
	}
	
	public void pokazXboxone() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			String pl=gry.get(i).getplatforma();
			if(pl=="Xboxone") {
				s=i +". " +  gry.get(i).toString() + "\t" + "Xboxone";
				System.out.println(s);
			}
		}
	}
	
	public void bilans() {
		if(portfel<=0)
			System.out.println("Wydales " + -portfel);
		else
			System.out.println("Zarobiles " + portfel);
	}
	
	

	public static void main(String[]args) {
		Klient zakupy = new Klient();
		zakupy.dodajPS4("God of War", 60, 5);
		zakupy.dodajPS4("God of War 2", 80, 8);
		zakupy.dodajPS3("God of War 3", 100, 12);
		zakupy.dodajPS4("God of War 4", 140, 1);
		//zakupy.dodajPS3("Okami", 40, 3);
		zakupy.dodajXbox360("Gears", 60, 3);
		zakupy.dodajXbox360("Gears 2", 70, 5);
		zakupy.dodajXbox360("Gears 3", 90, 2);
		zakupy.dodajXboxone("Gears 4", 120, 1);
		zakupy.dodajXboxone("Forza 4", 130, 2);
		zakupy.pokaz();
		System.out.println();
		zakupy.sprzedaj("Fifa 2017", 150,"Xboxone", 2);
		zakupy.sprzedaj("Fifa 2016", 80,"Xbox360", 3);
		zakupy.sprzedaj("Fifa 2015", 100,"PS3", 1);
		zakupy.sprzedaj("Okami",60,"Nintendo",2);
		zakupy.sprzedaj("Fifa 2015", 100,"PS4", 1);
		zakupy.kup("God of War",2);
		zakupy.kup("God of War 4",2);
		zakupy.kup("Fifa 2018", 1);
		zakupy.kup("Forza 4", 2);
		zakupy.kup("Gears 3", 1);
		System.out.println();
		zakupy.pokazPS3();
		System.out.println();
		zakupy.pokazPS4();
		System.out.println();
		zakupy.pokazXbox360();
		System.out.println();
		zakupy.pokazXboxone();
		System.out.println();
		zakupy.pokaz();
		System.out.println();
		zakupy.bilans();
	}
	
}
