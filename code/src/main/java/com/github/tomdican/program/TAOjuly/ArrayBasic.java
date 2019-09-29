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

        int[] arr = {3, 2, 7, 10, 1, 4, 5, 9, 11};
        // 求两个数和为定值
//        twoSum(arr,arr.length,8);

        // 求和为定值的任意组合数
//        sumOfkNumber(new ArrayList<>(),12,10);

//        // 累积 回溯法 剪枝
//        search(10,12);

        //求一个数组的最大子数组和
        int[] arr1 = {3, 2, -4, 8, 1, -4, 5, 9, -11};
        maxSubArray(arr1, arr1.length);



    }


    static int maxSubArray(int[] a, int n) {
        int currSum = 0;
        int maxSum = a[0];       //全负情况，返回最大数

        for (int j = 0; j < n; j++) {
            currSum = (a[j] > currSum + a[j]) ? a[j] : currSum + a[j];
            maxSum = (maxSum > currSum) ? maxSum : currSum;

        }
        System.out.println(maxSum);
        return maxSum;
    }

    //输入acc， sum， 尝试Wk
    static boolean sumOfkNumber2(int acc, int k, double sum, int M,boolean flag, boolean[] X) {

        X[k] = true;   // 选第k个数
        if (acc + k == M) // 若找到一个和为M，则设置解向量的标志位，输出解
        {
            flag = true;
            for (int i = 1; i <= k; ++i) {
                if (X[i]) {
                    System.out.print(" +"+ i);
                }
            }
            System.out.println(",");
        } else {   // 若第k+1个数满足条件，则递归左子树
            if (acc + k + (k + 1) <= M) {
                flag = sumOfkNumber2(acc + k, k + 1, sum - k, M, flag, X);
            }
            // 若不选第k个数，选第k+1个数满足条件，则递归右子树
            if ((acc + sum - k >= M) && (acc + (k + 1) <= M)) {
                X[k] = false;
                flag = sumOfkNumber2(acc, k + 1, sum - k, M, flag, X);
            }
        }
        return flag;
    }

    static void search(int N, int M) {
        // 初始化解空间
        boolean[] X = new boolean[N + 1];
        double sum = (N + 1) * N * 0.5;
        if (1 > M || sum < M) // 预先排除无解情况
        {
            System.out.printf("not found\n");
            return;
        }
        boolean f = false;
        f = sumOfkNumber2(0, 1, sum, M,f, X);
        if (!f) {
            System.out.printf("not found\n");
        }
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
