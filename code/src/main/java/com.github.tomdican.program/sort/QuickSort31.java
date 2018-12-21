package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * loop for partition
 * mark partition with negative element
 * insertion for the arr with size 4
 */
public class QuickSort31 {

    public static void main(String[] args) {
        //int[] arr = {4,5,1,2,3,8,7,2};
        int[] arr = {4,5,1,2,3,8,7,2,11,2,11,8,9,22,33,1};
        quickSort(arr,arr.length);
        Util.printArray(arr);
    }

    public static void quickSort(int[] arr, int len) {
        int r = len - 1;
        int l = 0;
        int tempr = r;
        while(l <= tempr) {
            if ((l+3) >= tempr) {
                insertion(arr, l, tempr);
                l = tempr + 1;
                tempr = findNextR(arr, arr.length);
            } else if (l < tempr) {
                int q = partition(arr, l, tempr);
                arr[tempr] = -arr[tempr];
                tempr = q;
            }

        }
    }

    public static int findNextR(int[] arr, int len) {
        int i = 0;
        while(i < len) {
           if (arr[i++]  < 0) {
               arr[i-1] = -arr[i-1];
               return i-1;
           }
        }
        return len-1;
    }

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        while(l < r) {
            while(l < r && arr[r] >= pivot) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
            }

            while(l < r && arr[l] <= pivot) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
            }
        }
        arr[l] = pivot;
        return l;
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
