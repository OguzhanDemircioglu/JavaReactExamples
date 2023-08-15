import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Anagram uygulamasına Hoşgeldiniz, lütfen arama yapınız.\n Örnek:Bıçaklıçı ve Bıçakla\n Çıkmak için q ya basınız");
        String ornek = "";

        while (!ornek.equals("q")) {

            ornek = scan.nextLine();
            String[] list = ornek.split(" ");
            int[] list1 = new int[3];

            if (list.length != 3) {
                System.out.println("3 kelime ile validasyon yapılmalı. Örnek:Bıçaklıçı ve Bıçakla");
            } else if (list[0].length() != list[2].length()) {
                System.out.println("Girdiğiniz kelimelerin uzunlukları aynı değil, bu yüzden Anagram değildir");
            } else {
                for (int j = 0; j < list.length; j++) {
                    for (int i = 0; i < list[j].length(); i++) {
                        int say = 1;
                        for (int k = 0; k < list[j].length(); k++) {
                            int charVal = list[j].charAt(k);
                            say = say * charVal;
                        }
                        list1[j] = say;
                    }
                }

                if (list1[0] == list1[2]) {
                    System.out.println("Girdiğiniz kelimeler Anagramdır");
                } else
                    System.out.println("Girdiğiniz kelimeler farklı harfler içeriyor, bu yüzden Anagram değildir");
            }
        }

    }
}