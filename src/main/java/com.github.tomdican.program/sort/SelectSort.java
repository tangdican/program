package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class SelectSort {

    // select sort
    // select the min element from the unsorted array
    // {1,2,3,9,4,8} ---> {1,2,3,4,9,8}
    public static void selectSort(int[] a){
        int i = 0;
        while (i < a.length) {

            int j = i;
            int temp = a[j];
            while (++j < a.length) {
                if (a[i] > a[j])
                    Util.exchange(a,i,j);
            }
            i++;
        }
    }
}
