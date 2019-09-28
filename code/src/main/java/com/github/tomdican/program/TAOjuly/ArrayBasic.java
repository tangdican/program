package com.github.tomdican.program.TAOjuly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/02.00.md
 */
public class ArrayBasic {
    public static void main(String[] args) {

        Integer[] arr = {3, 2, 7, 10, 1, 4, 5, 9, 11};
        // 求两个数和为定值
//        twoSum(arr,arr.length,8);

        // 求和为定值的任意组合数
        sumOfkNumber(new ArrayList<>(),12,10);

    }

    static void sumOfkNumber(List<Integer> list1, int sum, int n) {
        // 递归出口
        if (n <= 0 || sum <= 0)
            return;

        // 输出找到的结果
        if (sum == n) {
            for (int i = 0; i < list1.size(); i++) {
                System.out.print(list1.get(i) + " + " );
            }
            System.out.println("  +" + n+",");
        }

        list1.add(n);      //典型的01背包问题
        sumOfkNumber(list1, sum - n, n - 1);   //“放”n，前n-1个数“填满”sum-n
        list1.remove(list1.size() - 1);
        sumOfkNumber(list1, sum, n - 1);     //不“放”n，n-1个数“填满”sum
    }

    public static void twoSum(int data[], int length, int sum) {
        Arrays.sort(data); //  如果数组非有序的，那就事先排好序O(N log N)
        int begin = 0;
        int end = length - 1;

        //俩头夹逼，或称两个指针两端扫描法，很经典的方法，O(N)
        while (begin < end) {
            long currSum = data[begin] + data[end];

            if (currSum == sum) {
                //题目只要求输出满足条件的任意一对即可
                System.out.printf("%d %d\n", data[begin], data[end]);

                //如果需要所有满足条件的数组对，则需要加上下面两条语句：
                //begin++
                //end--
                break;
            } else {
                if (currSum < sum)
                    begin++;
                else
                    end--;
            }
        }
    }


}
