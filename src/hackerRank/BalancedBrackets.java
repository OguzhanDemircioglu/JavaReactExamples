package hackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalancedBrackets {
/*  Input
    {[()]}
    {[(])}
    {{[[(())]]}}
    {{}}[]

    Output
    Balanced
    Not balanced
    Balanced
    Balanced*/

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("{[()]}");
        arr.add("{[(])}");
        arr.add("{{[[(())]]}}");
        arr.add("{{}}[]");

        for (String l: arr){
            isBalanced(l);
        }

    }

    static void isBalanced(String str){

        Stack st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {

            if(str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '(') {
                st.push(str.charAt(i));

            } else if (!st.empty() &&
                    ((str.charAt(i) == ']' && st.peek().equals('[')) ||
                    (str.charAt(i) == '}' && st.peek().equals('{')) ||
                    (str.charAt(i) == ')' && st.peek().equals('(')))) {

                st.pop();

            } else {
                st.push(str.charAt(i));
            }
        }

        if(st.empty()) {
            System.out.println("Balanced");
        } else {
            System.out.println("Not balanced");
        }

    }
}
