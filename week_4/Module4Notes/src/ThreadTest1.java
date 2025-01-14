import java.util.Scanner;

/**
 * A program that starts several threads, each of which performs the
 * same computation. The user specifies the number of threads. The
 * point is to see that the threads finish in an indeterminate order.
 */
public class ThreadTest1 {

    private final static int MAX = 5000000;

    /**
     * When a thread belonging to this class is run it will count the
     * number of primes between 2 and MAX. It will print the result
     * to standard output, along with its id number and the elapsed
     * time between the start and the end of the computation.
     */
    private static class CountPrimesRunnable extends Thread {
        int id; // An id number for this thread; specified in the constructor.

        public CountPrimesRunnable(int id) {
            this.id = id;
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            int count = countPrimes(2, MAX);
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Thread " + id + " counted " +
                    count + " primes in " + (elapsedTime / 1000.0) + " seconds.");
        }
    }

    /**
     * Start several CountPrimesThreads. The number of threads, between 1 and 25,
     * is specified by the user.
     */
    public static void main(String[] args) {
        int numberOfThreads = 0;

        Scanner kbd = new Scanner(System.in);
        while (numberOfThreads < 1 || numberOfThreads > 25) {
            System.out.print("How many threads do you want to use  (from 1 to 25) ?  ");
            numberOfThreads = kbd.nextInt();
            if (numberOfThreads < 1 || numberOfThreads > 25)
                System.out.println("Please enter a number between 1 and 25 !");
        }
        System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
        Thread[] worker = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
            worker[i] = new Thread(new CountPrimesRunnable(i));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++)
            worker[i].start();
        System.out.println("Threads have been created and started.");

        // Exercise M4IN-5:
        // wait for child threads to complete (with 'join')
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                worker[i].join(); // Wait until worker[i] finishes, if it has not already.
            } catch (InterruptedException e) {
            }
        }
        System.out.println(
                numberOfThreads + " processes finished in " + ((System.currentTimeMillis() - startTime) / 1000.0)
                        + " seconds");
    }

    /**
     * Compute and return the number of prime numbers in the range
     * min to max, inclusive.
     */
    private static int countPrimes(int min, int max) {
        int count = 0;
        for (int i = min; i <= max; i++)
            if (isPrime(i))
                count++;
        return count;
    }

    /**
     * Test whether x is a prime number.
     * x is assumed to be greater than 1.
     */
    private static boolean isPrime(int x) {
        assert x > 1;
        int top = (int) Math.sqrt(x);
        for (int i = 2; i <= top; i++)
            if (x % i == 0)
                return false;
        return true;
    }

} // end class ThreadTest1
