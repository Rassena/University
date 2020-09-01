public class ThreadTester 
{  public static void main( String[] args )
   {
      PrintThread thread1, thread2, thread3, thread4;

      thread1 = new PrintThread( "thread1" );
      thread2 = new PrintThread( "thread2" );
      thread3 = new PrintThread( "thread3" );
      thread4 = new PrintThread( "thread4" );

      System.out.println( "\nStarting threads" );

      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();

      System.out.println( "Threads started\n" );
   }
}

class PrintThread extends Thread 
{  private int sleepTime;

   // PrintThread constructor assigns name to thread 
   // by calling Thread constructor
   public PrintThread( String name )
   {
      super( name );

      // sleep between 0 and 5 seconds
      sleepTime = (int) ( Math.random() * 5000 );
      System.out.println( "Name: " + getName() + ";  sleep: " + sleepTime );
   }

   @Override public void run()
   {
      // put thread to sleep for a random interval
      try 
      {  System.out.println( getName() + " going to sleep" );
         Thread.sleep( sleepTime );
      }
      catch ( InterruptedException exception ) 
        { System.out.println( exception.toString() ); }

      // print thread name
      System.out.println( getName() + " done sleeping" );
   }
}
