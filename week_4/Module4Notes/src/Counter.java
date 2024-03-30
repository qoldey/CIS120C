class Counter {
    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

    public static void main(String[] args) {

        Counter c = new Counter();
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