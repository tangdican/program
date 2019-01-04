package com.github.tomdican.program.concurrent.reentrantlock;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCache {
    private final static String READ = "read";
    private final static String WRITE = "write";

    public static void main(String[] args) {
        start = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Job();
            thread.setName(READ+i);
            thread.start();
        }
        for (int i = 0; i < 1; i++) {
            Thread thread = new Job();
            thread.setName(WRITE+i);
            thread.start();
        }
        start.countDown();
    }

    private static CountDownLatch start;
    private static class Job extends Thread {

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 2; i++) {
                if (getName().startsWith(READ)) {
                    get(""+i);
                } else {
                    put(""+i,i);
                }
            }
        }

        public String toString() {
            return getName();
        }
    }

    /****************** cache *********************/

    private static final Map<String, Object> map = new HashMap<String, Object>();
    private static final ReadWriteLock rwl = new ReentrantReadWriteLock1(false);
    private static final Lock r = rwl.readLock();
    private static final Lock w = rwl.writeLock();


    public static final Object get(String key) {
        r.lock();
        try {
            System.out.println("Lock by [" + Thread.currentThread().getName() + "], Waiting by " + ((ReentrantReadWriteLock1)rwl).getQueuedThreads1()+",state:"+((ReentrantReadWriteLock1)rwl).getReadHoldCount());
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        w.lock();
        try {
            System.out.println("Lock by [" + Thread.currentThread().getName() + "], Waiting by " + ((ReentrantReadWriteLock1)rwl).getQueuedThreads1()+",state:"+((ReentrantReadWriteLock1)rwl).getWriteHoldCount());
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    static class ReentrantReadWriteLock1 extends ReentrantReadWriteLock {
        ReentrantReadWriteLock1() {

        }
        ReentrantReadWriteLock1(boolean fair) {
            super(fair);
        }

        Collection<Thread> getQueuedWriterThreads1() {
            return this.getQueuedWriterThreads();
        }
        Collection<Thread> getQueuedReadThreads1() {
            return this.getQueuedReaderThreads();
        }
        Collection<Thread> getQueuedThreads1() {
            List<Thread> list = (List)this.getQueuedThreads();
            Collections.reverse(list);
            return list;
        }
    }
}
