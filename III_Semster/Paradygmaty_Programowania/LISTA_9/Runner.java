public class Runner {

    public static void singleThread(){
        new MyRun(1000).run();
        new MyRun(100).run();
    }

    public static void multiThread(){

        boolean finished = false;

        MyRun first= new MyRun(1000);
        MyRun second= new MyRun(100);
        new Thread(first).start();
        new Thread(second).start();

        do {
            System.out.print("");
        }while (!first.finished||!second.finished);
    }

    public static void main(String[] args) {

        long time1 = System.currentTimeMillis();
        singleThread();
        System.out.println("SingleThread: " + (System.currentTimeMillis() - time1) + " ms");

        long time2 = System.currentTimeMillis();
        multiThread();
        System.out.println("Multihreads: " + (System.currentTimeMillis() - time2) + " ms");

    }

}