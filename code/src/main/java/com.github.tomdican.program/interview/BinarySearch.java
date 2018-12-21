package com.github.tomdican.program.interview;

public class BinarySearch {
    public static void main(String[] args){
        int[] a = {1,2,3,4,5};
        int r = find(a,4,0,a.length-1);

        System.out.println(r);
    }

    static int find(int[] a, int x , int low, int high){
        if(low > high) return -1;
        int mid = (low + high)/2;
        if(x < a[mid])
           return  find(a, x, 0, mid - 1);
        else if (x > a[mid])
           return  find(a, x, mid + 1, high);

        return mid;

    }
}
