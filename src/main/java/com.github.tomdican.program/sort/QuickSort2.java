package com.github.tomdican.program.sort;

public class QuickSort2 extends QuickSort1{
    public static void main(String [] args){

        int [] a = {8,11,88,1,2,77,9,3,12};
        int[] result;
        result = quicksort(a,0,a.length-1);
        printArray(result);
    }

    public static int partition(int[] a, int left, int right){
        int current = a[left];
        int i = left;
        int j = right;

        while( i < j ){

            j = findRightLow(a,i,j,current);
            i = findLeftHigh(a,i,j,current);

        }

        a[i] = current;
        return i;
    }

    public static int findRightLow(int[] a, int i, int j, int current){

        while (a[j] > current && i < j) {
            j--;
        }

        if (i < j) {
            a[i] = a[j];
        }
        return j;
    }

    public static int findLeftHigh(int[] a, int i, int j, int current){

        while (a[i] <= current && i < j){
            i++;
        }

        if (i < j) {
            a[j] = a[i];
        }
        return i;
    }
}
