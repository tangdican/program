package com.github.tomdican.program.sort;

public class RadixSort {

    // radix sort
    // sort the digital bit from the low bit to the high bit
    // {21,334,8,43,67,245} ,the first bit ---> {21,43,334,245,67,8}
    public static void radixSort(int[] a) {

        int bitNumLimit = 8;
        int bs = 0;
        while (++bs <= bitNumLimit && checkBitExist(a,bs) != 0) {

            int[] count = new int[10];
            int i = 0;

            while (i < a.length) {
                int index = getDigit(a[i++],bs);
                count[index]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] += count[j-1];
            }

            int[] temp = new int[a.length];
            for (int j = a.length - 1 ; j >= 0; j--) {
                int index = getDigit(a[j],bs);
                temp[count[index] - 1] = a[j];
                count[index]--;
            }

            for (int j = 0; j < a.length; j++) {
                a[j] = temp[j];
            }
        }
    }

    // check that the bit of a element of the array exists
    private static int checkBitExist(int[] a,int bs) {
        int indexloop = 0;
        for (int i = 0; i < a.length; i++) {
            indexloop += getDigit(a[i],bs);
        }
        return indexloop;
    }

    // get the digital of the digit at the bit
    // such as,digit=2231,bit=2,return 3
    private static int getDigit(int digit,int bit) {
        int b = bit;
        int tens = 1;
        while (b-- >= 2) {
            tens *= 10;
        }

        int result = digit / tens % 10;
        return result;
    }
}
