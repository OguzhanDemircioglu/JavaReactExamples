import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.media.*;

import java.io.*;

public class Mp3ReadWrite {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(".//src//helper//Amorf - Waaraf.mp3");
        Player player = new Player(fileInputStream);
        player.play();

        FileInputStream in = new FileInputStream(".//src//helper//Amorf - Waaraf.mp3");
        FileOutputStream out = new FileOutputStream(".//src//helper//1.mp3");

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

    }
}
