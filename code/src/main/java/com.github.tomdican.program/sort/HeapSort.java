package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class HeapSort {
    // heap sort
    // {4,2,1,6,2,8,3} ---> {8,6,4,2,2,1,3}
    // {4,3,1,2,2,6,8} --->
    public static void heapSort(int[] a) {

        buildMaxHeap(a);

        // move the top element of the heap to the bottom of the heap
        // from big to small
        for (int i = a.length - 1; i >= 0; i--) {
            Util.exchange(a, i, 0);
            adjustHeap(a, i, 0);
        }
    }

    // move the bigger to the parent in all the heap
    public static void buildMaxHeap(int[] a) {

        for (int i = a.length/2; i >= 0; i--) {
            adjustHeap(a, a.length, i);
        }
    }

    // move the bigger to the parent recursively
    public static void adjustHeap(int[] a, int size, int parent) {

        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        int largest = parent;

        if(left < size && a[left] > a[largest]) {
            largest = left;
        }

        if(right < size && a[right] > a[largest]) {
            largest = right;
        }

        if(parent != largest) {
            Util.exchange(a, parent, largest);
            // move bigger to parent recursively
            // move smaller to children recursively
            adjustHeap(a, size, largest);
        }
    }
}
