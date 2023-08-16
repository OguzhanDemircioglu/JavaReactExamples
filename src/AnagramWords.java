import java.util.Arrays;
import java.util.Scanner;

public class AnagramWords {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Anagram uygulamasina Hosgeldiniz, lutfen arama yapiniz.\n Ornek:Bicaklici Bicakla\n Cikmak icin q ya basiniz");
        String ornek = "";

        while (!ornek.equals("q")) {

            ornek = scan.nextLine();
            String[] list = ornek.split(" ");

            if (list.length != 2) {
                System.out.println("2 kelime ile validasyon yapilmali. Ornek:Bicaklici Bicakla");
            } else if (list[0].length() != list[1].length()) {
                System.out.println("Girdiginiz kelimelerin uzunluklari ayni degil, bu yuzden Anagram degildir");
            } else {
                int[] charList = new int[list[0].length()];
                int[] charList2 = new int[list[1].length()];
                for (int i = 0; i < list.length; i++) {
                    for (int k = 0; k < list[i].length(); k++) {
                        if (i == 0) {
                            charList[k] = list[i].charAt(k);
                        }
                        if (i == 1) {
                            charList2[k] = list[i].charAt(k);
                        }
                    }
                }

                Arrays.sort(charList);
                Arrays.sort(charList2);

                String msg = "";
                for (int i = 0; i < list.length; i++) {

                    if (charList[i] == charList2[i]) {
                        msg = "Girdiginiz kelimeler Anagramdir";
                    } else
                        msg = "Girdiginiz kelimeler farkli harfler iceriyor, bu yuzden Anagram degildir";
                    break;
                }

                System.out.println(msg);

            }
        }

    }
}