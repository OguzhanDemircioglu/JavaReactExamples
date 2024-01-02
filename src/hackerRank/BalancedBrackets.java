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
        arr.add("{{[[(())]]}}-");
        arr.add("{{}}[]");

        for (String l: arr){
            isBalanced(l);
        }

    }

    static void isBalanced(String str){

        Stack stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char charVal = str.charAt(i);
            if(charVal == '(' || charVal == '{' || charVal == '['){
                stack.push(charVal);
            }else if(
                    !stack.isEmpty() &&
                            ((charVal == ')' && stack.peek().equals('(')) ||
                                    (charVal == '}' && stack.peek().equals('{')) ||
                                    (charVal == ']' && stack.peek().equals('[')))
            ){
                stack.pop();
            }else {
                stack.push(charVal);
            }
        }

        if(stack.isEmpty()){
            System.out.println("BALANCED");
        }else {
            System.out.println("NOT BALACED");
        }
    }
}
