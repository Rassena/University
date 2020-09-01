package Lab2_6;

public class Main {
	
	static private Person[] p;
	static private Person[] sorted;
	private static void people() {
		Person p0 = new Person("Smith", "John", 67);
		Person p1 = new Person("Banks", "Stefan", 18);
		Person p2 = new Person("Nash", "Nahla", 22);
		Person p3 = new Person("Webster", "Conna", 28);
		Person p4 = new Person("Farrow", "Karan", 20);
		Person p5 = new Person("Webster", "Charly", 46);
		Person p6 = new Person("Smith", "John", 30);
		
		p = new Person[7];
		
		p[0] = p0;
		p[1] = p1;
		p[2] = p2;
		p[3] = p3;
		p[4] = p4;
		p[5] = p5;
		p[6] = p6;	
	}

	private static void showInsertSort(Comparator<Person> comp) {
		InsertSort<Person> is = new InsertSort<Person>(comp);		
		sorted = (Person[]) is.sort(p);	
		for(int i =0; i<sorted.length;i++) {
			System.out.println(sorted[i]);
		}
		System.out.println();
	}		
	
	private static void showSelectSort(Comparator<Person> comp) {
		SelectSort<Person> ss = new SelectSort<Person>(comp);
		sorted = (Person[]) ss.sort(p);
		for(int i = 0;i<sorted.length;i++) {
			System.out.println(sorted[i]);
		}
		System.out.println();
	}
	
	public static void main(String[]args) {

		people();
		
		Comparator<Person> comp1 = new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o1.getNazwisko().compareTo(o2.getNazwisko());
			}
		};
		
		Comparator<Person> comp2 = new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o1.getImie().compareTo(o2.getImie());
			}
		};
		
		Comparator<Person> comp3 = new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				if(o1.getWiek()<o2.getWiek())
					return -1;
				else
					if(o1.getWiek()>o2.getWiek())
						return 1;
						else
							return 0;
			}
		};
		
		Comparator<Person> compare = new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				if(o1.getNazwisko().compareTo(o2.getNazwisko())==0)
					if(o1.getImie().compareTo(o2.getImie())==0)
						if(o1.getWiek()<o2.getWiek())
							return -1;
						else
							if(o1.getWiek()<o2.getWiek())
								return 1;
								else
									return 0;
					else
						return o1.getImie().compareTo(o2.getImie());
				else
					return o1.getNazwisko().compareTo(o2.getNazwisko());			
			}
		};
			
		CompoundComparator<Person> compound = new CompoundComparator<Person>();
		compound.addComparator(comp1);
		compound.addComparator(comp2);
		compound.addComparator(comp3);
	
		System.out.println("Posortowane nazwiskami:");
		showInsertSort(comp1);
		
		System.out.println("Posortawane imionami:");
		showInsertSort(comp2);

		System.out.println("Posortowane wiekiem:");
		showInsertSort(comp3);
		
		System.out.println("Posortowane compalatorem zlozonym:");
		showInsertSort(compound);
		
		System.out.println("Posortowane zlozonym compareTo:");
		showInsertSort(compare);
		
		System.out.println("Posortowane nazwiskami:");
		showSelectSort(comp1);
		
		System.out.println("Posortawane imionami:");
		showSelectSort(comp2);

		System.out.println("Posortowane wiekiem:");
		showSelectSort(comp3);
		
		System.out.println("Posortowane compalatorem zlozonym:");
		showSelectSort(compound);
		
		System.out.println("Posortowane zlozonym compareTo:");
		showSelectSort(compare);
	}	
}
