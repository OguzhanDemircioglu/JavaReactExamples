package hackerRank;

import java.util.HashSet;

public class PairSubString {
    public static void main(String[] args) {

        String str1 = "heloo";
        String str2 = "stro";
        System.out.println(
                pairSubSting(str1,str2)
        );

    }

    public static String pairSubSting(String str1, String str2) {

        HashSet<Character> hashSet = str1.chars().mapToObj(c -> (char) c).collect(HashSet::new, HashSet::add, HashSet::addAll);

        if (str2.chars().mapToObj(c -> (char) c).anyMatch(hashSet::contains)) {
            return "yes";
        }

        return "no";
    }

}
