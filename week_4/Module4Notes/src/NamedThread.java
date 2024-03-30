public class NamedThread extends Thread {
    private String name; // The name of this thread.

    public NamedThread(String name) { // Constructor gives name to thread.
        this.name = name;
    }

    @Override
    public void run() { // The run method prints a message to standard output.
        System.out.println("Greetings from thread '" + name + "'!");
    }

    public static void main(String[] args) {
        NamedThread greetings = new NamedThread("Fred");
        greetings.start();
        System.out.println("Thread has been started");
    }
}