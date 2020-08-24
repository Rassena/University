package FirstSemestr;
import java.util.Scanner;
import java.util.StringTokenizer;
     
    class Test {

    	
    	private static Scanner sc;

		public static void echo() {
    		while (true) {
    			sc = new Scanner(System.in);
    		      String str = sc.nextLine();
    		      StringTokenizer st = new StringTokenizer(str);
    		      String []tab;
    		      tab=new String[st.countTokens()];
    		    //  System.out.println(st.countTokens());
    		   //   System.out.println(tab.length);
    		      int i=0;
    		      while (st.hasMoreElements()) {
    		       //  System.out.print((String) st.nextToken()+ " ");
    		         tab[i]= st.nextToken();
    		         i++;
    		     }
    		      int n=tab.length -1;
    		      while(n>=0) {
    		    	  System.out.print(tab[n] + " ");
    		    	  n--;
    		      }
    		}
    	}
    		 
   public static void main(String[] args) {
	   echo();
	   
   }
} 
    