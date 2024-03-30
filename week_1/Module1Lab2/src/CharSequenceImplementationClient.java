public class CharSequenceImplementationClient {
    public static void main(String[] args) {

        // I am not sure if I entirely understood this lab, this doesn't compile
        // properly and s remains null regardless of my implementations so far.

        CharSequence foo = new CharSequenceImplementation();

        System.out.println(foo);

        String s = foo.toString();
        int size = s.length();
        char c = s.charAt(5);
        CharSequence ss = s.subSequence(1, 2);

    }
}