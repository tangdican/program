package com.github.tomdican.program.TAOjuly;

import com.github.tomdican.program.Util;

/**
 * https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.00.md
 */
public class StringBasic {
    public static void main(String[] args) {
        char[] abcdef = "abcdef".toCharArray();

        // 旋转字符
        // LeftRotateString(abcdef,abcdef.length,2);
//        System.out.println(abcdef);

        // a是否包含b所有字符
        char[] b = "ce".toCharArray();
        System.out.println(stringContain(abcdef, b ));

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
