import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TryOut {

    public static <words> void main(String[] args) {

        int[] asd = {1, 2, 3, 5, 104, 15, 6};

        List<String> arr = new ArrayList<>();
        arr.add("uz");
        arr.add("foo");
        arr.add("of");
        System.out.println(aWordCreatedBySameChar(arr));
        /*
        String[] input = {"This", "is", "This", "not"};

        char[] charArray1 = "str1".toCharArray();

        System.out.println(charArray1[0]);

*/

/*
        List<String> words = Arrays.asList("xyz", "foo", "of");

        long similarPairsCount = countSimilarPairs(words);

        System.out.println("Number of similar pairs: " + similarPairsCount);

        System.out.println(getUniqueChars("lolo"));

        aWordCreatedBySameChar("8lolo1");







        */


    }

    public static Map<String, Long> countSimilarPairs(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(TryOut::sortedChars, Collectors.counting()));

    }

    public static String  sortedChars (String word){
        // Helper method to sort characters in a word
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static String getUniqueChars(String input) {
        // String'i karakter dizisine dönüştür, karakterleri stream'e çevir ve benzersiz karakterleri topla
        String result = input.chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        return result;
    }

/*    public static long theWordsCreatedBySameChar(List<String> arr){
        long returnVAL = arr.stream().map(TryOut::aWordCreatedBySameChar).mapToLong(i -> {
            long counter = 0;
            for (String arrVal: arr){
                if(i.equals(arrVal)){
                    counter++;
                }
            }
            return counter;

        }).sum();
        return returnVAL;
    }*/

    public static long aWordCreatedBySameChar(List<String> words){

        List<String> wordByUniqeChar = words.stream()
                .map(a->a.chars().distinct()
                .mapToObj(i -> String.valueOf((char)i)).sorted()
                .collect(Collectors.joining(""))).collect(Collectors.toList());

        long sum =0;
        for (int i =0;i< wordByUniqeChar.size();i++){
            long counter =-1;
            for (String ko: wordByUniqeChar){
                if(wordByUniqeChar.get(i).equals(ko)){
                    counter++;
                }
            }
            sum+=counter;
        }
        return sum;
    }
}
