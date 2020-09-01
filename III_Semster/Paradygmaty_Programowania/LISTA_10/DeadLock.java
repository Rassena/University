import java.util.Random;

class DeadLock implements Runnable {
    Integer bank_A = 100;
    Integer bank_B = 200;

    public void aToB() {
        bank_A -= 10;
        bank_B += 10;
    }

    public void bToA() {
        bank_B -= 10;
        bank_A += 10;
    }

    public void run() {

        for (int i = 0; i < 1000; i++) {

            if(Thread.currentThread().getName().equals("Thread_1")) {
                synchronized (bank_A) {
                    System.out.println(Thread.currentThread().getName() + " locked bank_A");
                    synchronized (bank_B) {
                        System.out.println(Thread.currentThread().getName() + " locked bank_B");
                        aToB();
                    }
                    System.out.println(Thread.currentThread().getName() + " unlocked bank_B");
                }
                System.out.println(Thread.currentThread().getName() + " unlocked bank_A");
            }
            else {
                synchronized (bank_B) {
                    System.out.println(Thread.currentThread().getName() + " locked bank_B");
                    synchronized (bank_A) {
                        System.out.println(Thread.currentThread().getName() + " locked bank_A");
                        bToA();
                    }
                    System.out.println(Thread.currentThread().getName() + " unlocked bank_A");
                }
                System.out.println(Thread.currentThread().getName() + " unlocked bank_B");
            }
        }
    }
}