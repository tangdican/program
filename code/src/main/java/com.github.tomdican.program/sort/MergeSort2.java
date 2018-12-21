package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * guide and code, https://www.geeksforgeeks.org/merge-sort/
 * {10,2,6,22,13,23,111,21,227,912,1234}
 */
public class MergeSort2 {

    public static void sort(int[] input) {
        int len = input.length;
        int partLen = 1;
        while (partLen < len) {
            merge(input,partLen);
            Util.printArray(input);

            // because 2 = 1*2
            partLen *= 2;
        }
    }

    public static void merge(int[] input, int partLen) {
        int tempLen = 2*partLen;
        for (int i = 0; i < input.length + tempLen; i += tempLen) {
            sort(input, i, tempLen);
        }
    }

    public static void sort(int[] input, int startLoc, int partLen) {
        int[] temp = new int[partLen];
        int mid = startLoc + partLen/2;
        int left = startLoc;
        int right = mid;
        int rightLen = startLoc + partLen;
        int leftLen = mid;
        int j = 0;
        for (; j < partLen; j++) {

            if ((right >= input.length) || left >= leftLen || right >= rightLen) {
                break;
            }
            if (input[left] <= input[right]) {
                temp[j] = input[left];
                left++;
            } else {
                temp[j] = input[right];
                right++;
            }
        }
        while (left < input.length && left < leftLen) {
            temp[j++] = input[left++];
        }
        while (right < input.length && right < rightLen) {
            temp[j++] = input[right++];
        }
        for (int k = 0; k < partLen; k++) {
            if (startLoc + k >= input.length) {
                break;
            }
            input[startLoc + k] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] input = {10,123,111,22,13,23,21,21,227,912,2};
        MergeSort2.sort(input);
    }
}
