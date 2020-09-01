package FirstSemestr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scrabble {
	static BufferedReader br;
	static int []tabpkt;
	static String []tabalf;
	static int wynik;
	
	public static void dodaj(int x) {
		wynik+=x;
	}
	
	public static int wynik() {
		return wynik;
	}
	
	public static void punkty() {
		int i =0;
		for(;i<=8;i++) 
			tabpkt[i]=1;
		for(;i<=16;i++) 
			tabpkt[i]=2;
		for (;i<=22;i++)
			tabpkt[i]=3;
		for (;i<=28;i++)
			tabpkt[i]=5;
		tabpkt[29]=6;
		tabpkt[30]=7;
		tabpkt[31]=9;
	}
	
	public static void alfabet() {
		tabalf[0]="A";
		tabalf[1]="E";
		tabalf[2]="I";
		tabalf[3]="N";
		tabalf[4]="O";
		tabalf[5]="R";
		tabalf[6]="S";
		tabalf[7]="W";
		tabalf[8]="Z";
		tabalf[9]="C";
		tabalf[10]="D";
		tabalf[11]="K";
		tabalf[12]="L";
		tabalf[13]="M";
		tabalf[14]="P";
		tabalf[15]="T";
		tabalf[16]="Y";
		tabalf[17]="B";
		tabalf[18]="G";
		tabalf[19]="H";
		tabalf[20]="J";
		tabalf[21]="£";
		tabalf[22]="U";
		tabalf[23]="¥";
		tabalf[24]="Ê";
		tabalf[25]="F";
		tabalf[26]="Ó";
		tabalf[27]="Œ";
		tabalf[28]="¯";
		tabalf[29]="Æ";
		tabalf[30]="Ñ";
		tabalf[31]="";
		//tabalf[]= {A,E,I,N,O,R,S,W,Z,C,D,K,L,M,P,T,Y,B,G,H,J,£,U,¥,Ê,F,Ó,Œ,¯,Æ,Ñ,};
	}
	
	public static void wyswieltalf() {
		for(int i=0;i<tabalf.length;i++)
			System.out.print(tabalf[i] + " ");
		System.out.println();
	}
	
	public static void wyswieltpkt() {
		for(int i=0;i<tabpkt.length;i++)
			System.out.print(tabpkt[i] + " ");
		System.out.println();
	}
	
	public static void czytanie() {
		boolean koniec =false;
		String wiersz = null;
			while(!koniec) {
				br=new BufferedReader (new InputStreamReader(System.in));
				try {
					wiersz = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!wiersz.equals("END")) {
					for (int i=0; i< wiersz.length(); i++) {
						for(int j=0; j<tabalf.length;j++) {
							char c= wiersz.charAt(i);
							String s = Character.toString(c);
							if(s.compareToIgnoreCase(tabalf[j])==0) {
								dodaj(tabpkt[j]);
							}
						}
					}
					System.out.println("punkty za " + wiersz +" to: " + wynik());
					wynik=0;
				}		
				else
					koniec =true;
			}
		}
	
	public static void main(String[]args) {
		tabpkt=new int[32];
		tabalf=new String[32];
		punkty();
		alfabet();
		wyswieltalf();
		wyswieltpkt();
		czytanie();
		}
	
}
