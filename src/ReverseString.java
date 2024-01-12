public class ReverseString {
    public static void main (String [] args){
        String str = "cikolata";
        System.out.println(stringReverser(str));
    }

    static String stringReverser(String str){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(str.length()-1-i));
        }
        return stringBuilder.toString();
    }
}
