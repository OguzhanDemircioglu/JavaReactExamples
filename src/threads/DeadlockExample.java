package threads;

public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Lock 1 acquired.");
                synchronized (lock2) {
                    System.out.println("Thread 1: Lock 2 acquired.");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Lock 2 acquired.");
                synchronized (lock1) {
                    System.out.println("Thread 2: Lock 1 acquired.");
                }
            }

//            synchronized (lock1) {
//                System.out.println("Thread 2: Lock 1 acquired.");
//                synchronized (lock2) {
//                    System.out.println("Thread 2: Lock 2 acquired.");
//                }
//            }
        });

        t1.start();
        t2.start();
    }
}
// Death Lock senkronizasyon bozukluyoğundan kaynaklanır