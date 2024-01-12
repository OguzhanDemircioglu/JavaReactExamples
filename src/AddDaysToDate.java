import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddDaysToDate {

    public static String addDays(String startDate, int daysToAdd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        // Başlangıç tarihini ayarla
        Date date = sdf.parse(startDate);
        calendar.setTime(date);

        // Belirtilen gün kadar gün ekleyerek yeni tarihi hesapla
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        // Yeni tarihi "yyyy-MM-dd" formatında string olarak döndür
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args) throws ParseException {
        String startDate = "2021-12-11";
        int daysToAdd = 10;

        String newDate = addDays(startDate, daysToAdd);
        System.out.println(newDate);
    }
}
