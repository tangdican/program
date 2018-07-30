package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * optimize the mergesort
 *
 * reference:
 * https://algs4.cs.princeton.edu/lectures/22Mergesort.pdf
 */
public class MergeSort31 {
    public static void main(String[] args) {
        int[] input = {10,123,111,22,13,23,21,227,912,2};
        sort(input, 0, input.length-1);
    }

    public static void sort(int[] input,int left, int right) {
        if ( left >= right ) {
            return;
        }

        // insertion sort when len <= 4
        if ( right <= left + 3) {
            insertion(input, left, right);
            Util.printArray(input);
            return;
        }

        int mid = (left + right)/2;
        sort(input, left, mid);
        sort(input, mid + 1, right);

        // avoid merging the sorted array
        if (input[mid] <= input[mid+1]) {
            return;
        }

        merge(input, left, mid, right);

        Util.printArray(input);
    }

    // merge
    public static void merge(int[] input, int left,int mid, int right) {
        int len = right - left + 1;
        int[] temp = new int[len];
        int l = left;
        int r = mid + 1;
        int k = 0;
        while (k < len) {
            if (r > right || (l <= mid && input[l] < input[r])) {
                temp[k++] = input[l++];
            } else {
                temp[k++] = input[r++];
            }
        }

        for (int i = 0; i < len; i++) {
            input[left + i] = temp[i];
        }
    }

    // selection sort
    public static void selection(int[] input, int left, int right) {
        if (left >= right) {
            return;
        }
        int min = input[left];
        int i = left;
        while (++i <= right ) {
            if (min > input[i]) {
                Util.exchange(input, left, i);
            }
        }
        selection(input, left+1, right);
    }

    // insertion sort
    public static void insertion(int[] input, int start, int right) {
        if (start > right) {
            return;
        }
        int temp = input[start];
        int i = start;
        while (--i >= 0 && temp < input[i]) {
                input[i+1] = input[i];
        }
        input[i+1] = temp;
        insertion(input, start+1, right);
    }
}
