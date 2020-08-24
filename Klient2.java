package FirstSemestr;
import java.util.HashMap;

public class Klient2 {
	String platforma;
	double bilans;
	private HashMap<Integer,Sklep> gry;
	double portfel=0;
	int i=0;
	
	public Klient2() {
		gry = new HashMap<Integer,Sklep>();
	}
	
	public int gier() {
		return i;
	}
		
	public int kod(String pl) {
		int kod;
		if(pl=="PS4")
			kod=400;
		else
			if(pl=="PS3")
				kod=300;
			else
				if(pl=="Xbox360")
					kod=200;
				else
					if(pl=="Xboxone")
						kod=100;
					else
						kod= 0;
		return kod;
	}
	
	public int nr(String t, String pl) {
		int nr=0;
		int kod =kod(pl);
		for(int i=0;i<gry.size();i++) {
			if(gry.get(kod+i)!=null)
				if(gry.get(kod+i).gettytul()==t) {
					nr=kod+i;
					i+=gry.size();
				}
		}
		return nr;
	}
	
	public boolean zaDrogie(String pl, double c) {
		boolean x= false;
		int kod=kod(pl);
		for(int i=0; i<gry.size();i++)
			if(gry.get(kod+i)!=null)
				if(gry.get(kod+i).zaDrogie(c)==true)
					x=true;
		return x;
	}
	
	public boolean czyjest(String t, String pl) {
		boolean x=false;
		int kod= kod(pl);
		for(int i=0; i<gry.size();i++) {
			if(gry.get(kod+i)!=null)
				if(gry.get(kod+i).gettytul()==t)
					x=true;
		}
		return x;
	}
	
	public void dodajPS4(String t, double c, int ilosc) {
		i++;
		Playstation4 ps4 = new Playstation4(t,c,ilosc);
		gry.put(400+gier(), ps4);
	}
	
	public void dodajPS3(String t, double c, int ilosc) {
		i++;
		Playstation3 ps3 = new Playstation3(t,c,ilosc);
		gry.put(300+gier(), ps3);

	}
	
	public void dodajXbox360(String t, double c, int ilosc) {
		i++;
		Xbox360 xbox360 = new Xbox360(t,c,ilosc);
		gry.put(200+gier(), xbox360);

	}
	
	public void dodajXboxone(String t, double c, int ilosc) {
		i++;
		Xboxone xboxone = new Xboxone(t,c,ilosc);
		gry.put(100+gier(), xboxone);

	}
	
	public void sprzedaj(String t, double c, String pl, int ilosc) {
		double wartosc=c*1.2;
		int nr;
		if (czyjest(t,pl)==true) {
			nr=nr(t,pl);
				c= gry.get(nr).getcena();
				c=c*0.8;
				System.out.println("Mamy juz " + t + ", kupimy kazda za: " + c);
				gry.get(nr).dodaj(ilosc);
		}
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

	public void kup(String t, String pl, int ile) {
		int ilosc;
		double wydatek=0;
		if(czyjest(t,pl)==true) {
			int nr=nr(t,pl);
			ilosc=gry.get(nr).getilosc();
			if(ilosc>ile) {
				wydatek =ile*gry.get(nr).getcena();
				gry.get(nr).dodaj(-ile);			
			}
			else
				if(ilosc==ile) {
					wydatek =ile*gry.get(nr).getcena();
					gry.remove(nr);
				}
				else
					if(ilosc<ile) {
						System.out.print("Nie ma "+ ile + " " + t + ". ");
						ile=ilosc;
						wydatek =ile*gry.get(nr).getcena();
						gry.remove(nr);
					}
			portfel-=wydatek;
			System.out.println("Kupiles " + ile + "  " + t + " za " + wydatek);
		}
		else
			System.out.println("Nie mamy " + t +" na " + pl+ " w sklepie");
	}
	
	public void pokaz() {
		String s="0";
		System.out.println(".Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		int i=0;
		for(;i<gry.size();i++) {
			if(gry.get(300+i)!=null) {
				s=i +". " +  gry.get(300+i).toString() + "\t" + "Playstation3";
			}
			else
				if(gry.get(400+i)!=null) {
					s=i +". " +   gry.get(400+i).toString() + "\t" + "Playstation4";
				}
				else
					if(gry.get(200+i)!=null) {
						s=i +". " +   gry.get(200+i).toString() + "\t" + "Xbox360";
					}
					else
						if(gry.get(100+i)!=null) {
							s=i +". " +   gry.get(100+i).toString() + "\t" + "Xboxone";
						}
			System.out.println(s);
		}
	}
	
	public void pokazPS3() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			if(gry.get(300+i)!=null) {
				s=i +". " +  gry.get(300+i).toString() + "\t" + "Playstation3";
				System.out.println(s);
			}
		}
	}
	
	public void pokazPS4() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			if(gry.get(400+i)!=null) {
				s=i +". " +  gry.get(400+i).toString() + "\t" + "Playstation4";
				System.out.println(s);
			}
		}
	}
	
	public void pokazXbox360() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			if(gry.get(200+i)!=null) {
				s=i +". " +  gry.get(200+i).toString() + "\t" + "Xbox360";
				System.out.println(s);
			}
		}
	}
	
	public void pokazXboxone() {
		String s="0";
		System.out.println("Lp" + "\t" + "Tytul" + "\t\t" + "Cena" + "\t" + "Ilosc" + "\t" + "Platforma");
		for(int i=0;i<gry.size();i++) {
			if(gry.get(100+i)!=null) {
				s=i +". " +  gry.get(100+i).toString() + "\t" + "Xboxone";
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
		Klient2 zakupy = new Klient2();
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
		zakupy.kup("God of War","PS4",2);
		zakupy.kup("God of War 4","PS4",2);
		zakupy.kup("Fifa 2018","PS4", 1);
		zakupy.kup("Forza 4","Xboxone", 2);
		zakupy.kup("Gears 3","Xbox360", 1);
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
