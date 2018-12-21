package com.github.tomdican.program.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * synchronization:::
 * Threads communicate primarily by sharing access to fields and the objects reference fields
 * refer to. This form of communication is extremely efficient, but makes two kinds of errors
 * possible: thread interference and memory consistency errors. The tool needed to prevent
 * these errors is synchronization.
 *
 * Thread Interference describes how errors are introduced
 * when multiple threads access shared data.
 * interleave. This means that the two operations consist of multiple steps,
 * and the sequences of steps overlap.
 *
 *
 * Memory Consistency Errors describes errors that result
 * from inconsistent views of shared memory.
 * happen-before
 *
 *
 *
 *
 * synchronized methods and synchronized statements.
 *
 * reentrant synchronization::::
 * Allowing a thread to acquire the same lock more than once enables reentrant synchronization
 *
 *  An atomic action :::::
 *  cannot stop in the middle: it either happens completely,
 *  or it doesn't happen at all
 *  Reads and writes are atomic for reference variables and for most primitive variables
 *  (all types except long and double).
 *  Reads and writes are atomic for all variables declared volatile
 *  (including long and double variables).
 *  memory consistency errors are still possible
 *
 *  volatile:::::
 *  Using volatile variables reduces the risk of memory consistency errors
 *
 *
 * liveness problem ::::
 * deadlock,starvation and livelock.
 *
 *
 * wait()::
 * When a thread invokes d.wait, it must own the intrinsic lock for d
 *
 *
 * Immutable Objects::::
 *
 */
public class ThreadBasic {



    public static void main(String args[]) throws InterruptedException  {

       // sleepMessages();
       // sleepInterrupt(10);

    }

    /**********************************************
     *
     * a Producer-Consumer application
     *
     * The two threads communicate using a shared object. Coordination is essential:
     * the consumer thread must not attempt to retrieve the data before the producer thread
     * has delivered it, and the producer thread must not attempt to deliver new data if
     * the consumer hasn't retrieved the old data
     */
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public synchronized String take() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }


    /*************************************
     *
     * synchronized methods
     *
     * First, it is not possible for two invocations of synchronized methods on the same
     * object to interleave. When one thread is executing a synchronized method for an object,
     * all other threads that invoke synchronized methods for the same object block (suspend
     * execution) until the first thread is done with the object.
     *
     * Second, when a synchronized method exits, it automatically establishes a happens-before
     * relationship with any subsequent invocation of a synchronized method for the same
     * object. This guarantees that changes to the state of the object are visible to all
     * threads.
     *
     * When a thread invokes a synchronized method, it automatically acquires the intrinsic
     *  lock for that method's object and releases it when the method returns.
     *  since a static method is associated with a class, not an object. In this case,
     *  the thread acquires the intrinsic lock for the Class object associated with the class.
     *
     *
     * **/

    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }


    /****
     *
     * For this simple class, synchronization is an acceptable solution.
     * But for a more complicated class, we might want to avoid the liveness
     * impact of unnecessary synchronization. Replacing the int field with
     * an AtomicInteger allows us to prevent thread interference without
     * resorting to synchronization, as in AtomicCounter:
     *
     */

    private AtomicInteger aci = new AtomicInteger(0);

    public void incrementAtomic() {
        aci.incrementAndGet();
    }

    public void decrementAtomic() {
        aci.decrementAndGet();
    }

    public int valueAtomic() {
        return aci.get();
    }

//   using the synchronized keyword with a constructor is a syntax error
//
// public  synchronized ThreadBasic() {
//
//    }


    /*************************************
     *
     * Synchronized Statements

     *
     * **/
    private String lastName;
    private int nameCount;
    private List<String> nameList = new ArrayList<>();
    public void addName(String name) {
        synchronized(this) {
            lastName = name;
            nameCount++;
        }
        nameList.add(name);
    }

    // improving concurrency with fine-grained
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }

    /*********************************************/

    /**
     * interrupt and join
     *
     * @param i
     * @throws InterruptedException
     */
    private static void sleepInterrupt(int i) throws InterruptedException {
        // Delay, in milliseconds before
        // we interrupt MessageLoop
        // thread (default one hour).
        long patience = 1000 * 60 * 60;

        // If command line argument
        // present, gives patience
        // in seconds.
        if (i > 0) {
            try {
                patience = i * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }
        }
        threadMessage("Finally!");
    }


    /**
     * In any case, you cannot assume that
     * invoking sleep will suspend the thread for
     * precisely the time period specified
     *
     */
    private static void sleepMessages() throws InterruptedException {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        long l = System.currentTimeMillis();
        for (int i = 0;
            i < importantInfo.length;
            i++) {
            //Pause for 4 seconds
            Thread.sleep(4000);
            //Print a message
            long ll = System.currentTimeMillis();
            System.out.println(""+(ll - l)+":"+importantInfo[i]);
            l = ll;
        }
    }

    // Display a message, preceded by
    // the name of the current thread
    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
            threadName,
            message);
    }

    private static class MessageLoop
        implements Runnable {
        public void run() {
            String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
            };
            try {
                for (int i = 0;
                    i < importantInfo.length;
                    i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }
}
