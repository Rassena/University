package FirstSemestr;
import java.util.ArrayList;

public class Firma2 {
	int ileP;
	int ileR;
	int ileU;
	private ArrayList<Pracownik> lista;
	
	public Firma2() {
		lista = new ArrayList<Pracownik>();
	}
	
	public boolean czyZatrudniony(String n) {
		boolean x = false;
		for(int i=0;i<lista.size(); i++) {
			if(lista.get(i).nazwisko==n) {
				x=true;
				}
			}
		return x;
	}
	
	public void zatrUrz(String n, double e, double p, double pr) {
		if (czyZatrudniony(n)==true)
			System.out.println(n + " jest ju¿ zatrudniony");
		else {
			Urzednik u1= new Urzednik (n,e,p,pr);
			lista.add(u1);
			ileU++;
			ileP++;
		}
	}
	
	
	public void zatrRob(String n, double e, double st, double lg) {
		if (czyZatrudniony(n)==true)
			System.out.println(n + " jest ju¿ zatrudniony");
		else {
			Robotnik r1= new Robotnik (n, e, st, lg);
			lista.add(r1);
			ileR++;
			ileP++;
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
	
	public void zwolnij(String n) {
		boolean x=false;
		for(int i=0;i<ileP; i++) {
			if(lista.get(i).nazwisko==n) {
				ileP--;
				lista.remove(i);
				System.out.println("Zwolniono " + n);
				x=true;
			}
		}
		if(x==false)
				System.out.println("Nie ma pracownika  " + n);
	}
	
	public double sumaWyplat() {
		double suma=0;
		for(int i=0; i<ileP;i++)
			suma+=lista.get(i).wyplata();
		return suma;
	}
	
	public double sumaWyplatUrz() {
		double suma=0;
		for(int i=0; i<ileP;i++)
			if(lista.get(i).getZawod().equals("u"))
				suma+=lista.get(i).wyplata();
		return suma;
	}
	
	public double sumaWyplatRob() {
		double suma=0;
		for(int i=0; i<ileP;i++)
			if(lista.get(i).getZawod().equals("r"))
				suma+=lista.get(i).wyplata();
		return suma;
	}
	
	public void wydrukPrac() {
		System.out.println("Lp. \t nazwisko \t grupa \t \t etat");
		for(int i=0;i<ileP; i++) {
			if(lista.get(i).getZawod()=="u") {
				System.out.println(i +". \t" + lista.get(i).getNazwisko() + " \t urzednik \t" + lista.get(i).getEtat());
			}
			else {
				System.out.println(i +". \t" + lista.get(i).getNazwisko() + "\t \t robotnik \t" + lista.get(i).getEtat());
			}
		}
	}
	
	public void wydrukUrz() {
		System.out.println("Lp. \t nazwisko \t etat");
		for(int i=0;i<ileP; i++) {
			if(lista.get(i).getZawod()=="u") {
				System.out.println(i +". \t " + lista.get(i).getNazwisko() + "\t" + lista.get(i).getEtat());
			}
		}
	}
	
	public void wydrukRob() {
		System.out.println("Lp. \t nazwisko \t etat");
		for(int i=0;i<ileP; i++) {
			if(lista.get(i).getZawod()=="r") {
				System.out.println(i +". \t " + lista.get(i).getNazwisko() + " \t" + lista.get(i).getEtat());
			}
		}
	}
	
	public void wydrukPlac(){
		System.out.println("Lp.\t nazwisko \t grupa \t\t etat \t placa");
		for(int i=0;i<ileP; i++) {
			if(lista.get(i).getZawod()=="u") {
				System.out.println(i +".\t " + lista.get(i).getNazwisko() + "\t urzednik \t " + lista.get(i).getEtat() +"\t " + lista.get(i).wyplata());
			}
			else {  
				System.out.println(i +".\t " + lista.get(i).getNazwisko() + "\t\t robotnik \t " + lista.get(i).getEtat()+ "\t " + lista.get(i).wyplata());
			}
		}
	}
	
	public static void main(String[]Args) {
		Firma2 lista= new Firma2();
		lista.zatrRob("Nowak2", 0.75, 12, 90);
		lista.zatrRob("Nowak3", 0.75, 13, 120);
		lista.zatrRob("Nowak4", 1.25, 10, 200);
		lista.zatrRob("Nowak4", 1.25, 11, 220);
		lista.zatrUrz("Kowalski", 1.0, 1500, 20);
		lista.zatrUrz("Kowalski2", 1.25, 1800, 10);
		lista.zatrUrz("Kowalski3", 0.50, 2500, 30);
		lista.zatrUrz("Kowalski4", 0.75, 2200, 40);
		System.out.println();
		System.out.println("Ile zatrudnionych:  " + lista.ilePrac());
		System.out.println("Ile robotnikow:     " + lista.ileRob());
		System.out.println("Ile urzednikow:     " + lista.ileUrz());
		System.out.println("Wydruk pracownikow");
		lista.wydrukPrac();
		System.out.println("Wydruk robotnikow: ");
		lista.wydrukRob();
		System.out.println("Wydruk urzednikow: ");
		lista.wydrukUrz();
		System.out.println("Wydruk Plac: ");
		lista.wydrukPlac();
		System.out.println();
		lista.zwolnij("Nowak2");
		lista.zwolnij("Nowak2");
	}
	
}
