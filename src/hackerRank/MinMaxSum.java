package hackerRank;

import java.util.Arrays;
import java.util.List;

public class MinMaxSum {
    public static void main(String[] args){
        int[] arr = {1, 2, 5, 4, 5};
        List<Integer> arrList = List.of(1, 2, 5, 4, 5);

        miniMaxSumArray(arr);
        miniMaxSumList(arrList);

        getMaxValueCount(arrList);
    }

    public static void miniMaxSumArray(int[] arr) {
        long sum = Arrays.stream(arr).asLongStream().sum();
        long max = Arrays.stream(arr).asLongStream().max().orElse(0);
        long min = Arrays.stream(arr).asLongStream().min().orElse(0);

        System.out.println((sum-min) + " " + (sum-max));
    }

    public static void miniMaxSumList(List<Integer> arr) {
        long sum = arr.stream().mapToLong(Integer::longValue).sum();
        long max = arr.stream().mapToLong(Integer::longValue).max().orElse(0);
        long min = arr.stream().mapToLong(Integer::longValue).min().orElse(0);
        System.out.println((sum-min) + " " + (sum-max));
    }

    public static void getMaxValueCount(List<Integer> candles) {
        long count= candles.stream()
                .filter(
                        i -> i==candles.stream().max(Integer::compareTo).get().intValue()
                ).count();
        System.out.println(count);
    }
}
