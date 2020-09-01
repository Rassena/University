class IntCell {
    private int n = 0;
    private boolean isSet = true;

    public synchronized int getN() {
        while (!isSet)
            waitForNotifying();
        isSet = false;
        notifyAll();
        return n;
    }

    public synchronized void setN(int n) {
        while(isSet)
            waitForNotifying();
        this.n = n;
        isSet = true;
        notifyAll();
    }

    private void waitForNotifying() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Count extends Thread {
    private static IntCell n = new IntCell();

    @Override public void run() {
        int temp;
        for (int i = 0; i < 200000; i++) {
            temp = n.getN();
            n.setN(temp + 1);
        }
    }

    public static void main(String[] args) {
        Count p = new Count();
        Count q = new Count();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
        System.out.println("The value of n is " + n.getN());
    }
}
