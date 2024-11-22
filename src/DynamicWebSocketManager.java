import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class DynamicWebSocketManager {

    private static final List<WebSocket> webSockets = new ArrayList<>(); // Bağlantıları tut
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {

            System.out.println("Komutlar:");
            System.out.println("'add': Yeni bir WebSocket bağlantısı ekle.");
            System.out.println("'send <id> <message>': Belirtilen ID'li bağlantıya mesaj gönder.");
            System.out.println("'close <id>': Belirtilen ID'li bağlantıyı kapat.");
            System.out.println("'list': Tüm WebSocket bağlantılarını listele.");
            System.out.println("'exit': Programı sonlandır.");

            while (true) {
                System.out.print("Komut: ");
                String input = scanner.nextLine();
                String[] parts = input.split(" ", 3);

                switch (parts[0]) {
                    case "add":
                        addWebSocket();
                        break;
                    case "send":
                        if (parts.length < 3) {
                            System.out.println("Kullanım: send <id> <message>");
                        } else {
                            sendMessage(Integer.parseInt(parts[1]), parts[2]);
                        }
                        break;
                    case "close":
                        if (parts.length < 2) {
                            System.out.println("Kullanım: close <id>");
                        } else {
                            closeWebSocket(Integer.parseInt(parts[1]));
                        }
                        break;
                    case "list":
                        listWebSockets();
                        break;
                    case "exit":
                        closeAllWebSockets();
                        System.exit(0);
                    default:
                        System.out.println("Bilinmeyen komut.");
                }
            }
        }
    }

    private static void addWebSocket() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                try {
                    URI uri = URI.create("wss://echo.websocket.events");
                    WebSocket webSocket = client.newWebSocketBuilder()
                            .buildAsync(uri, new SimpleWebSocketListener())
                            .join();
                    webSockets.add(webSocket);
                    System.out.println("Yeni WebSocket eklendi. ID: " + (webSockets.size() - 1));
                } catch (Exception e) {
                    System.err.println("WebSocket bağlantısı oluşturulamadı: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println("Virtual thread işlemi sırasında hata oluştu: " + e.getMessage());
        }
    }

    private static void sendMessage(int id, String message) {
        if (id >= 0 && id < webSockets.size()) {
            WebSocket webSocket = webSockets.get(id);
            webSocket.sendText(message, true);
            System.out.println("Mesaj gönderildi: " + message);
        } else {
            System.out.println("Geçersiz WebSocket ID: " + id);
        }
    }

    private static void closeWebSocket(int id) {
        if (id >= 0 && id < webSockets.size()) {
            WebSocket webSocket = webSockets.get(id);
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Closed by user").join();
            webSockets.remove(id);
            System.out.println("WebSocket kapatıldı. ID: " + id);
        } else {
            System.out.println("Geçersiz WebSocket ID: " + id);
        }
    }

    private static void listWebSockets() {
        if (webSockets.isEmpty()) {
            System.out.println("Hiçbir WebSocket bağlantısı yok.");
        } else {
            System.out.println("Aktif WebSocket bağlantıları:");
            for (int i = 0; i < webSockets.size(); i++) {
                System.out.println("ID: " + i + " - WebSocket: " + webSockets.get(i));
            }
        }
    }

    private static void closeAllWebSockets() {
        for (WebSocket webSocket : webSockets) {
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Shutting down").join();
        }
        webSockets.clear();
        System.out.println("Tüm WebSocket bağlantıları kapatıldı.");
    }

    static class SimpleWebSocketListener implements WebSocket.Listener {
        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("WebSocket açıldı.");
            WebSocket.Listener.super.onOpen(webSocket);
        }

        @Override
        public java.util.concurrent.CompletableFuture<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            System.out.println("Gelen mesaj: " + data);
            return (CompletableFuture<?>) WebSocket.Listener.super.onText(webSocket, data, last);
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            System.err.println("WebSocket hatası: " + error.getMessage());
        }

        @Override
        public java.util.concurrent.CompletableFuture<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("WebSocket kapandı. Durum: " + statusCode + ", Sebep: " + reason);
            return (CompletableFuture<?>) WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }
    }
}
