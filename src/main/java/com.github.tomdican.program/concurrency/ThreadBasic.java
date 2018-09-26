package com.github.tomdican.program.concurrency;

public class ThreadBasic {



    public static void main(String args[]) throws InterruptedException  {

        sleepMessages();

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
}
