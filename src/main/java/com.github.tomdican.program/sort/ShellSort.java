package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class ShellSort {

    // shell sort
    // decrease the length of group，sort the corresponding element between the groups
    // d=4,{7,4,1,9,11,3,88,6,3} ---> {3,3,1,6,7,4,88,9,11}
    // when d=1,like the insert sort
    public static void shellSort(int[] a) {

        // group by d
        for (int d = a.length/2; d > 0; d/=2) {
           // traversing the array
            for (int i = d; i < a.length; i++) {
                int temp = a[i];
                int j = i - d;
                // sort the corresponding element between the groups
                while (j >= 0 && temp < a[j]) {
                    a[j+d] = a[j];
                    j -= d;
                }
                a[j+d] = temp;
            }
            Util.printArray(a);
        }
    }
}
