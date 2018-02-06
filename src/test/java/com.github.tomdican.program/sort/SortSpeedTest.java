package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;
import org.junit.Test;

public class SortSpeedTest {
    @Test
    public void sortSpeedTest() {
        int len = 100000;
        int[] input1 = new int[len];
        int[] input2 = new int[len];
        int[] input3 = new int[len];
        int[] input4 = new int[len];
        int[] input5 = new int[len];
        int[] input6 = new int[len];
        int[] input7 = new int[len];
        int[] input8 = new int[len];
        for (int i = 0; i < input1.length; i++) {

            //int input = input1.length/(i+1);
            int input = input1.length - i;
            input1[i] = input;
            input2[i] = input;
            input3[i] = input;
            input4[i] = input;
            input5[i] = input;
            input6[i] = input;
            input7[i] = input;
            input8[i] = input;
        }

        long starttime = System.currentTimeMillis();
        BubbleSort.bubbleSort(input1);
        long starttime1 = System.currentTimeMillis();
        starttime = starttime1-starttime;
        Util.println("bubble:"+starttime);

        InsertSort.insertSort(input2);
        long starttime2 = System.currentTimeMillis();
        starttime = starttime2 - starttime1;
        Util.println("insert:"+starttime);

        SelectSort.selectSort(input3);
        long starttime3 = System.currentTimeMillis();
        starttime = starttime3 - starttime2;
        Util.println("select:"+starttime);

        ShellSort.shellSort(input4);
        long starttime4 = System.currentTimeMillis();
        starttime = starttime4 - starttime3;
        Util.println("shell:"+starttime);

        QuickSort.quicksort(input5);
        long starttime5 = System.currentTimeMillis();
        starttime = starttime5 - starttime4;
        Util.println("quick:"+starttime);

        MergeSort.mergeSort(input6);
        long starttime6 = System.currentTimeMillis();
        starttime = starttime6 - starttime5;
        Util.println("merge:"+starttime);

        HeapSort.heapSort(input7);
        long starttime7 = System.currentTimeMillis();
        starttime = starttime7 - starttime6;
        Util.println("heap:"+starttime);

        RadixSort.radixSort(input8);
        long starttime8 = System.currentTimeMillis();
        starttime = starttime8 - starttime7;
        Util.println("radix:"+starttime);
    }
}
