package FirstSemestr;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Slownik {
	static BufferedReader br;
	static BufferedWriter bw;
	static String []tabalf;
	static int []tabilosc;
	static ArrayList<String> slownik;
	
	public static void wyswieltalf() {
		for(int i=0;i<tabalf.length;i++)
			System.out.print(tabalf[i] + " ");
		System.out.println();
	}
	
	public static void wyswieltilosc() {
		for(int i=0;i<tabilosc.length;i++)
			System.out.print(tabilosc[i] + " ");
		System.out.println();
	}
	
	
	
	public static void alfabet() {
		tabalf[0]="A";
		tabalf[1]="¥";
		tabalf[2]="B";
		tabalf[3]="C";
		tabalf[4]="Æ";
		tabalf[5]="D";
		tabalf[6]="E";
		tabalf[7]="Ê";
		tabalf[8]="F";
		tabalf[9]="G";
		tabalf[10]="H";
		tabalf[11]="I";
		tabalf[12]="J";
		tabalf[13]="K";
		tabalf[14]="L";
		tabalf[15]="£";
		tabalf[16]="M";
		tabalf[17]="N";
		tabalf[18]="Ñ";
		tabalf[19]="O";
		tabalf[20]="Ó";
		tabalf[21]="P";
		tabalf[22]="R";
		tabalf[23]="S";
		tabalf[24]="Œ";
		tabalf[25]="T";
		tabalf[26]="U";
		tabalf[27]="W";
		tabalf[28]="Y";
		tabalf[29]="Z";
		tabalf[30]="";
		tabalf[31]="Z";
	}

	public static void zerowanie() {
		for(int i = 0; i<tabilosc.length; i++)
			tabilosc[i]=0;
	}
	
	public static  void dodaj(String litera){
		int i =0;
		boolean zn =false;
		for(; i<tabalf.length&&zn==false;i++) {
			if(tabalf[i].equalsIgnoreCase(litera)) {
				tabilosc[i]++;
			}
		}
	}
	
	public static void czytanie(String plik) {
		slownik = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(plik));
			bw = new BufferedWriter(new FileWriter("Slownik.txt"));
			String linia = null;
			
			
			while((linia=br.readLine())!=null) {
				String[] slowa = linia.split(" ");
			//	slownik.add(slowa[0]);
				for(int i=0; i<linia.length();i++) {
					char c = linia.charAt(i);
					String litera = Character.toString(c);
					dodaj(litera);
					}
				for(int i= 0; i<slowa.length; i ++) {
		//			boolean brak= true;
					for(int j=0; j<slownik.size(); j++)
						if(slownik.contains(slowa[i])==false) {
							slownik.add(slowa[i]);
						}
				}
			
			}
			slownik.sort(null);
			for(int i =0; i<slownik.size();i++) {
				String s= slownik.get(i);
				bw.write(s);
				bw.newLine();	
			}											
			br.close();
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	public static void main(String[]args) {
		String plik = "Test.txt";
		tabalf = new String[32];
		tabilosc = new int[32];
		alfabet();
		zerowanie();
		czytanie(plik);
		wyswieltalf();
		wyswieltilosc();
	}
	
}
