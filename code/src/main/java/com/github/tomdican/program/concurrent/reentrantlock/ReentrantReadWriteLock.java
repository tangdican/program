package com.github.tomdican.program.concurrent.reentrantlock;

import com.github.tomdican.program.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReentrantReadWriteLock  implements ReadWriteLock {

    private final ReentrantReadWriteLock.ReadLock readerLock;
    private final ReentrantReadWriteLock.WriteLock writerLock;
    final Sync sync;

    public ReentrantReadWriteLock() {
        this(false);
    }

    public ReentrantReadWriteLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
        readerLock = new ReadLock(this);
        writerLock = new WriteLock(this);
    }

    // 入口
    @Override
    public Lock readLock() {
        return readerLock;
    }

    // 入口
    @Override
    public Lock writeLock() {
        return writerLock;
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {
        static final int SHARED_SHIFT   = 16;
        static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
        static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        // 16-32bit，readLock's state
        static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
        // 1-16bit, writeLock's state
        static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

        abstract boolean readerShouldBlock();
        abstract boolean writerShouldBlock();

//        // get read lock
//        protected final int tryAcquireShared(int unused) {
//            Thread current = Thread.currentThread();
//            int c = getState();
//            // if writelock,return
//            if (exclusiveCount(c) != 0 &&
//                getExclusiveOwnerThread() != current)
//                return -1;
//            // readLock's state
//            int r = sharedCount(c);
//
//            if (!readerShouldBlock() &&
//                r < MAX_COUNT &&
//                compareAndSetState(c, c + SHARED_UNIT)) {
//                if (r == 0) {
//                    firstReader = current;
//                    firstReaderHoldCount = 1;
//                } else if (firstReader == current) {
//                    firstReaderHoldCount++;
//                } else {
//                    HoldCounter rh = cachedHoldCounter;
//                    if (rh == null || rh.tid != getThreadId(current))
//                        cachedHoldCounter = rh = readHolds.get();
//                    else if (rh.count == 0)
//                        readHolds.set(rh);
//                    rh.count++;
//                }
//                return 1;
//            }
//            return fullTryAcquireShared(current);
//        }
    }

    static final class NonfairSync extends Sync {

        final boolean writerShouldBlock() {
            return false;
        }
        final boolean readerShouldBlock() {
            return super.apparentlyFirstQueuedIsExclusive();
        }

    }

    static final class FairSync extends Sync {
        final boolean writerShouldBlock() {
            return hasQueuedPredecessors();
        }
        final boolean readerShouldBlock() {
            return hasQueuedPredecessors();
        }
    }


    static class ReadLock implements Lock{

        private final Sync sync;

        protected ReadLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
        }
        // share lock
        @Override
        public void lock() {
            sync.acquireShared(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.releaseShared(1);
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    static class WriteLock implements Lock {

        private final Sync sync;

        protected WriteLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
        }

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }
}
