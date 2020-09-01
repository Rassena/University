import java.util.Random;

class Race_Condition implements Runnable {
    int bank_A = 100;
    int bank_B = 200;

    private void aToB() {
        bank_A -= 10;
        bank_B += 10;
    }

    private void bToA() {
        bank_B -= 10;
        bank_A += 10;
    }

    public void run() {
        Random random = new Random();
        Race_Condition race = new Race_Condition();

        Thread t1 = new Thread(race,"Thread_1") {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {Thread.sleep(random.nextInt(10));} catch (Exception e) {}
                    race.aToB();
                }
            }
        };

        Thread t2 = new Thread(race,"Thread_2") {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {Thread.sleep(random.nextInt(10));} catch (Exception e) {}
                    race.bToA();
                }
            }
        };

        t1.start();
        t2.start();

        System.out.println("Bank A: " +  bank_A);
        System.out.println("Bank B: " +  bank_B);
    }
}