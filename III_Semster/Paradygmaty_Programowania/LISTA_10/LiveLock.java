import java.util.Random;


class LiveLock implements Runnable{
    Integer bank_A = 100;
    Integer bank_B = 200;

    int releases =0;
    int transfers =0;

    String ownerA ="free";
    String ownerB ="free";

    public void aToB() {
        bank_A -= 10;
        bank_B += 10;
    }

    public void bToA() {
        bank_B -= 10;
        bank_A += 10;
    }

    private void setOwnerA() {
        ownerA=Thread.currentThread().getName();
    }

    private void setOwnerB() {
        ownerB=Thread.currentThread().getName();
    }

    private void releaseA() {
        ownerA="free";
    }
    private void releaseB(){
        ownerB="free";
    }

    public void run() {

        Random random = new Random();
        String nameOfThread = Thread.currentThread().getName();

        while (transfers<1000&&releases<1000) {

            if(nameOfThread.equals("Thread_1")) {

                while (!ownerA.equals(nameOfThread)) {
                    if (ownerA.equals("free")) {
                        setOwnerA();
                    }
                    try {
                        Thread.currentThread().sleep(random.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ownerA.equals(nameOfThread)) {
                        //Systemm.out.println(nameOfThread + " locked bank_A");
                    }
                }

                if (ownerB.equals("free")) {
                    setOwnerB();
                    try {
                        Thread.currentThread().sleep(random.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ownerB.equals(nameOfThread)) {
                        //Systemm.out.println(nameOfThread + " locked bank_B");
                    }
                }

                if (ownerB.equals("Thread_2")) {
                    if (ownerA.equals(nameOfThread)) {
                        releaseA();
                        releases++;
                        //Systemm.out.println(nameOfThread + " released bank_A");
                    }
                }

                if (ownerA.equals(nameOfThread) && ownerB.equals(nameOfThread)) {
                    aToB();
                    releaseA();
                    releaseB();
                    transfers++;
                    //Systemm.out.println(nameOfThread + " transfered from bank_A to bank_B");

                }
            }

            if(nameOfThread.equals("Thread_2")) {

                while (!ownerB.equals(nameOfThread)) {
                    if (ownerB.equals("free")) {
                        setOwnerB();
                    }
                    try {
                        Thread.currentThread().sleep(random.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ownerB.equals(nameOfThread)) {
                        //Systemm.out.println(nameOfThread + " locked bank_B");
                    }
                }

                if (ownerA.equals("free")) {
                    setOwnerA();
                    try {
                        Thread.currentThread().sleep(random.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ownerA.equals(nameOfThread)) {
                        //Systemm.out.println(nameOfThread + " locked bank_A");
                    }
                }

                if (ownerA.equals("Thread_1")) {
                    if (ownerB.equals(nameOfThread)) {
                        releaseB();
                        releases++;
                        //Systemm.out.println(nameOfThread + " released bank_B");
                    }
                }

                if (ownerB.equals(nameOfThread) && ownerA.equals(nameOfThread)) {
                    bToA();
                    releaseB();
                    releaseA();
                    transfers++;
                    //Systemm.out.println(nameOfThread + " transfered from bank_A to bank_B");
                }
            }

        }

    }

}