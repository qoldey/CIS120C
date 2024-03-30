public class NamedRunnable implements Runnable {
    private String name; // The name of this Runnable.

    public NamedRunnable(String name) { // Constructor gives name to object.
        this.name = name;
    }

    @Override
    public void run() { // The run method prints a message to standard output.
        System.out.println("Greetings from runnable '" + name + "'!");
    }

    public static void main(String[] args) {
        NamedRunnable greetings = new NamedRunnable("Fred");
        Thread greetingsThread = new Thread(greetings);
        greetingsThread.start();
        System.out.println("Thread has been started");
    }
}
