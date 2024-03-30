public class GenericBox {

    /**
     * Generic version of the Box class.
     * 
     * @param <T> the type of the value being boxed
     */
    public static class Box<T> {
        // T stands for "Type"
        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }

        @Override
        public String toString() {
            return "[" + t + "]";
        }

    }

    public static void main(String[] args) {

        Box<Integer> integerBox = new Box(); // When we omit the angle brackets, we get a warning "Box is a raw type.
                                             // References to generic type GenericBox.Box<T> should be parameterized"
        integerBox.set(5);
        System.out.println(integerBox);

        Box<String> stringBox = new Box<>();
        stringBox.set("help");
        System.out.println(stringBox);

        Box<Double> doubleBox = new Box<>();
        doubleBox.set(44.20);
        System.out.println(doubleBox);

    }

}