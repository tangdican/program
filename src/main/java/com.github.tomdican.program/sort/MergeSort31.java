package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class MergeSort31 {
    public static void main(String[] args) {
        int[] input = {10,123,111,22,13,23,21,21,227,912,2};
        MergeSort31.sort(input, 0, input.length-1);
    }

    public static void sort(int[] input,int left, int right) {
        if ( left == right ) {
            return;
        }
//        int mid = (left + right)/2;
//        MergeSort31.sort(input, left, mid);
//        MergeSort31.sort(input, mid + 1, right);

        while (left != right) {
            int mid = (left + right)/2;
            MergeSort31.merge(input, left, mid, right);
        }

        Util.printArray(input);
    }

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
}
