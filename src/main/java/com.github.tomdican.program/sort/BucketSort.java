package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * guide, https://www.geeksforgeeks.org/bucket-sort-2/
 * {3,2,4,7,1,5,6}
 */
public class BucketSort {
    public static void sort(int[] input) {
        int len = input.length;
        int[] bucket = new int[100];
        // push input into bucket
        for (int i = 0; i < len; i++) {
          bucket[input[i]] = input[i];
        }

        // pop from bucket
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                input[j++] = bucket[i];
            }
        }

    }

    public static void main(String[] args) {
        int[] input = {3,2,4,7,1,5,6};
        BucketSort.sort(input);
        Util.printArray(input);
    }
}
