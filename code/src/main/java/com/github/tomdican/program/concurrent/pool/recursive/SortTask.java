package com.github.tomdican.program.concurrent.pool.recursive;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 *
 * source: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html
 *
 */
public class SortTask extends RecursiveAction {

    public static void main(String[] args) {
        long[] a = new long[1010];
        for (int i = 0; i < a.length; i+=5) {
            int t = i + 5;
            a[i] = t;
            a[i+1] = t-1;
            a[i+2] = t-2;
            a[i+3] = t-3;
            a[i+4] = t-4;
        }

        SortTask sortTask = new SortTask(a);
        sortTask.compute();
        for (int i = 1000; i < 1010; i++) {
            System.out.println(","+a[i]);
        }
    }

    final long[] array; final int lo, hi;
    SortTask(long[] array, int lo, int hi) {
        this.array = array; this.lo = lo; this.hi = hi;
    }
    SortTask(long[] array) { this(array, 0, array.length); }
    protected void compute() {
        if (hi - lo < THRESHOLD)
            sortSequentially(lo, hi);
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid),
                new SortTask(array, mid, hi));
            merge(lo, mid, hi);
        }
    }
    // implementation details follow:
    static final int THRESHOLD = 1000;
    void sortSequentially(int lo, int hi) {
        Arrays.sort(array, lo, hi);
    }

    void merge(int lo, int mid, int hi) {
        long[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ?
                buf[i++] : array[k++];
    }
}
