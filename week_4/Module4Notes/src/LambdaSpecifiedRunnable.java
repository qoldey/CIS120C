public class LambdaSpecifiedRunnable {
    public static void main(String[] args) {
        Thread greetingsFromFred = new Thread(() -> System.out.println("Greetings from Fred!"));
        greetingsFromFred.start();
        System.out.println("Thread has been started");
    }
}
