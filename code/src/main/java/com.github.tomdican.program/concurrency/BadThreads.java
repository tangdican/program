package com.github.tomdican.program.concurrency;

import java.util.concurrent.atomic.AtomicReference;

public class BadThreads {

    static AtomicReference<String> message = new AtomicReference<>("0");

    private static class CorrectorThread
        extends Thread {

        public void run() {
            try {
                sleep(0);
                System.out.println("子咸亨睡觉结束"+message);
            } catch (InterruptedException e) {

            }
            // Key statement 1:
            message.set("Mares do eat oats.");
        }
    }

    public static void main(String args[])
        throws InterruptedException {

        (new CorrectorThread()).start();


        System.out.println("住县城睡觉结束"+message);
        message.set("Mares do not eat oats.");
        Thread.sleep(0);
        // Key statement 2:
        System.out.println(message);
    }
}
