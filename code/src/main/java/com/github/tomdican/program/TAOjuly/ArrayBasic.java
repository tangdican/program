package com.github.tomdican.program.TAOjuly;

import com.github.tomdican.program.Util;

import java.util.Arrays;
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
//        int[] arr1 = {3, 2, -4, 8, 1, -4, 5, 9, -11};
//        maxSubArray(arr1, arr1.length);

        // 一个台阶总共有n 级，如果一次可以跳1 级，也可以跳2 级。
        //求总共有多少总跳法
//          System.out.println(fibonacci(6));
//          System.out.println(climbStairs(6));

        // 荷兰国旗
//        int[] arr2 = {1,0,0,2,1,0,1,2,1,0};
//        helanguoqi(arr2);
//        Util.printArray(arr2);

        //矩正乘法
//        int[][] a = {{1,2},{3,4}};
//        int[][] b = {{6,7},{8,9}};
//        int[][] c = {{0,0},{0,0}};
//        mulMatrix(a,b,c);
//        System.out.println(Arrays.deepToString(c));

        // 完美洗牌
//        int[] a = {1,1,1,1,2,2,2,2};
//        pefectShuffle1(a,a.length/2);
//        Util.printArray(a);

//        int[] a = {1,1,1,1,2,2,2,2};
//        cycleLeader(a,1,a.length/2);
//        cycleLeader(a,3,a.length/2);
//        Util.printArray(a);


    }

    //copyright@caopengcs 8/24/2013
    //时间O(n)，空间O(1)
    static void perfectShuffle3(int[] a, int n) {
        int n2, m, i, k, t;
        int from = 0;
        for (; n > 1; ) {
            // step 1
            n2 = n * 2;
            for (k = 0, m = 1; n2 / m >= 3; ++k, m *= 3)
                ;
            m /= 2;
            // 2m = 3^k - 1 , 3^k <= 2n < 3^(k + 1)

            // step 2
            rightRotate(a, m, n, from);

            // step 3
            for (i = 0, t = 1; i < k; ++i, t *= 3) {
                cycleLeader(a, t, m, from);
            }

            //step 4
            //    a += m * 2;
            n -= m;
        }
        // n = 1
        t = a[1];
        a[1] = a[2];
        a[2] = t;
    }

    // 旋转字符
    static void rightRotate(int[] s,int n,int m,int from) {
        m %= n;               //若要左移动大于n位，那么和%n 是等价的
        Util.reverse(s, from , from +m - 1); //反转[0..m - 1]，套用到上面举的例子中，就是X->X^T，即 abc->cba
        Util.reverse(s, from +m, from +n - 1); //反转[m..n - 1]，例如Y->Y^T，即 def->fed
        Util.reverse(s, from , from +n - 1); //反转[0..n - 1]，即如整个反转，(X^TY^T)^T=YX，即 cbafed->defab// c。
    }

    //数组下标从1开始，from是圈的头部，mod是要取模的数 mod 应该为 2 * n + 1，时间复杂度O(圈长）
    static void cycleLeader(int[] a, int from,int n,int start) {
        int mod =  2 * n + 1;
        int t, i;
        for (i = from * 2 % mod; i != from; i = i * 2 % mod) {
//            System.out.print(i+",");
            t = a[start+i-1];
            a[start+i-1] = a[start+from-1];
            a[start+ from-1] = t;
        }
    }

    //数组下标从1开始，from是圈的头部，mod是要取模的数 mod 应该为 2 * n + 1，时间复杂度O(圈长）
    static void cycleLeader(int[] a, int from,int n) {
        int mod =  2 * n + 1;
        int t, i;
        for (i = from * 2 % mod; i != from; i = i * 2 % mod) {
//            System.out.print(i+",");
            t = a[i-1];
            a[i-1] = a[from-1];
            a[from-1] = t;
        }
    }



    // 时间O(n)，空间O(n) 数组下标从1开始
    static void pefectShuffle1(int[] a, int n) {
        int n2 = n * 2, i, b[] = new int[n2];
        for (i = 1; i <= n2; ++i) {
//            System.out.print((i * 2) % (n2 + 1) -1  + ",");
            b[(i * 2) % (n2 + 1) - 1] = a[i-1];
        }
        for (i = 0; i < n2; ++i) {
            a[i] = b[i];
        }
    }

    //矩阵乘法，3个for循环搞定
    static void mulMatrix(int matrixA[][], int matrixB[][], int[][] matrixC) {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                matrixC[i][j] = 0;
                for (int k = 0; k < 2; ++k) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
    }

    //引用自gnuhpc
    static void helanguoqi(int[] array) {

        int begin = 0, current = 0, end = array.length-1;
        while (current <= end) {
            if (array[current] == 0) {
                Util.exchange(array,current, begin);
                current++;
                begin++;
            } else if (array[current] == 1) {
                current++;
            } else //When array[current] =2
            {
                Util.exchange(array,current, end);
                end--;
            }
        }
    }


    //1, 1, 2, 3, 5, 8, 13, 21..
    static int climbStairs(int n) {
        int dp[] = {1, 1, 2};
        if (n < 2) {
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[2] = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];
    }

    static int fibonacci(int n)
    {
        int result[] = {0, 1, 2};
        if (n <= 2)
            return result[n];

        return fibonacci(n - 1) + fibonacci(n - 2);
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
