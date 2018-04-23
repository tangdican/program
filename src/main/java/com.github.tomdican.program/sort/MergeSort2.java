package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * guide and code, https://www.geeksforgeeks.org/merge-sort/
 * {10,2,6,22,13,23,111,21,227,912,1234}
 */
public class MergeSort2 {

    public static void sort(int[] input) {
        int len = input.length;
        int partLen = 0;
        while (++partLen <= len/2) {
            sortPart(input,partLen);
            Util.printArray(input);
        }
    }

    public static void sortPart(int[] input, int partLen) {
        int tempLen = 2*partLen;
        for (int i = 0; ; i += tempLen) {
            int[] temp = new int[tempLen];
            int left = i;
            int right = i+partLen;
            int rightLen = i + 2*partLen;
            int leftLen = i +partLen;
            int j = 0;
            for (; j < partLen; j++) {

                if ((right >= input.length)) {
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
            for (int k = 0; k < tempLen; k++) {
                if (i+k >= input.length) {
                    return;
                }
                input[i+k] = temp[k];
            }

        }
    }

    public static void main(String[] args) {
        int[] input = {10,123,111,22,13,23,21,21,227,912,2};
        MergeSort2.sort(input);
        Util.printArray(input);
    }
}
