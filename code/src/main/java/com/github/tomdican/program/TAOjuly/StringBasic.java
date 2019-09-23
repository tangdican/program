package com.github.tomdican.program.TAOjuly;

import com.github.tomdican.program.Util;
import org.apache.commons.lang3.StringUtils;

import static java.lang.Math.min;

/**
 * https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.00.md
 */
public class StringBasic {
    public static void main(String[] args) {
        char[] abcdef = "abcdef".toCharArray();

        // 旋转字符
        // LeftRotateString(abcdef,abcdef.length,2);
      //  StringUtils.reverse(abcdef.toString());
     //   System.out.println(abcdef);

        // a是否包含b所有字符
//        char[] b = "ce".toCharArray();
//        StringUtils.contains(abcdef.toString(),b.toString());
//        System.out.println(stringContain(abcdef, b ));

        // 最长回文查找 Manacher算法
//        char[] s = "122123321".toCharArray();
//         String longestPalindrome = getLongestPalindrome(s);
//         System.out.println(longestPalindrome);

        // 字符全排
       // calcAllPermutation(abcdef,0,abcdef.length-1);

    }

    static void calcAllPermutation(char[] perm, int from, int to) {
        if (to <= 1) {
            return;
        }

        if (from == to) {
            for (int i = 0; i <= to; i++)
                System.out.print( ","+perm[i]);
            System.out.println(";");
        } else {
            for (int j = from; j <= to; j++) {
                Util.exchange(perm,j, from);
                calcAllPermutation(perm, from + 1, to);
                Util.exchange(perm,j, from);
            }
        }
    }

    private static String getLongestPalindrome(char[] ss) {
        char[] s=longestPalindromeLoc(ss);

        int mx = 0, id = 0;
        int[] p = new int[s.length];
        for (int i = 1; i < s.length; i++) {
            p[i] = mx > i ? min( 2*id-i<s.length && 2*id-i>=0? p[2 * id - i]:1, mx - i) : 1;

            while (i+p[i]<s.length && i-p[i]>=0 && s[i + p[i]] == s[i - p[i]])
                p[i]++;
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }
        }
        int maxLoc = 0;
        int maxI = 0;
        for (int i = 0; i < p.length; i++) {
            if (maxLoc < p[i]) {
                maxLoc = p[i];
                maxI = i;
            }
        }

        String result = "";
        for (int i = maxI-maxLoc+1; i < maxI+maxLoc; i++) {
            if (s[i]!='#') {
                result += String.valueOf(s[i]);
            }
        }

        return result;

    }

    private static char[] longestPalindromeLoc(char[] s) {
        char[] ss  = new char[2*s.length + 1];
        for (int i = 0; i < ss.length; i++) {
            if (i%2==0) {
                ss[i]='#';
            } else {
                ss[i] = s[i/2];
            }
        }

        return ss;
    }

    private static int longestPalindromeLocationCenter(char[] s) {

        int mx = 0, id = 0;
        int[] p = new int[s.length];
        for (int i = 1; i < s.length; i++) {
            p[i] = mx > i ? min( 2*id-i<s.length && 2*id-i>=0? p[2 * id - i]:1, mx - i) : 1;

            while (i+p[i]<s.length && i-p[i]>=0 && s[i + p[i]] == s[i - p[i]])
                p[i]++;
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }
        }

        int maxLoc = 0;
        int maxI = 0;
        for (int i = 0; i < p.length; i++) {
            if (maxLoc < p[i]) {
                maxLoc = p[i];
                maxI = i;
            }
        }
        return maxI;
    }

    // 旋转字符
    static void LeftRotateString(char[] s,int n,int m) {
        m %= n;               //若要左移动大于n位，那么和%n 是等价的
        Util.reverse(s, 0, m - 1); //反转[0..m - 1]，套用到上面举的例子中，就是X->X^T，即 abc->cba
        Util.reverse(s, m, n - 1); //反转[m..n - 1]，例如Y->Y^T，即 def->fed
        Util.reverse(s, 0, n - 1); //反转[0..n - 1]，即如整个反转，(X^TY^T)^T=YX，即 cbafed->defab// c。
    }

    // a是否包含b所有字符
    // “最好的方法”，时间复杂度O(n + m)，空间复杂度O(1)
    static boolean stringContain(char[] a,char[] b)
    {
        int hash = 0;
        for (int i = 0; i < a.length; ++i) {
// test
//            int i1 = a[i] - 'a';
//            int hash1 = 1 << i1;
//            hash |= hash1;
//            System.out.println("hash:"+Integer.toBinaryString(hash)+",i1:"+Integer.toBinaryString(i1));

            hash |= 1<< a[i]-'a';
        }
        for (int i = 0; i < b.length; ++i) {
            if ((hash & (1 << (b[i] - 'a'))) == 0) {
                return false;
            }
        }
        return true;
    }

}
