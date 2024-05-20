import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadWriteINIFile {
    public static void main(String[] args) throws IOException {

        //Basit yaz

        Properties properties = new Properties();
        properties.setProperty("name", "John Doe");
        properties.setProperty("age", "30");
        properties.setProperty("active", "true");

        FileWriter writer = new FileWriter(".//src//helper//output.ini");
        properties.store(writer, "");
        writer.close();

        //Buffer ile yaz

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".//src//helper//bufferedOutput.ini"));

        bufferedWriter.write("name=John Doe");
        bufferedWriter.newLine();
        bufferedWriter.write("age=30");
        bufferedWriter.newLine();
        bufferedWriter.write("active=true");

        bufferedWriter.close();

        //Oku

        BufferedReader reader = new BufferedReader(new FileReader(".//src//helper//bufferedOutput.ini"));
        String line;

        Map<String, String> map = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=");
            if (parts.length == 2) {
                map.put(parts[0].trim(), parts[1].trim());
            }
        }

        String name = map.get("name");
        int age = Integer.parseInt(map.get("age"));
        boolean active = Boolean.parseBoolean(map.get("active"));

        System.out.println("Ad: " + name);
        System.out.println("Yaş: " + age);
        System.out.println("Aktif: " + active);

        reader.close();
    }
}
