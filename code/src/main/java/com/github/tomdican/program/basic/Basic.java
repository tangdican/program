package com.github.tomdican.program.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Basic {
    public static void main(String[] args) {
    //    int a[] = {2,4,5,10,8,6};
//        int[] r = getTwoNums(a,7);
//        System.out.println(Arrays.toString(r));

        int a[] = {1,2,4,5,6,7,8,9,10,11};
        printLadder(a,0,1);
    }

    /**
     * output:
     * 1
     * 2 3
     * 4 5 6
     * 7 8 9 10
     *
     * @param a
     */
    private static void printLadder(int[] a, int start, int len) {
        if (start >= a.length) return;

        for (int i = start; i < (start + len) && i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println("");
        printLadder(a,start+len,len+1);
    }

    /**
     * 从一个数组中返回两个值相加等于目标值的下标
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] getTwoNums(int[] nums, int target) {
            int[] result = new int[2] ;
            Map<Integer,Integer> map = new HashMap<>(2) ;
            for (int i=0 ;i<nums.length;i++){

                if (map.containsKey(nums[i])){
                    result = new int[]{map.get(nums[i]),i} ;
                }
                map.put(target - nums[i],i) ;
            }
            return result ;
    }

}
