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
        for (int i = 0; i < 1000; i++) {
            if(Thread.currentThread().getName().equals("Thread_1"))
                aToB();
            else
                bToA();
        }
    }
}