import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovingTotal {
    /**
     * Adds/appends list of integers at the end of internal list.
     */
    List<Integer> sumList = new ArrayList<Integer>();


    public void append(int[] list) {
        sumList.add(Arrays.stream(list).reduce((i, sum) -> sum = i + sum).getAsInt());
    }

    /**
     * Returns boolean representing if any three consecutive integers in the
     * internal list have given total.
     */
    public boolean contains(int total) {
        if (sumList.contains(total)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        MovingTotal movingTotal = new MovingTotal();

        movingTotal.append(new int[]{1, 2, 3, 4});

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));
        System.out.println(movingTotal.contains(12));
        System.out.println(movingTotal.contains(7));

        movingTotal.append(new int[]{5});

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));
        System.out.println(movingTotal.contains(12));
        System.out.println(movingTotal.contains(7));
    }
}