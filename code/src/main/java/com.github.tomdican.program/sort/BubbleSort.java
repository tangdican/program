package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class BubbleSort {

    // bubble sort
    // compare a[i] to a[i+1],put the max element to the end of the array
    // such as,{3,2,1,4,5,6} ---> {2,1,3,4,5,6}
    public static void bubbleSort(int[] a ) {

        for (int i = a.length-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
               if (a[j] > a[j+1])
                   Util.exchange(a,j,j+1);
            }
        }
    }
}
