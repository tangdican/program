package com.github.tomdican.program.sort;

public class InsertSort {

    // insert sort
    // insert a element into the right place of the array
    // such as,{1,3,6,8,10,2} ---> {1,2,3,6,8,10}
    public static void insertSort(int[] a){
        int i = 1;
        int j = 0;
        while (i < a.length) {
            j = i - 1;
            int temp = a[i];
            while (j >= 0 && temp < a[j]) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = temp;
            i++;
        }
    }
}
