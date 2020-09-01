public class ForkJoin8 { 
  public static void main(String[] args) throws InterruptedException { 
    Thread[] threads = new Thread[4];
	for(int i=0; i<threads.length; i++) {
	  final String message = "Thread " + i + " forked";
	  threads[i] = new Thread(() -> System.out.println(message));  
	} 
	for(Thread t: threads) { t.start(); }    // fork
	for(Thread t: threads) { t.join(); }     // join	  
	System.out.println("All threads joined");	  			
  }
}