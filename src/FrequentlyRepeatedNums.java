import java.util.*;

public class FrequentlyRepeatedNums {
    public static void main(String[] args){

        int [] nums = {1,2,3,4,5,5,2,3,3};

        System.out.println(
                decipher(nums)
        );
    }

    public static int decipher(int[] nums) {

        if (nums.length == 0) {
            return -1;
        }

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        int maxFreq = Collections.max(frequency.values());

        List<Integer> mostFrequent = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == maxFreq) {
                mostFrequent.add(entry.getKey());
            }
        }

        return Collections.min(mostFrequent);
    }
}
