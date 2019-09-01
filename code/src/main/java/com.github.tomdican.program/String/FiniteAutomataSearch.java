package com.github.tomdican.program.String;

import java.util.Arrays;

/**
 * source:https://www.geeksforgeeks.org/finite-automata-algorithm-for-pattern-searching/
 */
public class FiniteAutomataSearch {
    public static void main(String[] args) {
        char[] pat = "ACACAGA".toCharArray();
        //char[] pat = "AABAACAADAABAAABAA".toCharArray();
        char[] txt = "AABACABACACAGA".toCharArray();
        faSearch(txt,pat);
    }

    static int NO_OF_CHARS = 256;

    private static void faSearch(char[] txt, char[] pat) {
        int M = pat.length;
        int N = txt.length;

        Integer[][] TF = new Integer[M+1][NO_OF_CHARS];

       // computeTF(pat, M, TF);

        computeTransFun(pat,M,TF); // efficient-constructtion-of-finite-automata
        for (Integer[] one : TF){

            System.out.println(Arrays.toString(Arrays.asList(one).subList(65,91).toArray()));
        }

        // Process txt over FA.
        int i, state = 0;
        for (i = 0; i < N; i++)
        {
            state = TF[state][txt[i]];
            if (state == M)
                System.out.println("Pattern found "
                        + "at index " + (i-M+1));
        }
    }

   // efficient-constructtion-of-finite-automata
    static void computeTransFun(char[] pat,
                                int M, Integer[][] TF)
    {
        int i, lps = 0, x;

        // Fill entries in first row
        for (x = 0; x < NO_OF_CHARS; x++)
        {
            TF[0][x] = 0;
        }
        TF[0][pat[0]] = 1;

        // Fill entries in other rows
        for (i = 1; i < M; i++)
        {
            // Copy values from row at index lps
            for (x = 0; x < NO_OF_CHARS; x++)
            {
                TF[i][x] = TF[lps][x];
            }

            // Update the entry corresponding to this character
            TF[i][pat[i]] = i + 1;

            // Update lps for next row to be filled
            if (i < M)
            {
                lps = TF[lps][pat[i]];
            }
        }
    }




    private static void computeTF(char[] pat, int M, Integer[][] TF) {
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = getNextState(pat, M, state, x);
    }

    private static int getNextState(char[] pat, int M, int state, int x) {
        if(state < M && x == pat[state])
            return state + 1;

        int ns, i;
        for (ns = state; ns > 0; ns--)
        {
            if (pat[ns-1] == x)
            {
                for (i = 0; i < ns-1; i++)
                    if (pat[i] != pat[state-ns+1+i])
                        break;
                if (i == ns-1)
                    return ns;
            }
        }

        return 0;
    }
}
