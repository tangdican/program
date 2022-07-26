package com.github.tomdican.program.String;


import com.github.tomdican.program.Util;
import com.github.tomdican.program.interview.Permutation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
//        String str = "aaabb";
//        int subCount = countSubString(str, 2);
//        //int subCount = findSubString(str, 2);
//        System.out.println("sum:"+subCount);

        // reverse and rotation
//        String str = "bababaa";
//        String result = reverse(str,0, str.length()-1);
//        System.out.println("reverse string: "+ result);
//        String resultLeft = leftRotation(str, 2);
//        System.out.println("left rotation:" + resultLeft);
//        String resultRight = rightRotation(str, 2);
//        System.out.println("right rotation:" + resultRight);

        // sorting and search
//        String[] str = {"a","bbc","aa","aac","cbc","bcb"};
//        sortByTrie(str);

        // Case Sensitive String
//        String str = "aBCxF";
//        String result = toggleCase(str);
//        System.out.println(result);

        // Occurrence Based String
//        String str = "geeksquikzg";
//        char result = findFirstNonRepeatChar(str);
//        result = findFirstRepeatChar(str);
//        System.out.println(result);

        // Anagram
//        String[] str = {"listen","abc","silent","cba","ww","a"};
//        findAnagram(str);

        // Palindromic
        // A string is said to be palindrome if reverse of the string is same as string
//        String str = "cbccbc";
//        int count = countPalindromic(str);
//        System.out.println("sub Palindromic sum: "+ count);

        // binary string
//        String binaryStr = "0000";
//        //String result = twoComplement(binaryStr);
//        String result = twoComplementEfficency(binaryStr);
//        System.out.println(result);

        // Lexicographic pattern
//        String str = "abcd";
//        int count = countPowerSet(str.toCharArray(), "", 0, str.length());
//        //int count = countPermutationsLoop(str);
//        //int count = countPermutationsRec(str.toCharArray(), 0, str.length()-1);
//       // int count = countPermutationsRecWithRepeat(str.toCharArray(), 0, str.length()-1);
//        System.out.println("");
//        System.out.println(count);

        // Pattern Searching
//        String p = "AABAACAADAABAABA";
//        String t = "ABA";
//        int[] index = patternSearchWithFP(p, t);
//       // int[] index = patternSearchWithSimple(p, t);
//        Util.printArray(index);

//        // split string
//        String[] words = {"mobile","samsung","sam","sung",
//            "man","mango","icecream","and",
//            "go","i","like","ice","cream"};
//        String str = "ilikesamsungicecream";
////        String result = printWordBreak(words, str);
////        System.out.println(result);
//
//        String[] results = new String[10];
//        printAllWordBreak(words, str, 0, str.length(), "", results);
//        for (int i = 0; i < results.length; i++) {
//            if (results[i] != null) {
//                System.out.println(results[i]);
//            }
//        }

