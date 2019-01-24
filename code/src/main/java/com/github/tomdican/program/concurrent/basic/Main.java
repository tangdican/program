package com.github.tomdican.program.concurrent.basic;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        testArrayBlockingQueue();
    }

    private static void testArrayBlockingQueue() throws InterruptedException {
        int capacity = 2;
        ArrayBlockingQueue<Integer> abq
                = new ArrayBlockingQueue<Integer>(capacity);

//        // add  numbers
//        abq.add(1);
//        abq.add(2);
//        abq.add(3);

        abq.put(1);
        abq.put(2);
        System.out.println("ArrayBlockingQueue take:" + abq.take());

        abq.put(3);
        System.out.println("ArrayBlockingQueue:" + abq);
    }
}
