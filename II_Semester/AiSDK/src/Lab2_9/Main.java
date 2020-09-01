package Lab2_9;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	static Hash hash;
	
	
	private static void test(int maxValue,int repeats) {
		Random r = new Random();
		ArrayList<Integer> keys = new ArrayList<Integer>();
		
		Hash hash107= new Hash(14291);
		Hash hash207= new Hash(14291);
		Hash hash108= new Hash(12531);
		Hash hash208= new Hash(12511);
		Hash hash109= new Hash(11111);
		Hash hash209= new Hash(11111);
		
		
		for(int i =0;i<10000;i++) {
			int value = r.nextInt(maxValue+1);
			int key = r.nextInt(maxValue+1);
			while(keys.contains(key))
				key = r.nextInt(maxValue+1);
			hash107.put(key, value);
			hash108.put(key, value);
			hash109.put(key, value);
			hash207.put2(key, value);
			hash208.put2(key, value);
			hash209.put2(key, value);
		}
		
		for(int i=0;i<repeats;i++) {
			int key = r.nextInt(maxValue+1);
			hash107.get(key);
			hash108.get(key);
			hash109.get(key);
			hash207.get2(key);
			hash208.get2(key);
			hash209.get2(key);
		}
		
		System.out.println("alfa 0,7:");
//		System.out.println("Trafione: " + hash107.trafione);
		System.out.println("Srednia trafionych: " + (1.0*hash107.probyTrafionych/hash107.trafione));
//		System.out.println("Chybione: " + (repeats - hash107.trafione));
		System.out.println("Srednia Chybionych: " + (1.0*hash107.probyChybionych/(repeats - hash107.trafione)));
		System.out.println("Srednia: " + (1.0*(hash107.probyTrafionych+hash107.probyChybionych)/repeats));
		System.out.println();
//		System.out.println("Trafione: " + hash207.trafione);
		System.out.println("Srednia trafionych: " + (1.0*hash207.probyTrafionych/hash207.trafione));
//		System.out.println("Chybione: " + (repeats - hash207.trafione));
		System.out.println("Srednia Chybionych: " + (1.0*hash207.probyChybionych/(repeats - hash207.trafione)));
		System.out.println("Srednia: " + (1.0*(hash207.probyTrafionych+hash207.probyChybionych)/repeats));
		
		System.out.println();
		
		System.out.println("alfa 0,8:");
//		System.out.println("Trafione: " + hash108.trafione);
		System.out.println("Srednia trafionych: " + (1.0*hash108.probyTrafionych/hash108.trafione));
//		System.out.println("Chybione: " + (repeats - hash108.trafione));
		System.out.println("Srednia Chybionych: " + (1.0*hash108.probyChybionych/(repeats - hash108.trafione)));
		System.out.println("Srednia: " + (1.0*(hash107.probyTrafionych+hash108.probyChybionych)/repeats));
		System.out.println();
//		System.out.println("Trafione: " + hash208.trafione);
		System.out.println("Srednia trafionych: " + (1.0*hash208.probyTrafionych/hash208.trafione));
//		System.out.println("Chybione: " + (repeats - hash208.trafione));
		System.out.println("Srednia Chybionych: " + (1.0*hash208.probyChybionych/(repeats - hash208.trafione)));
		System.out.println("Srednia: " + (1.0*(hash208.probyTrafionych+hash208.probyChybionych)/repeats));
		
		System.out.println();
		
		System.out.println("alfa 0,9:");
//		System.out.println("Trafione: " + hash109.trafione);
		System.out.println("Srednia trafionych: " + (1.0*hash109.probyTrafionych/hash109.trafione));
//		System.out.println("Chybione: " + (repeats - hash109.trafione));
		System.out.println("Srednia Chybionych: " + (1.0*hash109.probyChybionych/(repeats - hash109.trafione)));
		System.out.println("Srednia: " + (1.0*(hash109.probyTrafionych+hash109.probyChybionych)/repeats));
		System.out.println();
//		System.out.println("Trafione: " + hash209.trafione);
		System.out.println("Srednia trafionych: " + (1.0*hash209.probyTrafionych/hash209.trafione));
//		System.out.println("Chybione: " + (repeats - hash209.trafione));
		System.out.println("Srednia Chybionych: " + (1.0*hash209.probyChybionych/(repeats - hash209.trafione)));
		System.out.println("Srednia: " + (1.0*(hash209.probyTrafionych+hash209.probyChybionych)/repeats));
	}
	
	private static void addElements() {
		hash.put(1,79);
		hash.put(997,15);
		hash.put(112,145);
		hash.put(97,56);
		hash.put(32,47);
		hash.put(531,218);
		hash.put(24, 62);
		hash.put(12,719);
	}
	
	private static void addElements2() {
		hash.put2(1,79);
		hash.put2(997,15);
		hash.put2(112,145);
		hash.put2(97,56);
		hash.put2(32,47);
		hash.put2(531,218);
		hash.put2(24, 62);
		hash.put2(12,719);
	}
	
	private static void hashLined() {
		addElements();
		
		System.out.println("Hash liniowy: ");
		hash.dump();
//		System.out.println("hash size: " + hash.size());
//		System.out.println("Amount of elements: "  + hash.getAmountOfElements());
		
		System.out.println("Pobrano: " + hash.get(24));
//		System.out.println();
		
//		hash.dump();
//		System.out.println("hash size: " + hash.size());
//		System.out.println("Amount of elements: "  + hash.getAmountOfElements());
		
		System.out.println("Usunieto: " + hash.remove(24));
		System.out.println("Usunieto: " + hash.remove(97));
		System.out.println("Usunieto: " + hash.remove(32));
		System.out.println("Usunieto: " + hash.remove(531));
		System.out.println();
		
		hash.dump();
//		System.out.println("hash size: " + hash.size());
//		System.out.println("Amount of elements: "  + hash.getAmountOfElements());
		
	}
	
	private static void hashDouble() {
		addElements2();
		
		System.out.println("Hash podwojny: ");
		hash.dump();
//		System.out.println("hash size: " + hash.size());
//		System.out.println("Amount of elements: "  + hash.getAmountOfElements());
		
		System.out.println("Pobrano: " + hash.get2(24));
//		System.out.println();
		
//		hash.dump();
//		System.out.println("hash size: " + hash.size());
//		System.out.println("Amount of elements: "  + hash.getAmountOfElements());
		
		System.out.println("Usunieto: " + hash.remove2(24));
		System.out.println("Usunieto: " + hash.remove2(97));
		System.out.println("Usunieto: " + hash.remove2(32));
		System.out.println("Usunieto: " + hash.remove2(531));
		System.out.println();
		
		hash.dump();
//		System.out.println("hash size: " + hash.size());
//		System.out.println("Amount of elements: "  + hash.getAmountOfElements());
		
	}
	
	
	public static void main(String[]args) {
		
		hash = new Hash(11);
		hashLined();
		
		hash = new Hash(11);
		hashDouble();
		
		test(20000,1000000);	//Pierwsze ma byc wieksze od 1000 !!
	}

}
