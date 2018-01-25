package com.github.tomdican.program;

public class QuickSort {
    public static void main(String [] args){

        int [] a = {8,11,88,1,2,77,9,3,12};

        quicksort(a,0,a.length-1);

    }

    //quick sort
    public static void quicksort(int[] a, int begin, int end) {
        if(begin >= end) {
            return;
        }

        int current = a[begin];
        int i = begin;
        int j = end;
        while( i < j ){
            while (a[j] > current&&i<j) {
                j--;
            }

            if (i < j) {
                a[i] = a[j];
            }

            while (a[i]<=current&&i<j){
                i++;
            }

            if (i < j) {
                a[j] = a[i];
            }
            a[i] = current;
            printArray(a);
          //  System.out.println("ij: "+i+","+j);
        }

        quicksort(a,begin,i-1);
        quicksort(a,i+1,end);
    }

    public static void printArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+",");

        }
        System.out.println("");
    }

}
