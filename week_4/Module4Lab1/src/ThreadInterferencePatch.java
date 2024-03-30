import java.util.Scanner;

// a thread class which includes Counter as a nested class. 
public class ThreadInterferencePatch extends Thread {
    public final SynchronizedCounter c;

    public ThreadInterferencePatch(SynchronizedCounter c) {
        this.c = c;
    }

    // a reference to a shared Counter object

    static class SynchronizedCounter {
        private int c = 0;

        // with the synchronized keyword, these methods cannot be accessed by multiple
        // threads at the same time, therefore avoiding any Thread interference and
        // locking.
        // The method essentially becomes locked until a thread is done using it, then
        // allowing the next thread to take control.

        public synchronized void increment() {
            c++;
        }

        public synchronized void decrement() {
            c--;
        }

        public synchronized int value() {
            return c;
        }

    }// End SynchronizedCounter class

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

        ThreadInterferencePatch obj = new ThreadInterferencePatch(new SynchronizedCounter()); // create object and
                                                                                              // shared Counter
        SynchronizedCounter counter = obj.c; // reference to obj.c

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
