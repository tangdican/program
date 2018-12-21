package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

public class QuickSort {
    public static void main(String [] args){

        int [] a = {8,11,88,1,2,77,9,3,12};
        int[] result;
        result = quicksort(a,0,a.length-1);
        Util.printArray(result);
    }

    public  static  void quicksort(int[] a) {
        quicksort(a,0,a.length-1);
    }

    //quick sort
    public static int[] quicksort(int[] a, int begin, int end) {
        if(begin >= end) {
            return a;
        }
      //  Util.println("");
      //  Util.println("");
      //  printLine(end-begin+1);
      //  printArray(a,begin,end);
        int current = a[begin];
        int i = begin;
        int j = end;
        while( i < j ){
            while (a[j] > current && i < j) {
                j--;
            }

            if (i < j) {
         //       Util.print(a[i]+"("+i+")"+"<="+a[j]+"("+j+")"+" ; ");
                a[i] = a[j];
            }

            while (a[i] <= current && i < j){
                i++;
            }

            if (i < j) {

          //      Util.print(a[j]+"("+j+")"+"<="+a[i]+"("+i+")"+" ; ");
                a[j] = a[i];
            }
          //  Util.println(a[i]+"("+i+")"+"<="+current+"(current) ; ");
          //  Util.println("");
            a[i] = current;
          //  printArray(a,begin,end);
        }

        quicksort(a,begin,i-1);

        return quicksort(a,i+1,end);
    }

}
