
public class CharSequenceImplementation implements CharSequence {

    private static String s;

    // I think the UML diagram calls for a string parameter here, however, I didn't
    // see how that would work with the way "foo" is created within the assignment.
    public CharSequenceImplementation() {

    }

    // Returns the length of this character sequence.
    @Override
    public int length() {
        return s.length();
    }

    // Returns the char value at the specified index.
    @Override
    public char charAt(int arg0) {
        return s.charAt(arg0);
    }

    // Returns a CharSequence that is a subsequence of this sequence.
    @Override
    public CharSequence subSequence(int arg0, int arg1) {
        return s.subSequence(arg0, arg1);
    }

    @Override
    public String toString() {
        return s;
    }

}
