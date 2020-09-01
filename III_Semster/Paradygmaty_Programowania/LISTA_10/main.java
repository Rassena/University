import static java.lang.Thread.sleep;

public class main {

    public static void race(){
        Race_Condition race = new Race_Condition();

        System.out.println("Race Condition: ");
        System.out.println("Bank_A: " + race.bank_A);
        System.out.println("Bank_B: " + race.bank_B);

        Thread t1 = new Thread(race,"Thread_1");
        Thread t2 = new Thread(race,"Thread_2");

        t1.start();
        t2.start();

        try {t1.join();t2.join();} catch (InterruptedException e){}

        System.out.println("After: ");
        System.out.println("Bank_A: " + race.bank_A);
        System.out.println("Bank_B: " + race.bank_B);

    }

    public static void dead() {
        DeadLock dead = new DeadLock();

        System.out.println("DeadLock: ");
        System.out.println("Bank_A: " + dead.bank_A);
        System.out.println("Bank_B: " + dead.bank_B);

        Thread t1 = new Thread(dead,"Thread_1");
        Thread t2 = new Thread(dead,"Thread_2");

        t1.start();
        t2.start();

        try {t1.join();t2.join();} catch (InterruptedException e){}
    }

    public static void live() {
        LiveLock live = new LiveLock();

        System.out.println("LiveLock: ");
        System.out.println("Bank_A: " + live.bank_A);
        System.out.println("Bank_B: " + live.bank_B);

        Thread t1 = new Thread(live,"Thread_1");
        Thread t2 = new Thread(live,"Thread_2");

        t1.start();
        t2.start();

        try {t1.join();t2.join();} catch (InterruptedException e){}

        System.out.println("After: ");
        System.out.println("Bank_A: " + live.bank_A);
        System.out.println("Bank_B: " + live.bank_B);
        System.out.println("Released: " + live.releases);
        System.out.println("Transfers: " + live.transfers);


    }

    public static void wait(int time){
        wait(time);
    }

    public static void main(String[] args) {

        race();
        System.out.println("-----------");
        live();
        System.out.println("-----------");
        dead();

    }


}
