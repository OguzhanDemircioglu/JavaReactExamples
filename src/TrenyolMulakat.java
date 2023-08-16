import java.util.Scanner;

public class TrenyolMulakat {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Sayi giriniz: ");
        String N = scan.nextLine();

        int sayi = 0;

        try {
            sayi = Integer.valueOf(N).intValue();
        } catch (NumberFormatException e) {
            System.out.println("Girdi bir sayi olmalidir: Gelen Hata\n " + e);
            e.printStackTrace();
        }

        int firstSum = 0;

        while (true) {

            int sum = 0;
            for (int i = 0; i < (sayi + "").length(); i++) {
                int temp = Integer.valueOf((sayi + "").substring(i, i + 1));
                sum += temp;
            }

            if ((sayi + "").equals(N)) {
                firstSum = sum;
            } else {
                if (firstSum == sum) {
                    System.out.println(sayi);
                    break;
                }
            }
            sayi++;
        }
    }
}
