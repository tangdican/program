package com.github.tomdican.program.TAOjuly;

import com.github.tomdican.program.Util;

/**
 * https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.00.md
 */
public class StringBasic {
    public static void main(String[] args) {
        // 旋转字符
        char[] abcdef = "abcdef".toCharArray();
        LeftRotateString(abcdef,abcdef.length,2);

        System.out.println(abcdef);
    }

    static void LeftRotateString(char[] s,int n,int m) {
        m %= n;               //若要左移动大于n位，那么和%n 是等价的
        Util.reverse(s, 0, m - 1); //反转[0..m - 1]，套用到上面举的例子中，就是X->X^T，即 abc->cba
        Util.reverse(s, m, n - 1); //反转[m..n - 1]，例如Y->Y^T，即 def->fed
        Util.reverse(s, 0, n - 1); //反转[0..n - 1]，即如整个反转，(X^TY^T)^T=YX，即 cbafed->defab// c。
    }
}
