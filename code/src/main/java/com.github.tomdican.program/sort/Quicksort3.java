package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * implement unrecursively
 * too complex
 */
public class Quicksort3 {
    public static void main(String[] args) {
        quickSort(8);
        Util.printArray(arr);
    }

    public static int[] arr = {4,5,1,2,3,8,7,2};

    /**
     * {4,5,1,2,3,8,7,2}
     * l,tmpr,i,q,arr
     * 0,7,0,3,{2,2,1,5,3,8,7,-4}
     * 0,2,0,3,{}
     * 0,1,1,2,{1,2,-2,5,3,8,7,-4}
     * 0,0,2,1,{1,-2,-2,5,3,8,7,-4}
     * ***************************
     * 1,1,1,1,{1,2,-2,5,3,8,7,-4}
     * 2,2,0,1,{1,2,2,5,3,8,7,-4}
     * 3,7,-1,1,{1,2,2,5,3,8,7,4}
     *
     * 3,6,0,7,{1,2,2,5,3,4,7,-8}
     * 3,3,1,4,{1,2,2,3,5,4,-7,-8}
     * 4,6,0,4,{1,2,2,3,5,4,7,-8}
     * *************************
     * 4,4,1,5,{1,2,2,3,4,5,-7,-8}
     * 5,6,0,5,{1,2,2,3,4,5,7,-8}
     * 5,5,1,6,{1,2,2,3,4,5,-7,-8}
     * 6,6,0,6,{1,2,2,3,4,5,7,-8}
     * 7,7,-1,6,{1,2,2,3,4,5,7,8}
     */
    public static void quickSort(final int size) {
        int l = 0;
        int r = size - 1;
        int q, i = 0;
        int tmpr = r;
        while (true) {
            i--;
            while (l < tmpr) {
                q = partition2(l, tmpr);
                arr[tmpr] = -arr[tmpr];
                tmpr = q - 1;
                ++i;
            }
            if (i < 0) {
                break;
            }
            l++;
            tmpr = findNextR(l, size);
            arr[tmpr] = -arr[tmpr];
        }
    }

    private static int findNextR(final int l, final int size) {
        for (int i = l; i < size; ++i) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return size - 1;
    }

    /**
     * partition
     * pivot at middle location
     */
    private static int partition(int l, int r) {
        int pivot = arr[(l + r) / 2];
        while (l <= r) {
            while (arr[r] > pivot) {
                r--;
            }
            while (arr[l] < pivot) {
                l++;
            }
            if (l <= r) {
                int tmp = arr[r];
                arr[r] = arr[l];
                arr[l] = tmp;
                l++;
                r--;
            }
        }
        return l;
    }

    /**
     * partition
     * pivot at left location
     */
   private static int partition2(int l, int r) {
        int pivot = arr[l];
        while (l < r) {
            while (l < r && arr[r] >= pivot ) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
            }
            while (l < r && arr[l] <= pivot ) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
            }
        }
        arr[l] = pivot;
        return l;
   }
}
