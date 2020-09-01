import java.util.Random;

class LiveLock implements Runnable{
    String bank_A = "100";
    String bank_B = "200";
    boolean bank_A_ocuppied=false;
    boolean bank_B_ocuppied=false;

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

    private void waitForNotify() {
        try {wait();} catch (InterruptedException e) {}
    }

    public void run(){
        LiveLock live = new LiveLock();
        Random random = new Random();

        Thread t1 = new Thread(live,"Thread_1") {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while(bank_A_ocuppied) {waitForNotify();}
                    synchronized (live.bank_A) {
                        bank_A_ocuppied=true;
                        System.out.println("Thread 1: locked bank_A");

                        try {Thread.sleep(random.nextInt(10));} catch (Exception e) {}
                        try {
                            if (bank_B_ocuppied) {break;}
                            synchronized (live.bank_B) {
                                bank_B_ocuppied=true;
                                System.out.println("Thread 1: locked bank_B");
                                live.aToB();
                            }
                            bank_B_ocuppied=false;
                        } catch (Exception e) {System.out.println("Bank_B Blocked");}
                        bank_A_ocuppied=false;
                    }
                    try {Thread.sleep(random.nextInt(10));} catch (Exception e) {}
                    notifyAll();
                }
                System.out.println("Threat_1:");
                System.out.println("Bank_A: " + live.bank_A);
                System.out.println("Bank_B: " + live.bank_B);
            }
        };

        Thread t2 = new Thread(live,"Thread_2") {
            public void run() {
                for(int i =0; i<100;i++) {
                    synchronized (live.bank_B) {
                        bank_B_ocuppied=true;
                        System.out.println("Thread 2: locked bank_B ");
                        try {Thread.sleep(random.nextInt(10));} catch (Exception e) {}
                        try {
                            synchronized (live.bank_A) {
                                bank_A_ocuppied=true;
                                System.out.println("Thread 2: locked Bank_A");
                                live.bToA();
                            }
                            bank_A_ocuppied=false;
                        } catch (Exception e) {}
                        bank_B_ocuppied=false;
                    }
                    try { Thread.sleep(random.nextInt(10));} catch (Exception e) {System.out.println("Bank_B Blocked");}
                }
                System.out.println("Threat_2:");
                System.out.println("Bank_A: " + live.bank_A);
                System.out.println("Bank_B: " + live.bank_B);
            }
        };

        t1.start();
        t2.start();


    };

}
