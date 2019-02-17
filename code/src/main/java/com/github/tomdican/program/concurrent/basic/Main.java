package com.github.tomdican.program.concurrent.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // test heap overflow
//        List list = new ArrayList();
//        for (int i = 0;; i++) {
//            list.add(new Main());
//        }

       //  testArrayBlockingQueue();
//         testPriorityBlockingQueue();
//        testSemaphore();
//        testSemaphore2();

//        testCyclicBarrier();

        // CountDownLatch
//        Driver d = new Driver();
//        d.main();

        // exchanger
        testExchanger();

    }

    private static void testExchanger() {
        Exchanger<String> ex = new Exchanger<String>();
        new Thread(new ConsumerThread(ex)).start();

        new Thread(new ProducerThread(ex)).start();
    }


    static class ProducerThread implements Runnable {
        String str;
        Exchanger<String> ex;
        ProducerThread(Exchanger<String> ex){
            this.ex = ex;
            str = new String();
        }
        @Override
        public void run() {
            for(int i = 0; i < 3; i ++){
                str = "Producer" + i;
                try {
                    str = ex.exchange(str);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

    static class ConsumerThread implements Runnable {
        String str;
        Exchanger<String> ex;
        ConsumerThread(Exchanger<String> ex){
            this.ex = ex;
        }
        @Override
        public void run() {
            for(int i = 0; i < 3; i ++){
                try {
                    str = ex.exchange(new String());
                    System.out.println("Got from Producer " + str);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

  static class Driver { // ...
   void main() throws InterruptedException {
     int N = 3;
     CountDownLatch startSignal = new CountDownLatch(1);
     CountDownLatch doneSignal = new CountDownLatch(N);

     for (int i = 0; i < N; ++i) // create and start threads
       new Thread(new Worker(startSignal, doneSignal)).start();

     doSomethingElse();            // don't let run yet
     startSignal.countDown();      // let all threads proceed
     doSomethingElse();
     doneSignal.await();// wait for all to finish
       System.out.println("finished... ");
   }

   private void doSomethingElse() {
       System.out.println("do something else!");
   }
  }

 static class Worker implements Runnable {
     private final CountDownLatch startSignal;
     private final CountDownLatch doneSignal;

     Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
         this.startSignal = startSignal;
         this.doneSignal = doneSignal;
     }

     public void run() {
         try {
             startSignal.await();
             doWork();
             doneSignal.countDown();
         } catch (InterruptedException ex) {
         } // return
     }

     void doWork() {
         System.out.println("do work..");
     }
 }
    private static void testCyclicBarrier() {

        float[][] martrix = {
                {1,1,1,1},
                {2,2,2,2},
                {3,3,3,3},
                {4,4,4,4}
        };
        Solver solver = new Solver(martrix);
    }

    private static void testSemaphore2() {
        Semaphore semaphore = new Semaphore(4, true);
        new Thread() {
            public void run() {
                try {
                    for (int i = 0; i < 9; i++) {
                        semaphore.acquire();
                        System.out.print(i+"->, ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        for (int i = 0; i < 9; i++) {
            if (i%4==0) {
                try {
                    System.out.print("sleep start..");
                    Thread.sleep(1000);
                    System.out.println("sleep end..");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            semaphore.release();
            System.out.print(i+"<-; ");
        }
    }

    private static void testSemaphore() {
        int max = 10;
        SemaphoresPool semaphoresPool = new SemaphoresPool(max);
       new Thread()  {
           @Override
           public void run() {
               System.out.print("lock:");
               for (int i = 0; i < 2*max; i++) {
                   try {
                       System.out.print(", "+semaphoresPool.getItem());
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }.start();

        new Thread()  {
            @Override
            public void run() {
                System.out.print("unlock:");
                for (int i = 0; i < max; i++) {
                        System.out.print(" ;"+i);
                        semaphoresPool.putItem(i);
                }
            }
        }.start();

    }

    private static void testPriorityBlockingQueue() throws InterruptedException {
        PriorityBlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>(5);

        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i=0;i< 5; i++) {
                        System.out.println(Thread.currentThread().getName() + " PriorityBlockingQueue take:" + blockingQueue.take());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        blockingQueue.put(4);
        blockingQueue.put(7);
        blockingQueue.put(8);
        blockingQueue.put(3);

        Thread.sleep(1000);

        blockingQueue.put(2);
        blockingQueue.put(6);
        blockingQueue.put(1);

        System.out.println(Thread.currentThread().getName()+ " PriorityBlockingQueue:" + blockingQueue);

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
