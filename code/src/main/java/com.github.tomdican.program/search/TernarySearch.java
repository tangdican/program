package com.github.tomdican.program.search;

/**
 * source: https://www.geeksforgeeks.org/binary-search-preferred-ternary-search/
 */
public class TernarySearch {
    public static void main(String[] args) {
        int arr[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21,
                34, 55, 89, 144, 233, 377, 610};
        int x = 55;

        int index = ternarySearch(arr,0,arr.length-1,x);
        System.out.println(x +": "+index);
    }

    static int ternarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid1 = l + (r - l)/3;
            int mid2 = mid1 + (r - l)/3;

            if (arr[mid1] == x)  return mid1;
            if (arr[mid2] == x)  return mid2;

            if (arr[mid1] > x) return ternarySearch(arr, l, mid1-1, x);
            if (arr[mid2] < x) return ternarySearch(arr, mid2+1, r, x);

            return ternarySearch(arr, mid1+1, mid2-1, x);
        }
        return -1;
    }
}
