import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingReader {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "www.google.com");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("İşlem çıkış kodu: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
