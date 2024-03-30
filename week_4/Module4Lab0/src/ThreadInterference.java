import java.util.Scanner;

// a thread class which includes Counter as a nested class. 
public class ThreadInterference extends Thread {
    public final Counter c;

    public ThreadInterference(Counter c) {
        this.c = c;
    }

    // a reference to a shared Counter object

    static class Counter { // I couldn't figure out how to run in main w/o changing to static.
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

    }// End Counter class

    static int increments = 0;
    static int threads = 0;

    @Override
    public void run() {

        // repeatedly call the increment() method.
        for (int i = 0; i < increments; i++) {
            c.increment();
        }

    } // end run implementation

    public static void multiThreading() {

        ThreadInterference obj = new ThreadInterference(new Counter()); // create object and shared Counter
        Counter counter = obj.c; // reference to obj.c

        // let the user specify
        System.out.println("Threads to run: ");
        threads = (new Scanner(System.in).nextInt());
        System.out.println("Times to increment: ");
        increments = (new Scanner(System.in).nextInt());

        System.out.println("\nCreating " + threads + " threads...");
        Thread[] worker = new Thread[threads];
        // Create the threads, giving each the same Counter object reference.
        for (int i = 0; i < threads; i++)
            worker[i] = new Thread(obj);

        // Start the threads
        for (int i = 0; i < threads; i++)
            worker[i].start();
        System.out.println(threads + " Threads have been created and started.");

        long startTime = System.currentTimeMillis();

        // wait for all the threads to terminate
        for (int i = 0; i < threads; i++) {
            try {
                worker[i].join(); // Wait until worker[i] finishes, if it has not already.
            } catch (InterruptedException e) {
            }
        }
        System.out.println(
                threads + " processes finished in " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
        int value = counter.value();
        System.out.printf("shared counter: %d expected: %d\n", value, increments * threads);// Is the value of the
                                                                                            // shared Counter object
                                                                                            // correct?
    } // end multiThreading demonstration method

    public static void main(String[] args) {
        multiThreading();

    }// end main method

}// End class App
