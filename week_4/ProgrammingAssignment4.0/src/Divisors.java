import java.util.Scanner;

public class Divisors implements Runnable {

    int lower;
    int upper;
    static int pos; // indicates number that has the most divisors so far
    static int max; // indicates most number of divisors seen so far
    static int threads; // indicates number of threads requested by user

    public Divisors(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        // pos = scanRange(lower, upper); // also set's max
    }

    // finds the integer in the range lower-upper w the largest number of divisors
    protected static int scanRange(int lower, int upper) {

        int divisors = max;
        pos = lower;

        for (int i = lower; i <= upper; i++) {
            divisors = numDivisors(i);

            if (divisors > max) {
                max = divisors;
                pos = i;
            }
            // System.out.printf("%d has: %d divisors\n", i, numDivisors(i));
        }
        return pos;
    }

    private static int numDivisors(int x) {
        int c = 1;
        for (int i = 1; i < (x / 2) + 1; i++)
            if (x % i == 0)
                c++;
        return c;
    }

    public static void main(String[] args) {
        int lower = 1;
        int trueMax = 10000;
        trueMax = trueMax / 2; // idk why but this multiplies by 2 as is?
        multiThreading(lower, trueMax);

    }

    private static void multiThreading(int lower, int trueMax) {

        System.out.print("Threads ");
        threads = (new Scanner(System.in).nextInt());
        int runsPerThread = trueMax / threads; // check this many numbers per thread

        Divisors obj = new Divisors(lower, trueMax);
        Thread t = new Thread(obj);

        System.out.println("\nCreating " + threads + " threads...");
        Thread[] worker = new Thread[threads];
        // Create the threads, giving each the same Counter object reference.
        for (int i = 0; i < threads; i++)
            worker[i] = new Thread(obj);

        // Start the threads
        for (int i = 0; i < threads; i++) {

            worker[i].start(); // start specified number of threads
            obj.lower += runsPerThread; // increase upper/lower to scan next ranges
            obj.upper += runsPerThread;

        }
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

        System.out.printf(obj.pos + " has the most divisors: %d", obj.max);

    }

    @Override
    public void run() {
        scanRange(this.lower, this.upper);
        System.out.println(this.upper);

    }

}
