package com.github.tomdican.program.TAOjuly;

public class DynamicBasic {

    public static void main(String[] args) {

        // “输入三个字符串s1、s2和s3，判断第三个字符串s3是否由前两个字符串s1和s2交错而成”
        String s1 = "aabcc",s2 = "dbbca",s3 = "aadbbcbcac";
        System.out.println(isInterleave(s1,s2,s3));

    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), s = s3.length();

        //如果长度不一致，则s3不可能由s1和s2交错组成
        if (n + m != s)
            return false;

        boolean[][] dp = new boolean[n + 1][m + 1];

        //在初始化边界时，我们认为空串可以由空串组成，因此dp[0][0]赋值为true。
        dp[0][0] = true;

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (dp[i][j]

                        || (i - 1 >= 0 && dp[i - 1][j] == true &&
                        //取s1字符
                        s1.charAt(i - 1) == s3.charAt(i + j - 1))

                        || (j - 1 >= 0 && dp[i][j - 1] == true &&
                                //取s2字符
                                s2.charAt(j - 1) == s3.charAt(i + j - 1)))

                    dp[i][j] = true;
                else
                    dp[i][j] = false;
            }
        }
        return dp[n][m];
    }

}
