package com.github.tomdican.program.TAOjuly;

import java.util.Arrays;

public class ArrayBasic {
    public static void main(String[] args) {

        int[] arr = {3, 2, 7, 10, 1, 4, 5, 9, 11};
        Arrays.sort(arr);
        twoSum(arr,arr.length,8);
    }

    public static void twoSum(int data[], int length, int sum)
    {
        //sort(s, s+n);   如果数组非有序的，那就事先排好序O(N log N)

        int begin = 0;
        int end = length - 1;

        //俩头夹逼，或称两个指针两端扫描法，很经典的方法，O(N)
        while (begin < end)
        {
            long currSum = data[begin] + data[end];

            if (currSum == sum)
            {
                //题目只要求输出满足条件的任意一对即可
                System.out.printf("%d %d\n", data[begin], data[end]);

                //如果需要所有满足条件的数组对，则需要加上下面两条语句：
                //begin++
                //end--
                break;
            }
            else{
                if (currSum < sum)
                    begin++;
                else
                    end--;
            }
        }
    }
}
