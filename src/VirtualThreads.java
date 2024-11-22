import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreads {
    public static void main(String[] args) throws InterruptedException {

        startMultiWebSocket();
    }


    static void _itterator() {
        var numbers = List.of(1, 2, 3, 4, 5);
        for (var _ : numbers) { // Döngüde değer kullanılmıyor
            System.out.println("Sadece iterasyon yapılıyor.");
        }
    }

    static void goVirtualThreadsEx() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        int vThreadCount = 100_000;

        for (int i = 0; i < vThreadCount; i++) {
            int threadIndex = i;
            Thread thread = Thread.ofVirtual().start(() -> {
                int result = 1;
                for (int j = 1; j < 10; j++) {
                    result *= j;
                }
                System.out.println("Result[" + threadIndex + "] " + result);
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join(100);
        }
    }

    static void goThreadsMinimal() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 100; i++) {
            int taskId = i;
            executor.submit(() ->
                    System.out.println("Task " + taskId +
                            " platform thread ile çalışıyor."));
        }

        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Görevler tamamlanamadı!");
        }
    }

    static void goVirtualThreadsMinimal() throws InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " virtual thread ile çalışıyor.");
                });
            }
        }
    }

    static void goThreads() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(50); // Simule edilen I/O beklemesi
                    System.out.println("Task platform thread ile çalışıyor.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Görevler tamamlanamadı!");
        }
    }

    static void goVirtualThreads() throws InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(50); // Simule edilen I/O beklemesi
                        System.out.println("Task virtual thread ile çalışıyor.");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }

    static void performHeavyCalculation() {
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i; // Simule edilen CPU yükü
        }
    }

    static void goThreadsIterator() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 100; i++) {
            executor.submit(VirtualThreads::performHeavyCalculation);
        }
        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Görevler tamamlanamadı!");
        }
    }

    static void goVirtualThreadsIterator() throws InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                executor.submit(VirtualThreads::performHeavyCalculation);
            }
        }
    }


    // Basit bir WebSocket Listener
    static class SimpleWebSocketListener implements WebSocket.Listener {
        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("WebSocket bağlantısı açıldı.");
            WebSocket.Listener.super.onOpen(webSocket);
        }

        @Override
        public CompletableFuture<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            System.out.println("Sunucudan gelen mesaj: " + data);
            return (CompletableFuture<?>) Listener.super.onText(webSocket, data, last);
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            System.err.println("Hata: " + error.getMessage());
        }

        @Override
        public CompletableFuture<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("WebSocket bağlantısı kapatıldı. Durum: " + statusCode + ", Sebep: " + reason);
            return (CompletableFuture<?>) Listener.super.onClose(webSocket, statusCode, reason);
        }
    }

    static void startWebSocket(){
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // WebSocket URI'sini belirtiyoruz
            URI uri = URI.create("wss://echo.websocket.events"); // Test sunucusu

            // HttpClient oluştur
            HttpClient client = HttpClient.newBuilder()
                    .executor(executor) // Virtual Thread Executor kullan
                    .build();

            // WebSocket istemcisini başlat
            WebSocket webSocket = client.newWebSocketBuilder()
                    .buildAsync(uri, new SimpleWebSocketListener())
                    .join(); // Async işlemi bekle

            // Mesaj gönder
            webSocket.sendText("Merhaba Virtual Threads!", true);

            // WebSocket açık kalırken beklemek için
            Thread.sleep(5000); // Sunucudan yanıt alabilmek için bekliyoruz

            // WebSocket bağlantısını kapat
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Bye!").join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void startMultiWebSocket(){
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                int id = i;
                executor.submit(() -> {
                    URI uri = URI.create("wss://echo.websocket.events");
                    HttpClient client = HttpClient.newBuilder().executor(executor).build();
                    WebSocket webSocket = client.newWebSocketBuilder()
                            .buildAsync(uri, new SimpleWebSocketListener())
                            .join();
                    webSocket.sendText("Mesaj " + id, true);
                    webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Bye!").join();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
