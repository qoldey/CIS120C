import java.util.ArrayList;

public class notes {
    // generic method to exchange positions of two elements in an array.
    public static <T extends Comparable<T>> void sort(ArrayList<T> a, int i, int j) {
        T temp;
        temp = a.get(j);
        a.set(j, a.get(i));
        a.set(i, temp);
    }
}
