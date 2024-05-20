import java.io.*;

public class ReadingAndWritingText {
    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(".//src//helper//output.txt"));
        writer.write("yazılıyor...");
        writer.write("\nyazılıyor...");
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader(".//src//helper//output.txt"));
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();

        //Binary Reading And Writing

        FileInputStream in = new FileInputStream(".//src//helper//ben.png");
        FileOutputStream out = new FileOutputStream(".//src//helper//yeniResim.png");

        int byteRead;

        while((byteRead = in.read()) != -1){
            out.write(byteRead);
        }

        InputStreamReader readerStream = new InputStreamReader(in, "UTF-8");

        BufferedReader bufferedReader = new BufferedReader(readerStream);

        String resimContent = "";
        while ((resimContent = bufferedReader.readLine()) != null) {
            System.out.println(resimContent);
        }

        in.close();
        out.close();
        bufferedReader.close();

        //File Reading And Writing
        FileWriter fileWriter = new FileWriter(".//src//helper//output.txt");
        fileWriter.write("Hellooo");
        fileWriter.close();

        FileReader fileReader = new FileReader(".//src//helper//output.txt");

        BufferedReader bufferedReader1 = new BufferedReader(fileReader);

        String line1;
        while ((line1 = bufferedReader1.readLine()) != null) {
            System.out.println(line1);
        }

        fileReader.close();
        bufferedReader1.close();
    }
}
