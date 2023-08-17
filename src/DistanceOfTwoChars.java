import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DistanceOfTwoChars {

    public static void main (String [] args){

        String aa ="AcAAAc";

        List<Character> arr = new ArrayList<>();

        for (int i = 0; i < aa.length(); i++) {

            for (int k = 0; k < aa.length(); k++) {
                if(aa.charAt(i) != aa.charAt(k)){
                    arr.add(aa.charAt(i));
                }
            }
        }

        arr = arr.stream().distinct().sorted().collect(Collectors.toList());

        int val=0;
        for (int k = 0; k < arr.size(); k++) {

            for (int i = 0; i < aa.length(); i++) {
                char a1= arr.get(k);
                char a2= aa.charAt(i);

                if(a1 == a2){
                    val++;
                }
            }
        }

        int sum =0;
        for (int k = 0; k < arr.size(); k++) {
            int first = aa.indexOf(arr.get(k));
            int second = aa.lastIndexOf(arr.get(k));

            while( first != second){

                char temp = aa.charAt(first+1);

                if(arr.get(k) != temp){
                    sum++;
                }else {
                    break;
                }
                first++;
            }
        }

        System.out.println(sum-1);

    }
}
