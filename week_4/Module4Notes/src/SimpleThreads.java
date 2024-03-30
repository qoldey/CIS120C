public class SimpleThreads {

    // Display a message, preceded by
    // the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {

        // Delay, in milliseconds before
        // we interrupt MessageLoop
        // thread (default one hour).
        long patience = 1000 * 60 * 60;

        // Exercise M4IN-2: change patience to be shorter than how long the program
        // takes to execute.
        patience = 10000; // the thread is killed on line 76 and the loop is exited.

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        int numberOfThreads = 0;
        for (int i = 0; i < 4; i++) {
            Thread l = new Thread(new MessageLoop());
            numberOfThreads++;
            l.start();
            Thread.sleep(1000);
        }

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        // wait for child threads to complete (with 'join')

        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(); // after 1 sec, if thread has been running longer than patience, kill it
            // Exercise M4IN-3: change ^ to t.join();
            // changing this makes the below code unreachable.
            // t.isAlive() will never be true after waiting for the thread to finish.

            // Exercise M4IN-4: This if block is unreachable with t.join() as is.
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }
        }
        threadMessage("Finally!");

        threadMessage("took: ");
    }
}