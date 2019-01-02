package com.github.tomdican.program.concurrent.reentrantlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 *
 * fair :
 *   Lock by [0], Waiting by [4, 1, 2, 3],state:2
     Lock by [4], Waiting by [1, 2, 3, 0],state:2
     Lock by [1], Waiting by [2, 3, 0, 4],state:2
     Lock by [2], Waiting by [3, 0, 4, 1],state:2
     Lock by [3], Waiting by [0, 4, 1, 2],state:2
     Lock by [0], Waiting by [4, 1, 2, 3],state:2
     Lock by [4], Waiting by [1, 2, 3],state:2
     Lock by [1], Waiting by [2, 3],state:2
     Lock by [2], Waiting by [3],state:2
     Lock by [3], Waiting by [],state:2
 *
 *
 * unfair:
 *
 *   Lock by [4], Waiting by [1, 2, 0, 3],state:2
     Lock by [4], Waiting by [1, 2, 0, 3],state:2
     Lock by [1], Waiting by [2, 0, 3],state:2
     Lock by [1], Waiting by [2, 0, 3],state:2
     Lock by [2], Waiting by [0, 3],state:2
     Lock by [2], Waiting by [0, 3],state:2
     Lock by [0], Waiting by [3],state:2
     Lock by [0], Waiting by [3],state:2
     Lock by [3], Waiting by [],state:2
     Lock by [3], Waiting by [],state:2
 *
 *
 */
public class FairAndUnfairTest2 {

    public static void main(String[] args) {
        FairAndUnfairTest2 fairAndUnfairTest = new FairAndUnfairTest2();
        fairAndUnfairTest.testLock(unfairLock);
        //fairAndUnfairTest.testLock(fairLock);
    }

    private static Lock fairLock   = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);
    private static CountDownLatch start;

    public void fair() {
        testLock(fairLock);
    }

    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(Lock lock) {
        start = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Job(lock);
            thread.setName("" + i);
            thread.start();
        }
        start.countDown();
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 2; i++) {
                lock.lock();
                lock.lock();
                try {
                    System.out.println("Lock by [" + getName() + "], Waiting by " + ((ReentrantLock2) lock).getQueuedThreads()+",state:"+((ReentrantLock2) lock).getHoldCount());
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }

        public String toString() {
            return getName();
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
