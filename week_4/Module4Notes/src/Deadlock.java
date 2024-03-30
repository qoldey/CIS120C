public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        // synchronized methods and code can only be accessed by one thread at once
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                    + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                    + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        // alphonse.bow(gaston);
        // gaston.bow(alphonse);

        new Thread(new Runnable() {
            public void run() {
                alphonse.bow(gaston);
                System.out.println("gaston bowed"); // this will not be reached
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
        // Neither thread will finish their run method because the objects they are
        // accessing are synchronized and therefore locked by the other thread, and
        // cannot run the bowBack() method at the end of bow().
        System.out.println("finished");
    }
}