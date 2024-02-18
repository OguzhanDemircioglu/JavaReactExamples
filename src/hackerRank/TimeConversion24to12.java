package hackerRank;

public class TimeConversion24to12 {
    public static void main(String[] args) {
        timeConversion("07:05:45PM");
        timeConversion("12:01:00AM");
    }

    public static void timeConversion(String s) {

        String[] tokens = s.split(":");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        int seconds = Integer.parseInt(tokens[2].substring(0, 2));
        String period = tokens[2].substring(2);

        if (period.equals("PM") && hours != 12) {
            hours += 12;
        } else if (period.equals("AM") && hours == 12) {
            hours = 0;
        }

        System.out.printf("%02d:%02d:%02d%n", hours, minutes, seconds);
    }
}
