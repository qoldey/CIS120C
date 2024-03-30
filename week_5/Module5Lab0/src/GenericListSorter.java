import java.util.ArrayList;

class GenericListSorter<T> implements Comparable<T> {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        newList.add(3);
        newList.add(2);
        newList.add(1);

        System.out.println(newList.toString()); // before
        sort(newList); // TODO: fix compareTo
        System.out.println(newList.toString()); // after

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("z");
        stringList.add("x");
        stringList.add("y");

        System.out.println(stringList.toString()); // before
        sort(stringList);
        System.out.println(stringList.toString()); // after

    }

    public static <T extends Comparable<T>> void sort(ArrayList<T> a) {
        T temp;
        for (int i = 0; i < a.size() - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < a.size(); j++) {

                switch (a.get(i).compareTo(a.get(pos))) {// compares i and i+1
                    case -1:// if j == pos
                        break;
                    case 0: // if j < pos,
                        pos = j; // position with lowest num in array
                        break;
                    case 1: // if j > pos
                        break;
                }
            }
            temp = a.get(pos);
            a.set(pos, a.get(i));
            a.set(i, temp);
        }
    }

    // I am a bit lost here. How would I go about comparing generic types here where
    // I don't know what to compare?

    // I included ArrayListSelectSort.java to
    // demonstrate sorting an ArrayList<Integer>
    // The operator < is undefined for the argument type(s) listSorter<T>, T
    // The operator < is undefined for the argument type(s) T, T
    @Override
    public int compareTo(T j) {
        if (this.equals(j)) {
            return -1;
        }
        // else if (this < arg0) {
        // return 0;
        // }
        else {
            return 1;
        }
    }
}
