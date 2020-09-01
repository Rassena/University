public class ForkJoin
{ 
	public static void main(String[] args) throws InterruptedException
	{ Thread[] threads = new Thread[4];
	  for(int i=0; i<threads.length; i++)
	  { final String message = "Thread " + i + " forked";
	  	threads[i] = new Thread(new Runnable(){
	  	  @Override public void run()
	  	  { System.out.println(message); }	
	    });
	  } 
	  for(Thread t: threads) { t.start(); }    // fork
	  for(Thread t: threads) { t.join(); }     // join	  
	  System.out.println("All threads joined");	  			
	}
}