
public class ProdCons {
   public static void main(String args[]) {
       BoundedBuffer buf = new BoundedBuffer(5);   
    	 new Producer("Producer ", buf).start();
    	 new Consumer("Consumer ",buf).start();
   }       
}
