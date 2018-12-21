package com.github.tomdican.program.interview;

import com.github.tomdican.program.Util;

public class Liepin {

    public static void main(String[] args){
        String s = "1042003119";
        int k = 3;

        int min  = minCombinatinNum(s,k);

        Util.println("min: "+String.valueOf(min));
    }

    // 从数字字符串s中，取走k个连续数，找最小组合数
    // 比如：s="1423119",k=3,min=1119
    // 比如：s="1001",k=2,min=1;
    public static int minCombinatinNum(String s, int k) {

        // 拿走k个数后组合数的总数
        int count = s.length()-k+1;

        // 组合数中的最小数
        int min = 0;

        int i = 0;
        while (i < count) {
            // 计算每个组合数
            String str = s.substring(0,i)+s.substring(i+k,s.length());
            int temp  = Integer.parseInt(str);

            // 找其中最小的组合数
            if (temp < min || min == 0)
                min = temp;

            Util.println(String.valueOf(temp));
            i++;
        }
        return min;
    }
}
