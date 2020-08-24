package FirstSemestr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Echo {
	static BufferedReader br;
	
	public static void echo() {
		while(true) {
			br=new BufferedReader (new InputStreamReader(System.in));
			String wiersz = null;
			
			try {
				wiersz = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			StringTokenizer wers = new StringTokenizer(wiersz);
			String []tab;
		      tab=new String[wers.countTokens()];
		      int i=0;
		      while (wers.hasMoreElements()) {
		         tab[i]= wers.nextToken();
		         i++;
		     }
		      int n=tab.length -1;
		      while(n>=0) {
		    	  System.out.print(tab[n] + " ");
		    	  n--;
		      }
		      System.out.println();
		}
	}
	
	
	
	public static void main (String[]args) {
		echo();
	}

}
