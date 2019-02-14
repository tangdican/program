package com.github.tomdican.program.search;

public class InterpolationSearch {
    public static void main(String[] args) {
        int x = 35; // Element to be searched
        int arr[] = new int[]{10, 12, 13, 16,
                18, 19, 20, 21, 22, 23,
                24, 33, 35, 42, 47};
        int index = interpolationSearch(arr, x);

        if (index != -1)
            System.out.println("Element found at index " + index);
        else
            System.out.println("Element not found.");
    }

    private static int interpolationSearch(int[] arr, int x) {

        int lo = 0, hi = (arr.length - 1);

        while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            int xi = (hi-lo) / (arr[hi]-arr[lo]);
            int pos = lo + (
                    (xi) * (x - arr[lo])
            );

            if (arr[pos] == x)
                return pos;
            if (arr[pos] < x)
                lo = pos + 1;
            else
                hi = pos - 1;
        }
        return -1;
    }
}
