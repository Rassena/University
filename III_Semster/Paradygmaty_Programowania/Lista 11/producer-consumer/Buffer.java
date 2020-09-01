public class Buffer {
           //Message sent from producer to consumer.
   private String msg;
           //true if consumer should wait for producer to send message,
           //false if producer should wait for consumer to retrieve message.
   private boolean empty = true;

   public synchronized void put(String msg) {
          //Wait until message has been retrieved.
      while (!empty) {
         try { wait();} catch (InterruptedException e) {}
      }
         System.out.println(Thread.currentThread().getName() + " puts " +msg); 
          //Store message.
      this.msg = msg;
         //Toggle status.
      empty = false;
         //Notify consumer that status has changed.
       notifyAll();   
   }  

   public synchronized String take() {
         //Wait until message is available.
      while (empty) {
         try { wait(); } catch (InterruptedException e) {}
      }
         //Toggle status.
      empty = true;
         //Notify producer that status has changed.
      notifyAll();
         System.out.println(Thread.currentThread().getName() + " takes " +msg); 
      return msg;
    }
}
