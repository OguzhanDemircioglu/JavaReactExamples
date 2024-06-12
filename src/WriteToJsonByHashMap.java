import java.io.*;
import java.util.Base64;
import java.util.Scanner;

import helper.LameEncoder;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HandshakeCompletedListener;
import javax.sound.sampled.*;

public class WriteToJsonByHashMap {

    static File[] mp3Files = new File[]{};

    public static void main(String[] args) throws IOException, JSONException, UnsupportedAudioFileException {

        byte[] mp3Bytes = findAndExtractMp3Bytes(".//src//helper");
        if (mp3Bytes != null) {
            System.out.println("Hali hazırda sisteme yüklemediğiniz bir mp3 dosyası var, bu dosyayı hesabınıza iletelim mi?");
        } else {
            if (checkIfMp3Exists() == null) {
                sesKayit();
            }
            mp3OkuVeYaz("oguz");
        }
    }

    public static File[] checkIfMp3Exists() {
        File dir = new File("./src/helper");
        if (dir.exists() && dir.isDirectory()) {
            mp3Files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir1, String name) {
                    return name.endsWith(".mp3");
                }
            });
            return (mp3Files != null && mp3Files.length > 0) ? mp3Files : null;
        }
        return null;
    }

    public static void mp3OkuVeYaz(String username) {

        try {
            if (mp3Files.length > 0) {
                File mp3File = mp3Files[0];
                FileInputStream fis = new FileInputStream(mp3File);
                byte[] buffer = new byte[(int) mp3File.length()];
                fis.read(buffer);
                fis.close();

                String base64String = Base64.getEncoder().encodeToString(buffer);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", username);
                jsonObject.put("mp3", base64String);

                FileOutputStream fos = new FileOutputStream(".//src//helper//1.json");
                fos.write(jsonObject.toString().getBytes());
                fos.close();
            } else {
                System.out.println("MP3 dosyası bulunamadı!");
            }
        } catch (JSONException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void sesKayit() {
        try {
            AudioFormat audioFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    44100, // sample rate
                    16, // sample size in bits
                    2, // channels (stereo)
                    4, // frame size
                    44100, // frame rate
                    false // big endian
            );

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            line.start();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            byte[] data = new byte[line.getBufferSize() / 5];

            boolean[] recording = new boolean[]{true};

            System.out.println("Ses kaydını durdurmak için 'q' ya basın");
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                while (true) {
                    String girdi = scanner.next();
                    if (girdi.equalsIgnoreCase("q")) {
                        recording[0] = false;
                        break;
                    }
                }
            }).start();

            while (recording[0]) {
                numBytesRead = line.read(data, 0, data.length);
                out.write(data, 0, numBytesRead);
            }

            line.stop();
            line.close();

            byte[] audioData = out.toByteArray();
            byte[] mp3Data = convertToMp3(audioData);

            FileOutputStream fos = new FileOutputStream("./src/helper/ses_kaydi.mp3");
            fos.write(mp3Data);
            fos.close();
        } catch (Exception e) {
            System.out.println("Ses kayıt başarısız");
        }
    }

    private static byte[] convertToMp3(byte[] pcmData) throws Exception {
        System.loadLibrary("lame_enc");
        LameEncoder encoder = new LameEncoder(pcmData, 44100, 2, 128);

        byte[] mp3Data = encoder.encode();

        return mp3Data;
    }
    
    public static byte[] findAndExtractMp3Bytes(String directoryPath) throws IOException, JSONException {
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Belirtilen dizin bulunamadı");
            return null;
        }

        File[] jsonFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir1, String name) {
                return name.endsWith(".json");
            }
        });

        if (jsonFiles == null || jsonFiles.length == 0) {
            System.out.println("Belirtilen dizinde JSON dosyası bulunamadı");
            return null;
        }

        for (File jsonFile : jsonFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonString.toString());

            if (jsonObject.has("mp3")) {
                String base64String = jsonObject.getString("mp3");

                return Base64.getDecoder().decode(base64String);
            }
        }

        return null;
    }
}
