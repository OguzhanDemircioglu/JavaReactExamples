import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args){

System.out.println(getNameByPhoneNumber(9999));



    }

    public static final HashMap<String, Integer> HASH_NUMBERS =
            new HashMap<>() {
                {
                    put("oguz", 13244);
                    put("riza", 78789);
                    put("lale", 9999);
                }
            };

    public static Optional<String> getNameByPhoneNumber(int number) {
        return HASH_NUMBERS.entrySet().stream()
                .filter(i -> i.getValue().equals(number))
                    .map(Map.Entry::getKey)
                        .findFirst();
    }
}
