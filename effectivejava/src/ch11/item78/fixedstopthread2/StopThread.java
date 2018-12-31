package ch11.item78.fixedstopthread2;

import java.util.concurrent.TimeUnit;

// Cooperative thread termination with a volatile field
public class StopThread {
    private static volatile boolean stopRequested;

    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
                i++;
        });
        backgroundThread.start();

        System.out.println(System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
        System.out.println(System.currentTimeMillis());
    }
}
