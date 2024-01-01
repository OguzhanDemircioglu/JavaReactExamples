import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MovingTotal {
    /**
     * Adds/appends list of integers at the end of internal list.
     */
    List<Integer> sumList = new ArrayList<>();
    List<Integer> valList = new ArrayList<>();


    public void append(int[] list) {
        int lastIndex = sumList.isEmpty() ? -1 : sumList.size() - 1;

        IntStream.range(0, list.length)
                .forEach(i -> sumList.add(list[i] + (i > 0 ? list[i - 1] : 0) + (i > 1 ? list[i - 2] : 0)));
    }

    public boolean contains(int total) {
        return sumList.contains(total);
    }

    public static void main(String[] args) {
        MovingTotal movingTotal = new MovingTotal();

        movingTotal.append(new int[]{1, 2, 3, 4});

        System.out.println(movingTotal.contains(6));  // true
        System.out.println(movingTotal.contains(9));  // true
        System.out.println(movingTotal.contains(12)); // false
        System.out.println(movingTotal.contains(7));  // true

        movingTotal.append(new int[]{5});
        System.out.println("---");
        System.out.println(movingTotal.contains(6));  // true
        System.out.println(movingTotal.contains(9));  // true
        System.out.println(movingTotal.contains(12)); // true
        System.out.println(movingTotal.contains(7));  // true

        for (int i : movingTotal.sumList) {
            System.out.println(i);
        }
    }
}