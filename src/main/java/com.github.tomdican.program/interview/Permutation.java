package com.github.tomdican.program.interview;

import com.github.tomdican.program.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Permutation {
    public static void main(String[] args) {
        int[] a = {5,4,3,1};

        Util.println("permutation recursive:");
        permutation(a,0,a.length);

        sumPermutation(a,a.length,1);
        int sum = sumPermutaion(a.length);
        Util.println("sum2 : "+sum);

        Util.println("permutation loop:");
        permutation(a);
    }

    /** *******************************
     * print all permutations every line
     * the steps:
     * 1. index = 0, n = 4;
     *    index = 1, n = 3;
     *    index = 2, n = 2;
     *    index = 3, n = 1;
     *
     * 2. print(n) = n * print(n-1)
     * 3. exchange(n) = n * exchange(n-1)
     * 4. permutation(index,n) = n * permutation(index+1,n-1)
     *
     * ******************************
     * such as {5,4,3,8}
     * count: 4*3*2*1
     * for first loop
     * i=0
     * 5438,5483,5348,5384,5834,5843
     * i=1
     * 4538,4583,4358，4385,4835,4853
     * i=2
     * 3458,3485,3548,3584,3845,3854
     * i=3
     * 8435,8453,8345,8354,8543,8534
     *
     * recursive
    */
    public static void permutation(int[] a, int start, int len) {
        if(start == (len - 1)) {
            Util.printArray(a);
            return;
        }

        for (int i = start; i < a.length; i++) {
            Util.exchange(a,start,i);
            permutation(a,start+1,len);
            Util.exchange(a,start,i);
        }

    }

    /**
     * print all permutations
     *
     * loop
     */
    public static void permutation(int[] a ) {
        // store all permutations
        ArrayList<ArrayList<Integer>> aa = new ArrayList<ArrayList<Integer>>();

        // convert array to arraylist
        ArrayList<Integer> t = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            t.add(a[i]);
        }
        aa.add(t);

        for (int i = 1; i < a.length; i++) {
            int size = aa.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> temp = aa.get(j);
                for (int k = i-1; k >= 0; k--) {
                    Collections.swap(temp,k,i);
                    aa.add(new ArrayList<Integer>(temp));
                    Collections.swap(temp,k,i);
                }
            }
        }

        // print all permutations
        for (int i = 0; i < aa.size(); i++) {
            Util.println(Arrays.toString(aa.get(i).toArray()));
        }

    }

    /**
     * the sum of all permutations
     *
     * recursive method 1
     */
    public static void sumPermutation(int[] a, int n, int sum) {
        if( n == 1) {
            Util.println("sum : "+String.valueOf(sum));
            return;
        }
        sumPermutation(a,n-1,sum * n);
    }

    /**
     * the sum of all permutations
     *
     * recursive method 2
     */
    public static int sumPermutaion(int n) {
        if(n == 1) {
            return 1;
        }
        return n * sumPermutaion(n-1);
    }
}
