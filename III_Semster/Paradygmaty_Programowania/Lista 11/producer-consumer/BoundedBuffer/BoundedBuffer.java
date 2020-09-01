public class BoundedBuffer implements Produce, Consume {
    final private int N;
    private int in = 0, out = 0, n = 0;
    private int[] elems;

    public BoundedBuffer(int N) {
        this.N = N; elems = new int[N]; 
    }

    public synchronized void put(int x) {
        while (n >= N) 
          try {
        	  System.out.println(Thread.currentThread().getName()+" waiting"); 
        	  wait(); 
          } catch (InterruptedException ex) {System.out.println(ex);}
        elems[in] = x; in = (in + 1) % N ; n += 1; 
        System.out.println(Thread.currentThread().getName()+" putting " + x);
        for (int e: elems) {System.out.print(e+" ");}; System.out.println();
        if (n == 1) notifyAll();
    }

    public synchronized int take() {
        while (n == 0)
          try {
        	  System.out.println(Thread.currentThread().getName()+" waiting"); 
        	  wait(); 
          } catch (InterruptedException ex) {System.out.println(ex);}
        int x = elems[out]; elems[out]=0; out = (out + 1) % N ; n -= 1;
        for (int e: elems) {System.out.print(e+" ");}; System.out.println();
        if (n == N-1) notifyAll();
        return x;
    }
}

