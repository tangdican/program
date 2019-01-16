package com.github.tomdican.program.String;

import com.github.tomdican.program.Util;

/**
 * kmp-algorithm-for-pattern-searching
 *
 * source: https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
 */
public class KMPStringMatching {
    void KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        int lps[] = new int[M];
        int j = 0; // index for pat[]

        computeLPSArray(pat, M, lps);
// ACACABAB
// ACAB
// 0010
        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }
    // {0,0,1,2,0,1,2,3,4}
    void computeLPSArray(String pat, int M, int lps[])
    {
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }

        System.out.print("lps[]:");
        Util.printArray(lps);
    }

    public static void main(String args[])
    {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        new KMPStringMatching().KMPSearch(pat, txt);
    }
}
