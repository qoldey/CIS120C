import java.util.ArrayList;

public class ArrayListSelectSort {

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> newList = new ArrayList<Integer>();
        newList.add(3);
        newList.add(2);
        newList.add(1);

        System.out.println(newList.toString()); // unsorted
        sort(newList);
        // Collections.sort(newList); //I learned this would also sort our List
        System.out.println(newList.toString()); // sorted

    }

    public static void sort(ArrayList<Integer> a) { // SelectSort
        int temp;
        for (int i = 0; i < a.size() - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < a.size(); j++) {
                switch (compareTo(a.get(j), a.get(pos))) {// compares i and i+1
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

    // @Override
    public static int compareTo(int j, int pos) {
        if (j == pos) {
            return -1;
        } else if (j < pos) {
            return 0;
        } else {
            return 1;
        }
    }

}
