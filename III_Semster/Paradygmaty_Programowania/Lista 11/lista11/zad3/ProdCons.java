
public class ProdCons {
   public static void main(String args[]) {
	   final int P = 3, C = 3;
       BoundedBuffer buf = new BoundedBuffer(5);   
       Producer[] prods = new Producer[P];
       Consumer[] cons = new Consumer[C];
       for (int i=0; i<P; i++) {
    	   prods[i] = new Producer("Producer "+(i+1), buf);  prods[i].start();}
       for (int i=0; i<C; i++) {
    	   cons[i] = new Consumer("Consumer "+(i+1),buf); cons[i].start();}
   }       
}
