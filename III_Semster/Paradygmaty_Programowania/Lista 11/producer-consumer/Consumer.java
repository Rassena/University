public class Consumer extends Thread {
   private Buffer buf;

   public Consumer(String name, Buffer buf) {
      super(name);
      this.buf = buf;
   } 
 
  @Override 
   public void run() {
      String msg = "";    
      do {
         msg = buf.take();
//         System.out.println(getName() + " consumed " +msg); 
      } while(!msg.equals("Done"));
   }
}