public class ProdCons {
   public static void main(String[] args) {
      Buffer buf = new Buffer();
      (new Producer("Producer", buf)).start();
      (new Consumer("Consumer", buf)).start();
   }
}