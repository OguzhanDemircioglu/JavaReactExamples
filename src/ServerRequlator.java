import java.io.*;

public class ServerRequlator {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int req1 = Integer.parseInt(bufferedReader.readLine().trim());
        int t1 = Integer.parseInt(bufferedReader.readLine().trim());
        int req2 = Integer.parseInt(bufferedReader.readLine().trim());
        int t2 = Integer.parseInt(bufferedReader.readLine().trim());

        long result = getMinUpgradationTime(req1, t1, req2, t2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static long getMinUpgradationTime(int req1, int t1, int req2, int t2) {
        long r1 = t1;
        long r2 = t2;

        long lo = 1;
        long hi = 2L * (r1 + r2) * Math.max(req1, req2);

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            long lcm = lcm(req1, req2);
            long bothBusy = mid / lcm;
            long only1Busy = mid / req1 - bothBusy;
            long only2Busy = mid / req2 - bothBusy;
            long free = mid - mid / req1 - mid / req2 + bothBusy;

            long need1 = Math.max(0, r1 - only2Busy);
            long need2 = Math.max(0, r2 - only1Busy);

            if (need1 + need2 <= free) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
//İki sunucu var. İkisinin de güncellenmesi lazım.
//
//Kurallar:
//
//Her saniye sadece 1 tanesi güncellenebilir
//Sunucular belirli aralıklarla istek alıyor — istek aldığı saniyede güncelleme duruyor
//sunucu: req1'in katlarında meşgul (req1=2 ise → 2, 4, 6, 8...)
//sunucu: req2'nin katlarında meşgul (req2=3 ise → 3, 6, 9, 12...)
//İkisi de meşgulse o saniye boş geçilir
//Soru: İkisinin de güncellemesi en erken kaçıncı saniyede biter?
//
//Zorluk: t1 ve t2 1 milyara kadar çıkabiliyor, yani saniye saniye döngüyle çözemezsin — daha akıllı bir yöntem lazım.