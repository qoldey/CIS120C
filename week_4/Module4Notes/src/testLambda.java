public class testLambda implements testLambraInterface {

    public static void main(String[] args) {

        // Object code = () -> System.out.println("test");
        testLambda obj = new testLambda();
        obj.print();

    }

    @Override
    public void print() {
        System.out.println("hello!");
    }

}