//        // Balance Parentheses & Bracket Evaluation
//        String str = "{()}[]";
//        String str2 = "{{(}()}";
//        int loc = checkedParenthesis(str);
//        System.out.println(loc+"");
//        int loc2 = checkedParenthesis(str2);
//        System.out.println(loc2+"");

        // conversion
        String str = "banana$";
        String result = burrowWheelerTransform(str);
        System.out.println("");
        System.out.println(result);


    }

    /***
     * Burrows – Wheeler Data Transform Algorithm
     *
     * input: banana$
     * output: annb$aa
     *
     * source: https://www.geeksforgeeks.org/burrows-wheeler-data-transform-algorithm/
     *
     * @param str
     * @return
     */
    private static String burrowWheelerTransform(String str) {
        int len = str.length();
        char[] strs = str.toCharArray();
        char[] result = new char[len];
        String[] sortStr = new String[len];

        for (int i = 0; i < len; i++) {
            sortStr[i] = leftRotation(str, i+1);
        }
        String[] resultSort = sortByTrieChar(sortStr);
        for (int i = 0; i < len; i++) {
            result[i] = resultSort[i].charAt(len-1);
        }
        return String.valueOf(result);
    }

    /**
     *
     * Sorting array of strings (or words) using Trie
     *
     * input: "a","bbc","aa","aac","cbc","bcb"
     * output: a, aa, aac, bbc, bcb, cbc,
     *
     * source: https://www.geeksforgeeks.org/sorting-array-strings-words-using-trie/
     *
     * @param str
     */
    private static String[] sortByTrieChar(String[] str) {
        TrieChar trie = new TrieChar();
        int len = str.length;
        for (int i = 0; i < len; i++) {
            String strElement = str[i];
            int strLen = strElement.length();
            trie.sort(strElement, strLen, i);
        }

        String[] result = new String[str.length];
        Queue<String> queue =  trie.printOrderString(str);
        int i = 0;
        while (queue != null && !queue.isEmpty()) {
            result[i++] = queue.poll();
        }
        return result;
    }

    static class TrieChar {
        int size = 256;
        int index = -1;
        int count = 0;
        TrieChar[] child = null;

        public void sort(String element, int len, int loc) {
            TrieChar next = this;
            for (int i = 0; i < len; i++) {
                if (next.child == null) {
                    next.child = new TrieChar[size];
                }
                int index = element.charAt(i);
                //next.child[index].index++;
                if (next.child[index] == null) {
                    next.child[index] = new TrieChar();
                }
                next = next.child[index];
            }
            next.index = loc;
            next.count++;
        }

        public Queue<String> printOrderString(String[] str) {
            Queue<String> queue = new LinkedList<>();
            if (index != -1) {
                for (int i = 0; i < count; i++) {
                    System.out.print(str[index]+", ");
                    queue.add(str[index]);
                }
            }
            if (child != null) {
                for (int i = 0; i < size; i++) {
                    if (child[i] != null) {
                        Queue<String> temp = child[i].printOrderString(str);
                        while (temp != null && !temp.isEmpty()) {
                            queue.add(temp.poll());
                        }
                    }
                }
            }
            return queue;
        }
    }

    /**
     * Check for balanced parentheses in an expression
     *
     * input: {{(}()}
     * output: 3
     *
     * source: https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
     *
     * @param str
     * @return
     */
    private static int checkedParenthesis(String str) {
        int loc = 0;
        Stack<Integer> stack = new Stack<>();
        char[] strs = str.toCharArray();
        int i = 0;
        for (; i < str.length(); i++) {
            char c;
            if (stack.isEmpty()) {
                stack.push(Integer.valueOf(strs[i]));
                continue;
            } else {
                c = (char)stack.peek().intValue();
            }
            if (')' == strs[i]) {
                if ('(' != c ) {
                    loc = i;
                    break;
                } else {
                    stack.pop();
                }
            } else if ('}' == strs[i]) {
                if ('{' != c ) {
                    loc = i;
                    break;
                } else {
                    stack.pop();
                }
            } else if (']' == strs[i]) {
                if ('[' != c ) {
                    loc = i;
                    break;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(Integer.valueOf(strs[i]));
            }
        }
        if (!stack.isEmpty() && i == str.length()) {
            loc = str.length();
        }

        return loc;
    }

    /**
     * print all words with break
     *
     * input: ilikesamsungicecream
     * output:
     *  i like sam sung ice cream
        i like sam sung icecream
        i like samsung ice cream
        i like samsung icecream

        source: https://www.geeksforgeeks.org/word-break-problem-using-backtracking/
     *
     * @param words
     * @param str
     * @param startIndex
     * @param len
     * @param result
     * @param results
     */
    private static void printAllWordBreak(String[] words, String str, int startIndex, int len, String result, String[] results) {
        if (startIndex >= len) {
            for (int i = 0;i< results.length;i++) {
                if(results[i] == null || results[i].isEmpty() || results[i].equals("")) {
                    results[i] = result;
                    break;
                }
            }
            return;
        }

        for (int i = startIndex; i < len; i++) {
            String temp = str.substring(startIndex, i + 1);
            if (containWord(words, temp)) {
                printAllWordBreak(words,str,i+1, len, result + (temp + " "), results);
            }
        }
    }

    private static String printWordBreak(String[] words, String str) {
        int len = str.length();
        String result = "";
        int startIndex = 0;
        for (int i = 0; i < len; i++) {
            String temp = str.substring(startIndex, i+1);
            if (containWord(words, temp)) {
                startIndex = i + 1;
                result += (temp + " ");
            }
        }
        return result;
    }

    private static boolean containWord(String[] words, String substring) {
        int len = words.length;
        for (int i = 0; i < len; i++) {
            if (words[i].equals(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pattern Searching
     * input: AABAACAADAABAABA
     * output: ABA
     *
     * source: https://www.geeksforgeeks.org/finite-automata-algorithm-for-pattern-searching/
     *
     * @param p
     * @param t
     * @return
     */
    private static int[] patternSearchWithSimple(String p, String t) {
        int pLen  = p.length();
        int tLen = t.length();
        char[] ps = p.toCharArray();
        char[] ts = t.toCharArray();
        int[] index = new int[pLen];
        int loc = 0;

        for (int i = 0; i < pLen; i++) {
            if (ps[i] == ts[0]) {
                for (int j = 1; j < tLen; j++) {
                    if (i+j >= pLen || ps[i+j] != ts[j]) {
                        j = tLen;
                    }
                    if (j == tLen - 1) {
                        index[loc++] = i;
                    }
                }
            }
        }

        return index;
    }

    private static int[] patternSearchWithFP(String p, String t) {
        int pLen  = p.length();
        int tLen = t.length();
        char[] ps = p.toCharArray();
        char[] ts = t.toCharArray();
        int[][] TF = new int[pLen][256];

        // construct TF
        for (int i = 0; i < pLen; i++) {
            TF[i][ps[i]] = i + 1;
        }

        for (int i = 0; i < pLen; i++) {

        }

        return new int[0];
    }

    /**
     * print all permutations of a given string
     * avoid the repeat char
     *
     * input: abbc
     *
     * output: abbc,abcb,acbb,babc,bacb,bbac,bbca,bcba,bcab,cbba,cbab,cabb,
     *
     * 12
     *
     *
     * @param strs
     * @param l
     * @param r
     * @return
     */
    private static int countPermutationsRecWithRepeat(char[] strs, int l, int r) {
        if ( l == r) {
            System.out.print(String.valueOf(strs)+",");
            return 1;
        } else {
            int count = 0;
            for (int i = l; i <= r; i++) {
                // void the repeat char
                if (i != l && (strs[i] == strs[l] || strs[i-1] == strs[i])) {
                    continue;
                }
                Util.exchange(strs, l, i);
                count += countPermutationsRecWithRepeat(strs, l + 1, r);
                Util.exchange(strs, l, i);
            }
            return count;
        }
    }

    /**
     * print all permutations of a given string
     * recursively
     *
     * input: abcd
     * output: abcd,abdc,acbd,acdb,adcb,adbc,bacd,badc,bcad,bcda,bdca,bdac,
     * cbad,cbda,cabd,cadb,cdab,cdba,dbca,dbac,dcba,dcab,dacb,dabc,
     *
     * 24
     *
     *
     * source: https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
     *
     * @param strs
     * @return
     */
    private static int countPermutationsRec(char[] strs, int l, int r) {
        if ( l == r) {
            System.out.print(String.valueOf(strs)+",");
            return 1;
        } else {
            int count = 0;
            for (int i = l; i <= r; i++) {
                Util.exchange(strs, l, i);
                count += countPermutationsRec(strs, l + 1, r);
                Util.exchange(strs, l, i);
            }
            return count;
        }
    }


    /**
     * print all permutations
     *
     * store in queue
     *
     * loop
     *
     * inpput: abcd
     *
     * output: a,b,c,d, | a,b,d,c, | a,c,b,d, | a,c,d,b, | a,d,c,b, | a,d,b,c, | b,a,c,d, | b,a,d,c, | b,c,a,d, | b,c,d,a, | b,d,c,a, | b,d,a,c,
     * | c,b,a,d, | c,b,d,a, | c,a,b,d, | c,a,d,b, | c,d,a,b, | c,d,b,a, | d,b,c,a, | d,b,a,c, | d,c,b,a, | d,c,a,b, | d,a,c,b, | d,a,b,c, |
     *
     * 24
     *
     * @param str
     * @return
     */
    private static int countPermutationsLoop(String str) {
      return Permutation.countPermutationStack(str.toCharArray());
    }

    /**
     * print all substring
     *
     * input: abcd
     *
     * output: a,ab,abc,abcd,abd,ac,acd,ad,b,bc,bcd,bd,c,cd,d,
     * 15
     *
     * @param strs
     * @param curr
     * @param i
     * @param len
     * @return
     */
    private static int countPowerSet(char[] strs, String curr, int i, int len) {
        int count = 0;
        if (i > len) {
            return 0;
        } else {
            if (!curr.equals("")) {
                System.out.print(curr + ",");
                count++;
            }
            for (int j = i; j < len; j++) {
                curr += strs[j];
                count += countPowerSet(strs, curr, j+1, len);
                curr = curr.substring(0,curr.length()-1);
            }
        }
        return count;
    }

    /**
     * 2’s complement of a binary string
     * efficency
     *
     * input: "0000"
     * output: "10000"
     *
     * source: https://www.geeksforgeeks.org/efficient-method-2s-complement-binary-string/
     *
     * @param binaryStr
     * @return
     */
    private static String twoComplementEfficency(String binaryStr) {
        char[] strs = binaryStr.toCharArray();
        int len = binaryStr.length();
        boolean firstOne = false;
        for (int i = len - 1; i >= 0 ; i--) {
                if (firstOne) {
                    if (strs[i] == '1') {
                        strs[i] = '0';
                    } else if (strs[i] == '0') {
                        strs[i] = '1';
                    }
                } else {
                    if (strs[i] == '1') {
                        firstOne = true;
                    }
                }
        }
        return (firstOne ? "": "1") + String.valueOf(strs);
    }

    /***
     * 2’s complement of a binary string
     *
     * input: 0000
     * output: 10000
     *
     * source: https://www.geeksforgeeks.org/1s-2s-complement-binary-number/
     *
     * @param binaryStr
     * @return
     */
    private static String twoComplement(String binaryStr) {
        // 1's complement
        char[] strs = binaryStr.toCharArray();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == '1') {
                strs[i] = '0';
            } else if (strs[i] == '0') {
                strs[i] = '1';
            }
        }

        // 2's complement
        boolean upOne = false;
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i] == '1') {
                strs[i] = '0';
                upOne = true;
            } else if (strs[i] == '0'){
                strs[i] = '1';
                upOne = false;
                break;
            }
        }

        return (upOne ? "1" : "") + String.valueOf(strs);
    }

    /**
     * Count the number of possible palindrome substrings in a string
     * input: cbccbc
     *
     * output:  c,b,cbc,c,cc,bccb,cbccbc,c,b,cbc,c,
     * output: 11
     *
     * source: https://leetcode.com/discuss/interview-question/125095/count-palindromes
     *
     * @param str
     * @return
     */
    private static int countPalindromic(String str) {
        int count = 0;
        int len = str.length();
        for (int mid = 0; mid < len; mid++) {
            // substring of the odd length
            for (int j = 0; mid >= j && mid < len - j && str.charAt(mid - j) == str.charAt(mid + j); j++) {
                count++;
                System.out.print(str.substring(mid-j, mid+j+1) + ",");
            }
            // substring of the even length
            for (int j = 1; mid >= j - 1 && mid < len - j && str.charAt(mid - j + 1) == str.charAt(mid + j); j++) {
                count++;
                System.out.print(str.substring(mid-j+1, mid+j+1) + ",");
            }
        }
        System.out.println("");
        return count;
    }

    /***
     * Given a sequence of words, print all anagrams together
     *
     * input: "listen","abc","silent","cba","ww","a"
     * output: abc, cba, listen, silent,
     *
     *  source: https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together-set-2/
     *
     * @param str
     */
    private static void findAnagram(String[] str) {
        int len = str.length;
        String[] newStr = new String[str.length];
        for (int i = 0; i < len; i++) {
            int[] count = new int[256];
            for (int j = 0; j < str[i].length(); j++) {
                count[str[i].charAt(j)]++;
            }
            newStr[i] = "";
            for (int j = 0; j < count.length; j++) {
                for (int k = 0; k < count[j]; k++) {
                    newStr[i] += (char)j;
                }
            }
        }

        Tries tries = new Tries();
        for (int i = 0; i < newStr.length; i++) {
            tries.sort(newStr[i], newStr[i].length(), i);
        }
        tries.printOrderString(str);
    }

    static class Tries {
        int size = 26*2;
        // the max repeat times of string
        int index[] = new int[10];
        Tries[] child = null;

        public void sort(String element, int len, int loc) {
            Tries next = this;
            for (int i = 0; i < len; i++) {
                if (next.child == null) {
                    next.child = new Tries[size];
                }
                int index = getIndex(element.charAt(i));
                //next.child[index].index++;
                if (next.child[index] == null) {
                    next.child[index] = new Tries();
                }
                next = next.child[index];
            }

            for (int i = 0; i < next.index.length; i++) {
                if (next.index[i] == 0 ) {
                    next.index[i] = loc + 1; // in order for setting 0 to empty
                    i = next.index.length;
                }
            }
        }

        public void printOrderString(String[] str) {
            if (index[1] != 0) { // only print anagram
                for (int i = 0;i < index.length && index[i] > 0; i++) {
                    System.out.print(str[index[i]-1]+", ");
                }
            }
            if (child != null) {
                for (int i = 0; i < size; i++) {
                    if (child[i] != null) {
                        child[i].printOrderString(str);
                    }
                }
            }

        }

        private int getIndex(char c) {
            if (c >= 'a') {
                return 26 + c - 'a';
            } else {
                return c - 'A';
            }
        }
    }


    /***
     * find first repeated character in a string
     * use bits to store
     *
     * input: geeksquikzg
     * output: e
     *
     * source : https://www.geeksforgeeks.org/efficiently-find-first-repeated-character-string-without-using-additional-data-structure-one-traversal/
     *
     * @param str
     * @return
     */
    private static char findFirstRepeatChar(String str) {
        int charBit = 0;
        int len = str.length();
        int result = 0;
        for (int i = 0; i < len; i++) {
            int mvBits = str.charAt(i) - 'a';
            int oneBit = (1 << mvBits);
            if ((charBit & oneBit) > 0) {
                result = str.charAt(i);
                i = len;
            } else {
                charBit |= oneBit;
            }
        }
        return (char)result;
    }

    /**
     * Given a string, find its first non-repeating character
     *
     * input: geeksquikzg
     * output: s
     *
     * source: https://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
     *
     * @param str
     * @return
     */
    private static char findFirstNonRepeatChar(String str) {
        int result = 0;
        int len = str.length();
        Count[] counts = new Count[Count.maxSize];
        // mark and count
        for (int i = 0; i < len; i++) {
            int index = Count.getIndex(str.charAt(i));
            if (counts[index] == null) {
                counts[index] = new Count(1, i);
            } else {
                counts[index].count++;
            }
        }

        // find first non repeat char
        int minLoc = counts.length;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != null && counts[i].count == 1 && counts[i].index < minLoc) {
                minLoc = counts[i].index;
            }
        }
        result = str.charAt(minLoc);
        return (char)result;
    }

    static class Count {
        int count;
        int index;
        static final int maxSize = 2*26;

        public Count(int count, int index) {
            this.count = count;
            this.index = index;
        }

        public static int getIndex(char c) {
            if (c >= 'a') {
                return 26 + c - 'a';
            } else {
                return c - 'A';
            }
        }
    }

    /**
     * Toggle case of a string using Bitwise Operators
     *
     * input: aBCxF
     * output: AbcXf
     *
     * source: https://www.geeksforgeeks.org/toggle-case-string-using-bitwise-operators/
     *
     * @param str
     * @return
     */
    private static String toggleCase(String str) {
        char s[] = str.toCharArray();
        int caseDigit = 'a' - 'A';
        for (int i = 0; i < s.length; i++) {
            s[i] = (char)(caseDigit ^ s[i]);
        }
        return String.valueOf(s);
    }

    /**
     *
     * Sorting array of strings (or words) using Trie
     *
     * input: "a","bbc","aa","aac","cbc","bcb"
     * output: a, aa, aac, bbc, bcb, cbc,
     *
     * source: https://www.geeksforgeeks.org/sorting-array-strings-words-using-trie/
     *
     * @param str
     */
    private static void sortByTrie(String[] str) {
        Trie trie = new Trie();
        int len = str.length;
        for (int i = 0; i < len; i++) {
            String strElement = str[i];
            int strLen = strElement.length();
            trie.sort(strElement, strLen, i);
        }

       trie.printOrderString(str);
    }

    static class Trie {
        int size = 26*2;
        int index = -1;
        int count = 0;
        Trie[] child = null;

        public void sort(String element, int len, int loc) {
            Trie next = this;
            for (int i = 0; i < len; i++) {
                if (next.child == null) {
                    next.child = new Trie[size];
                }
                int index = getIndex(element.charAt(i));
                //next.child[index].index++;
                if (next.child[index] == null) {
                    next.child[index] = new Trie();
                }
                next = next.child[index];
            }
            next.index = loc;
            next.count++;
        }

        public void printOrderString(String[] str) {
                if (index != -1) {
                    for (int i = 0; i < count; i++) {
                        System.out.print(str[index]+", ");
                    }
                }
                if (child != null) {
                    for (int i = 0; i < size; i++) {
                        if (child[i] != null) {
                            child[i].printOrderString(str);
                        }
                    }
                }

        }

        private int getIndex(char c) {
            if (c >= 'a') {
                return 26 + c - 'a';
            } else {
                return c - 'A';
            }
        }
    }

    /***
     * left rotation
     *
     * input: bababaa
     * output: babaaba
     *
     * source: https://www.geeksforgeeks.org/left-rotation-right-rotation-string-2/
     *
     * @param str
     * @param steps
     * @return
     */
    private static String leftRotation(String str, int steps) {
        String result = str;
        result = reverse(result, steps, str.length()-1);
        result = reverse(result, 0, steps-1);
        result = reverse(result, 0, str.length()-1);
        return result;
    }

    /**
     * right rotation
     *
     * input: bababaa
     * output: aababab
     *
     * source: https://www.geeksforgeeks.org/left-rotation-right-rotation-string-2/
     *
     * @param str
     * @param steps
     * @return
     */
    private static String rightRotation(String str, int steps) {
        String result = str;
        result = leftRotation(result, str.length() - steps);
        return result;
    }

    /**
     * reverse string
     *
     * input: bababaa
     * output: aababab
     *
     * source: https://www.geeksforgeeks.org/program-reverse-string-iterative-recursive/
     *
     * @param str
     * @return
     */
    private static String reverse(String str, int startIndex, int endIndex) {
        int len = endIndex - startIndex + 1;
        char[] strchar = str.toCharArray();
        for (int i = startIndex; i < len/2 + startIndex; i++) {
            char temp = strchar[i];
            strchar[i] = strchar[len-i-1 + 2*startIndex];
            strchar[len-i-1 + 2*startIndex] = temp;
        }
        return String.valueOf(strchar);
    }

    /**
     * input: aaabb
     * output: 12
     *
     * O(n)
     *
     * source:  https://www.geeksforgeeks.org/count-substrings-character-occurring-k-times/
     *
     * @param str
     * @param maxTimes
     * @return
     */
    private static int countSubString(String str, int maxTimes) {
        int leftIndex = 0,rightIndex = 0;
        int len = str.length();
        int count[] = new int[256];
        int sumSub = 0;
        for (; rightIndex < len; rightIndex++) {
            int times = ++count[str.charAt(rightIndex)];
            while (times > maxTimes) {
                --count[str.charAt(leftIndex++)];
            }
            sumSub += (rightIndex - leftIndex + 1);
        }
        return sumSub;
    }

    /**
     *  count substrings character with occurring k times
     *  print the all substring
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
