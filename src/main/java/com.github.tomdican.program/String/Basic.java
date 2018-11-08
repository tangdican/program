package com.github.tomdican.program.String;

/**
 * Pangram: A pangram or holoalphabetic sentence is a sentence using every letter of a given alphabet at least once.
 * The best-known English pangram is “The quick brown fox jumps over the lazy dog.”

 Lipogram: A lipogram is a kind of constrained writing or word game consisting of writing paragraphs or longer works in which a particular letter or group of letters is avoided—usually a common vowel, and frequently E, the most common letter in the English language.
 “The quick brown fox jumped over the lazy dog” omits the letter S
 *
 */
public class Basic {

    public static void main(String[] args) {
//        String str = "abccaccdcaddc";
//        String result = rearrangeAdjacentRepeat(str);
//        System.out.println(result);
//        if (result.length() > 1 && result.charAt(result.length()-1) == result.charAt(result.length()-2)) {
//            System.out.println("not rearrange!!");
//
//        }


//        String str1 = "abcc";
//        String str2 = "abccac";
//        String extraStr = findExtraStr(str1, str2);
//        System.out.println(extraStr);

        // arthimetic operation in string
//        String str = "222222323";
//        int remainder = findRemainderBy11(str);
//        System.out.println("remainder when large number is divided by 11 :"+remainder);

        // Character Counting
        // Print all distinct characters of a string in order
//        String str = "Hello Geeks";
//        String distinctStr = findDistinctChar(str);
//        System.out.println(distinctStr);

        // subsequence and substring
        // Count substrings with each character occurring at most k times
        String str = "aaabb";
        int subCount = findSubString(str, 2);
        System.out.println("sum:"+subCount);
    }

    /**
     *  count substrings character with occurring k times
     *
     * input: aaabb
     *
     * output:
     *   ,a,a,a,b,b
         ,aa,aa,ab,bb
         ,aab,abb
         ,aabb

         sum:12
     *
     * source:  https://www.geeksforgeeks.org/count-substrings-character-occurring-k-times/
     *
     * @param str
     * @param maxTimes
     * @return
     */
    private static int findSubString(String str, int maxTimes) {
        int len = str.length();
        int sumSub = 0;
        for (int j = 1; j <= len; j++) {

            for (int k = 0; k <= len - j; k++) {
                int[] count = new int[256];
                String outStr = "";
                for (int i = k; i < j+k && i < len; i++) {
                    char c = str.charAt(i);
                    outStr += c;
                    if (++count[c] > maxTimes) {
                        i = j;
                        outStr="";
                    }
                }
                if (!outStr.equals("")) {
                    System.out.print(","+outStr);
                    sumSub++;
                }
            }
            System.out.println();
        }
        return sumSub;
    }

    /**
     * find all distinct characters
     * input: Hello Geeks
     * output: H,o,G,k,s,
     *
     * source: https://www.geeksforgeeks.org/print-all-distinct-characters-of-a-string-in-order-3-methods/
     * @param str
     * @return
     */
    private static String findDistinctChar(String str) {
       String result = "";

       int[] count = new int[256];

        for (int i = 0; i < str.length(); i++) {
            if (' '== str.charAt(i)) {
                continue;
            }
            count[str.charAt(i)]++;
        }

        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                result += str.charAt(i)+",";
            }
        }

       return result;
    }

    /**
     * find remainder when large number is divided by 11
     *
     * input: 222222323
     * output: 4
     *
     * source: https://www.geeksforgeeks.org/program-find-remainder-large-number-divided-11
     *
     * @param str
     * @return
     */
    private static int findRemainderBy11(String str) {
        int remainder = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            int divided = remainder * 10 + str.charAt(i) - '0';
            remainder = divided % 11;
        }

        return remainder;
    }

    /**
     * input:
     *  String str1 = "abcc";
        String str2 = "abccac";

        output: ac
     *
     * @param str1
     * @param str2
     * @return
     */
    private static String findExtraStr(String str1, String str2) {
        int extra[] = new int[26];
        for (int i = 0; i < str2.length(); i++) {
            extra[str2.charAt(i)-'a']++;
        }
        for (int i = 0; i < str1.length(); i++) {
            extra[str1.charAt(i)-'a']--;
        }
        String result = "";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < extra[i]; j++) {
                result += (char)('a' + i);
            }
        }
        return result;
    }

    /**
     *  input:  abccaccdcaddc
     *
     *  output: abcacdcacdcdc
     *
     *  source: https://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/
     *
     * @param str
     * @return
     */
    private static String rearrangeAdjacentRepeat(String str) {
        char s[] = str.toCharArray();
        char prev = s[0];
        boolean result = true;
        for (int i = 1; i < s.length; i++) {
            if (s[i] == prev) {
                for (int j = i+1; j < s.length; j++) {
                    if (s[j] != s[i]) {
                        char temp = s[i];
                         s[i] = s[j];
                         s[j] = temp;
                         j = s.length;
                    }
                }

            }
            prev = s[i];
        }
        return String.valueOf(s);
    }

}
