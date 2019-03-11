package com.github.tomdican.program.search;

import com.github.tomdican.program.Util;

/**
 * find the kth element from small to big
 * or find the top k element
 *
 * use the quicksort way
 *
 */
public class FindTopK {
    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3,8,7,2,11,2,11,8,9,22,33,1,33,1,3};
        int k = 11;
        int kth = findTopK(arr, k);
        Util.printArray(arr);
        Util.println(""+kth);
    }

    /**
     * T(n) = T(n-x) + n
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findTopK(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int partition = partition(arr, l, r);
            if (k > partition) {
                l = partition + 1;
            } else if (k < partition) {
                r = partition - 1;
            } else {
                return arr[partition];
            }
        }
        return -1;
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
