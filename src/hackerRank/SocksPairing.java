package hackerRank;

import java.util.Arrays;
import java.util.HashSet;

public class SocksPairing {
    public static void main(String[] args) {

        int[] arr = {1, 1, 3, 4, 5,1, 5, 3, 3};

        HashSet<Integer> hashSet = new HashSet<Integer>();

        System.out.println(
                Arrays.stream(arr).filter(i -> {
                    if (!hashSet.contains(i)) {
                        hashSet.add(i);
                        return false;
                    } else {
                        hashSet.remove(i);
                        return true;
                    }
                }).count()
        );
    }
}
