package ch11.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Simple framework for timing concurrent execution 327
public class ConcurrentTimer {
    private ConcurrentTimer() { } // Noninstantiable

    public static long time(Executor executor, int concurrency,
                            Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done  = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown(); // Tell timer we're ready
                try {
                    start.await(); // Wait till peers are ready
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();  // Tell timer we're done
                    System.out.println(">>>>>>>>done..");
                }
            });
        }

        ready.await();     // Wait for all workers to be ready
        long startNanos = System.nanoTime();
        start.countDown(); // And they're off!
        done.await();      // Wait for all workers to finish
        return System.nanoTime() - startNanos;
    }

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            long time = time(executorService, 5, () -> {
                for (int i = 0; i < 10; i++) {
                    System.out.print("," + Thread.currentThread().getName());
                }
                System.out.println("");
            });
            System.out.println(">>>>>>>>>>>>>>>>>"+time);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
