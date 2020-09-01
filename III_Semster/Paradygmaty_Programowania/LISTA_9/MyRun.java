public class MyRun implements Runnable {

    private int id;
    private double repeats;
    public boolean finished;

    public MyRun(int id) {
        this.id = id;
        this.repeats = this.id*1000000.0;
        finished=false;
    }

    @Override
    public void run() {
        while(!finished) {
            this.repeats--;
         //   System.out.println(i);
            if (this.repeats <= 0) {
                finished=true;
            }
        }
        System.out.println(" Thread no. "+ this.id +" Finished");
    }

}