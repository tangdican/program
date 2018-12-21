package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

import java.util.Stack;

/**
 * loop for partition with stack
 */
public class QuickSort33 {
    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3,8,7,2,11,2,11,8,9,22,33,1,33,1,3};
        quickSort(arr, 0 ,arr.length-1 );
        Util.printArray(arr);
    }

    public static void quickSort(int[] arr, int start, int end) {
        Stack<Integer> stack = new Stack();
        stack.push(end+1);
        int l = start;
        while (!stack.isEmpty()) {
            int peek = stack.peek();
            int r = peek-1;
            if ((r - l) > 0) {
                int partition = partition(arr, l, r);
                stack.push(partition);
            } else {
                l++;
                stack.pop();
            }
        }
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
