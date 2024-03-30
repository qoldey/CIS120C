public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }

    public static void main(String[] args) {

        SynchronizedCounter c = new SynchronizedCounter();
        int x = 100;
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < x; i++) {
                    c.increment();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < x; i++) {
                    c.increment();
                }
            }
        }).start();

    }

}
