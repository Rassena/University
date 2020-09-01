public class Producer extends Thread {
   private Buffer buf;

   public Producer(String name, Buffer buf) {
      super(name);
      this.buf = buf;
   } 
 
  @Override public void run() {
       for (int i=1; i<=10; i++) {
//          System.out.println(getName() + " producing m" + i);
          buf.put("m"+i);
       }
      buf.put("Done");
   }
}