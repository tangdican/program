package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class TimSort {

    public static void main(String[] args) {

        int[] a = {2,4,6,1,3,5};

        binarySort(a,0,a.length,0);
        Util.printArray(a);
    }


    /**
     * insert sort for the small length array
     *
     * input: 6,4,2,1,3,5,
     * output: 1,2,3,4,5,6,
     *
     * source: java.util.TimSort: binarySort
     *
     * @param a
     * @param lo
     * @param hi
     * @param start
     */
    private static void binarySort(int[] a, int lo, int hi, int start) {
        if (start == lo)
            start++;
        for ( ; start < hi; start++) {
            int pivot = a[start];

            // find the specific position
            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot < a[mid])
                    right = mid;
                else
                    left = mid + 1;
            }

            // move and insert into the specific position
            int n = start - left;
            arraycopy(a, left, a,left + 1, n);
            a[left] = pivot;
        }
    }

    public static void  arraycopy(int[]a, int src,int[] b, int dest, int len) {
        for (int i = len; i > 0; i--) {
            b[dest + i - 1] = a[src + i - 1];
        }
    }

}
