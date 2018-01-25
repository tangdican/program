package com.github.tomdican.program;

public class QuickSort {
    public static void main(String [] args){

        int [] a = {8,11,88,1,2,77,9,3,12};
        int[] result;
        result = quicksort(a,0,a.length-1);
        printArray(result);
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

    public static void printArray(int[] a){
      printArray(a,0,a.length-1);
    }

    public static void printArray(int[] a,int begin,int end){
        for (int i = begin; i <= end; i++) {
            Util.print(a[i]+",");
        }
        Util.println("");
    }

    public static void printLine(int n){
        while (n > 0){
            Util.print("--->");
            n--;
        }
        Util.println("");
    }
}
