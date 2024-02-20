package hackerRank;

import java.util.stream.IntStream;

public class TestTread {
    public static void main(String[] args) {
        SampleDemo A = new SampleDemo("A");
        SampleDemo B = new SampleDemo("B");
        B.start();
        A.start();
    }

    static class SampleDemo implements Runnable {
        private Thread t;
        private final String threadName;

        SampleDemo(String threadName) {
            this.threadName = threadName;
        }

        public void run() {
            IntStream.range(0, 3)
                    .forEach(item -> System.out.print(threadName));
        }

        public void start() {
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }
    }
}
