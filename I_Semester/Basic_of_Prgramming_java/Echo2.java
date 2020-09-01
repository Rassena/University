package FirstSemestr;
import java.util.Scanner;
import java.util.StringTokenizer;
     
    class Echo2 {
    	static Scanner sc;

		public static void echo2() {
    		while (true) {
    			sc = new Scanner(System.in);
    		      String wiersz = sc.nextLine();
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
    		 
   public static void main(String[] args) {
	   echo2();
	   
   }
   

} 