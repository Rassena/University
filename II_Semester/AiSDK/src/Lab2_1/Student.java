package Lab2_1;

public class Student{
	String imie;
	String nazwisko;
	int indeks;
	double ocena;

	public Student(int in,String nazw, String im,double oc) {
		imie = im;
		nazwisko=nazw;
		indeks = in;
		ocena = oc;
	}
	
	@Override
	public String toString() {
		String s = indeks + " " + nazwisko + " " + imie + " " + ocena;
		return s;
	}
	
	public int compareTo(Student st) {
		if(this.nazwisko.equals(st.nazwisko)) {
			return this.imie.compareTo(imie);
		}
		else {
			return this.nazwisko.compareTo(nazwisko);
		}
	}

	public void setOcena(double oc) {
		ocena = oc;
	}
	
	public boolean equals(Student st) {
		boolean equals=false;
		if(this.compareTo(st)==0) {
			if(this.indeks==st.indeks) {
				if(this.ocena==st.ocena)
					equals = true;
			}
		}
		return equals;
	}


}
