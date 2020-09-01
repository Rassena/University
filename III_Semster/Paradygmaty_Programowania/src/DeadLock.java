
class DeadLock implements Runnable{
    String bank_A = "100";
    String bank_B = "200";

    public void aToB() {
        int a = Integer.valueOf(bank_A);
        a-=10;
        int b = Integer.valueOf(bank_B);
        b+=10;

        bank_A = String.valueOf(bank_A);
        bank_B = String.valueOf(bank_B);
    }

    public void bToA() {
        int b = Integer.valueOf(bank_B);
        b-=10;
        int a = Integer.valueOf(bank_A);
        a+=10;

        bank_B = String.valueOf(bank_B);
        bank_A = String.valueOf(bank_A);
    }

    public void run(){
        DeadLock dead = new DeadLock();

        Thread t1 = new Thread(dead,"Thread_1") {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (dead.bank_A) {
                        System.out.println("Thread 1: locked bank_A");

                        try {Thread.sleep(10);} catch (Exception e) {}
                        synchronized (dead.bank_B) {
                            System.out.println("Thread 1: locked bank_B");
                            dead.aToB();
                        }
                    }
                    try {Thread.sleep(10);} catch (Exception e) {}
                }
                System.out.println("Threat_2:");
                System.out.println("Bank_A: " + dead.bank_A);
                System.out.println("Bank_B: " + dead.bank_B);
            }
        };

        Thread t2 = new Thread(dead,"Thread_2") {
            public void run() {
                for(int i =0; i<100;i++) {
                    synchronized (dead.bank_B) {
                        System.out.println("Thread 2: locked bank_B ");
                        try {Thread.sleep(10);} catch (Exception e) {}

                        synchronized (dead.bank_A) {
                            System.out.println("Thread 2: locked Bank_A");
                            dead.bToA();
                        }
                    }
                    try { Thread.sleep(10);} catch (Exception e) {}
                }
                System.out.println("Threat_2:");
                System.out.println("Bank_A: " + dead.bank_A);
                System.out.println("Bank_B: " + dead.bank_B);
            }
        };

        t1.start();
        t2.start();


    };

}
