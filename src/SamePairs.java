import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SamePairs {
    public static void main(String[] args) {

        List<String> arr = new ArrayList<>();
        arr.add("uz");
        arr.add("foo");
        arr.add("of");
        arr.add("of");
        getSamePairsNumber(arr);
        getCountValuesOfArrayElements(arr);
    }

    public static void getSamePairsNumber(List<String> words) {

        words = words.stream()
                .map(a -> a.chars().distinct()
                        .mapToObj(i -> String.valueOf((char) i)).sorted()
                        .collect(Collectors.joining(""))).collect(Collectors.toList());

        long sum = 0;
        for (int i = 0; i < words.size(); i++) {
            for (int q = i+1; q < words.size(); q++) {
                String k=words.get(i);
                String a=words.get(q);
                if(words.get(i).equals(words.get(q))){
                    sum++;
                }
            }

        }
        System.out.println(sum);
    }

    public static void getCountValuesOfArrayElements(List<String> arr){
      Map counts = arr.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        counts.forEach(
                (key,value) -> System.out.println(key +" -> "+ value)
        );
    }
}
