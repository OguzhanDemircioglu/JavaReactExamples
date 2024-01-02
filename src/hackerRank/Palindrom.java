package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class Palindrom {
    public static void main (String[] args){
        List<String> arr = new ArrayList<>();
        arr.add("asd131dsa");
        arr.add("{[(])}");
        arr.add("11155111");
        arr.add("1599517");

        for (String l: arr){
            palindrom(l);
        }
    }

    public static void palindrom(String str) {

        boolean returVal = true;

        for (int i=0; i<str.length();i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                returVal = false;
                break;
            }
        }
        if (returVal){
            System.out.println("PALINDROM");
        }else {
            System.out.println("NOT PALINDROM");
        }
    }
}
