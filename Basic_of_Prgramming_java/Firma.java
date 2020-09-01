package FirstSemestr;

public class Firma {
	Pracownik[]firma;
	int ileP;
	int ileR;
	int ileU;
	
	public Firma(int k) {
		firma=new Pracownik[k];
	}
	
	public boolean czyZatrudniony(String n) {
		boolean x = false;
		for(int i=0;i<ileP; i++) {
			if(firma[i].getNazwisko()==n) {
				x=true;
				}
			}
		return x;
	}
	
	public void zatrRob(String n, double e, double st, double lg) {
		if (czyZatrudniony(n)==true)
			System.out.println(n + " jest ju¿ zatrudniony");
		else {
			Robotnik r1= new Robotnik (n, e, st, lg);
			firma[ileP] = r1;
			ileR++;
			ileP++;
			}
	}

	public void zatrUrz(String n, double e, double p, double pr) {
		if (czyZatrudniony(n)==true)
			System.out.println(n + " jest ju¿ zatrudniony");
		else {
			Urzednik u1= new Urzednik (n,e,p,pr);
			firma[ileP]= u1;
			ileU++;
			ileP++;
		}
	}
	
	public void zwolnij(String n) {
		for(int i=0;i<ileP; i++) {
			if(firma[i].getNazwisko()==n) {
				ileP--;
				firma[i]=firma[ileP];
				firma[ileP]=null;
			}
		}
				
	}
	
	public int ileRob() {
		return ileR;
	}
	
	public int ileUrz() {
		return ileU;
	}
	
	public int ilePrac() {
		return ileP;
	}
	
	public void wydrukPrac() {
		System.out.println("Lp. nazwisko     grupa    etat");
		for(int i=0;i<ileP; i++) {
			if(firma[i].getZawod()=="u") {
				System.out.println(i +". " + firma[i].getNazwisko() + "   urzednik   " + firma[i].getEtat());
			}
			else {
				System.out.println(i +". " + firma[i].getNazwisko() + "   robotnik   " + firma[i].getEtat());
			}
		}
	}
	
	public void wydrukUrz() {
		System.out.println("Lp. nazwisko     etat");
		for(int i=0;i<ileP; i++) {
			if(firma[i].getZawod()=="u") {
				System.out.println(i +". " + firma[i].getNazwisko() + "   " + firma[i].getEtat());
			}
		}
	}
	
	public void wydrukRob() {
		System.out.println("Lp. nazwisko     etat");
		for(int i=0;i<ileP; i++) {
			if(firma[i].getZawod()=="r") {
				System.out.println(i +". " + firma[i].getNazwisko() + "    " + firma[i].getEtat());
			}
		}
	}
	
	public double sumaWyplat() {
		double suma=0;
		for(int i=0; i<ileP;i++)
			suma+=firma[i].wyplata();
		return suma;
	}
	
	public double sumaWyplatUrz() {
		double suma=0;
		for(int i=0; i<ileP;i++)
			if(firma[i].getZawod().equals("u"))
				suma+=firma[i].wyplata();
		return suma;
	}
	
	public double sumaWyplatRob() {
		double suma=0;
		for(int i=0; i<ileP;i++)
			if(firma[i].getZawod().equals("r"))
				suma+=firma[i].wyplata();
		return suma;
	}
	
	public void wydrukPlac(){
		System.out.println("Lp. nazwisko     grupa    etat    placa");
		for(int i=0;i<ileP; i++) {
			if(firma[i].getZawod()=="u") {
				System.out.println(i +". " + firma[i].getNazwisko() + "   urzednik   " + firma[i].getEtat() +"     " + firma[i].wyplata());
			}
			else {  
				System.out.println(i +". " + firma[i].getNazwisko() + "   robotnik   " + firma[i].getEtat()+ "     " + firma[i].wyplata());
			}
		}
	}
	

	public static void main(String[]args) {
		 Firma firma1= new Firma(10);
		 firma1.zatrRob("Nowak", 1.0, 10, 100);
		 firma1.zatrRob("Nowak2", 0.75, 12, 90);
		 firma1.zatrRob("Nowak3", 0.75, 13, 120);
		 firma1.zatrRob("Nowak4", 1.25, 10, 200);
		 firma1.zatrRob("Nowak4", 1.25, 11, 220);
		 firma1.zatrUrz("Kowalski", 1.0, 1500, 20);
		 firma1.zatrUrz("Kowalski2", 1.25, 1800, 10);
		 firma1.zatrUrz("Kowalski3", 0.50, 2500, 30);
		 firma1.zatrUrz("Kowalski4", 0.75, 2200, 40);
		 System.out.println("ile zatrudnionych: " + firma1.ilePrac());
		 System.out.println("ile robotnikow: " + firma1.ileRob());
		 System.out.println("ile urzednikow: " + firma1.ileUrz());
		 System.out.println();
		 firma1.wydrukRob();
		 System.out.println();
		 firma1.wydrukUrz();
		 System.out.println();
		 System.out.println("Przed usunieciem");
		 System.out.println();
		 firma1.wydrukPrac();
		 System.out.println();
		 firma1.zwolnij("Kowalski3");
		 firma1.zwolnij("Nowak2");
		 System.out.println("Po zwolnieniu");
		 System.out.println();
		 firma1.wydrukPrac();
		 System.out.println();
		 System.out.println("Suma wyplat pracownikow: " + firma1.sumaWyplat());
		 System.out.println("Suma wyplat robotnikow: " + firma1.sumaWyplatRob());
		 System.out.println("Suma wyplat urzednikow: " + firma1.sumaWyplatUrz());
		 System.out.println();
		 firma1.wydrukPlac();
	}
	
}





