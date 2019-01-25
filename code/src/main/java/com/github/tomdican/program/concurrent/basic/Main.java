package com.github.tomdican.program.concurrent.basic;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // test heap overflow
//        List list = new ArrayList();
//        for (int i = 0;; i++) {
//            list.add(new Main());
//        }

         testArrayBlockingQueue();

    }

    private static void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(2);

        // add numbers
//        abq.add(1);
//        abq.add(2);
//        abq.add(3);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i=0;i< 5; i++) {
                        System.out.println(Thread.currentThread().getName() + " ArrayBlockingQueue take:" + abq.take());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        abq.put(1);
        abq.put(2);
        abq.put(3);
        abq.put(11);
        abq.put(12);
        abq.put(13);

        System.out.println(Thread.currentThread().getName()+ " ArrayBlockingQueue:" + abq);
    }
}
