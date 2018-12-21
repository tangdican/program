package com.github.tomdican.program.interview;

import com.github.tomdican.program.Util;

public class TopKFrequence {
    public static void main(String[] args){

        int[] a = {1,1,2,2,3,3,3,4};
        int k = 2;

        int[] top = topKFrequence(a,k);

        Util.printArray(top);

    }

    // return a array containing the top k frequence in the input array
    // return the bigger element when the frequences equals
    // such as,{1,1,2,2,3,3,3,4},k=2,return [3,2]
    // better than O(NLogN)
    public static int[] topKFrequence(int[] a, int k){

        // store the top k frequent element
        int[] top = new int[k];
        // store the frequence of all elements
        int[] topFrequence = new int[a.length];
        // store the all different elements
        int[] topA = new int[a.length];

        int i = 0;
        int minFrequece = 0;
        int minA = 0;
        while (i < a.length) {
            int loc = 0;

            topFrequence[loc]++;
            topA[loc] = a[i];
            i++;
        }

        int j = 0;
        while(j < k) {
            top[j] = topA[j];
            j++;
        }

        return top;
    }
}
