package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class QuickSort1 extends QuickSort {
    public static void main(String [] args){

        int [] a = {8,11,88,1,2,77,9,3,12};
        int[] result;
        result = quicksort(a,0,a.length-1);
        Util.printArray(result);
    }

    //quick sort
    public static int[] quicksort(int[] a, int left, int right) {
        if(left >= right) {
            return a;
        }

        int p = partition(a,left,right);

        quicksort(a,left,p-1);

        return quicksort(a,p+1,right);
    }

    public static int partition(int[] a, int left, int right){
        int current = a[left];
        int i = left;
        int j = right;

        while( i < j ){
            while (a[j] > current && i < j) {
                j--;
            }

            if (i < j) {
                a[i] = a[j];
            }

            while (a[i] <= current && i < j){
                i++;
            }

            if (i < j) {

                a[j--] = a[i];
            }
            
        }

        a[i] = current;
        return i;
    }
}
