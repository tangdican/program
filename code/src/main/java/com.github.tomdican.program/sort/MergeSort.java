package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class MergeSort {

    // merge sort
    // sort through grouping recursively
    // {2,4,9,4,1,3,2} ---> {1,2,3,2,4,9,4}
    public static void mergeSort(int[] a) {
        mergeSort(a,0,a.length-1);
    }

    public static void mergeSort(int[] a, int low, int high) {
        if(!(low < high))
            return;
        int mid =  (high + low)/2;
        mergeSort(a,low,mid);
        mergeSort(a,mid+1,high);
        merge(a,low,mid,high);
       // Util.printArray(a);
    }

    // sort the group
    public static void merge(int[] a, int low, int mid, int high) {
        int j = mid + 1;
        int i = low;
        int[] temp = new int[high - low + 1];
        int t = 0;

        // compare between left part and right part
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[t++] = a[i];
                i++;
            } else {
                temp[t++] = a[j];
                j++;
            }
        }

        // left part remaining
        while (i <= mid) {
            temp[t++] = a[i++];
        }

        // right part remaining
        while (j <= high) {
            temp[t++] = a[j++];
        }

        for(int k = 0, l = low; k < temp.length; k++) {
            a[l++] = temp[k];
        }
    }
}
