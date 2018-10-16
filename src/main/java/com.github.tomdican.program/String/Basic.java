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


        String str1 = "abcc";
        String str2 = "abccac";
        String extraStr = findExtraStr(str1, str2);
        System.out.println(extraStr);
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
