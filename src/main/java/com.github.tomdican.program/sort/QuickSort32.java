package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class QuickSort32 {
    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3,8,7,2,11,2,11,8,9,22,33,1};
        quickSort(arr,0, arr.length-1);
        Util.printArray(arr);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(arr, start, end);
        quickSort(arr, start, partition-1);
        quickSort(arr, partition+1, end);
    }

    // left as pivot
    public static int partition(int[] arr, int start, int end) {

        int l = start, r = end, pivot = arr[start];
        while (l < r) {
            while (arr[r] > pivot) {
                r--;
            }
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            if (l < r) {
                Util.exchange(arr, l, r);
            }
        }
        Util.exchange(arr, start, l);

        return l;
    }
}
