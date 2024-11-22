import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        goVirtualThreadsIterator();
        long endTime = System.nanoTime();
        System.out.println("Metod çalışma süresi: " + (endTime - startTime) / 1_000_000 + " ms");
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

}
