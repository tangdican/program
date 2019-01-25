package com.github.tomdican.program.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Basic {
    public static void main(String[] args) {
        int a[] = {2,4,5,10,8,6};
        int[] r = getTwoNums(a,7);
        System.out.println(Arrays.toString(r));
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
