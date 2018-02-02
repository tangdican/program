package com.github.tomdican.program.interview;

import com.github.tomdican.program.Util;

public class VersionCompare {
    public static  void main(String[] args){
        String str1 = "1.3.11";
        String str2 = "1.03.11";

        int result = versionCompares(str1,str2);

        Util.print(String.valueOf(result));
    }

    // compare version1 with version2
    // such as: "1.31.2" > "1.31.0.2"
    // return
    // 1: v2 > v1
    // -1: v2 < v1
    // 0: v2 = v1
    public static int versionCompare(String v1, String v2) {

        char[] v1s = v1.toCharArray();
        char[] v2s = v2.toCharArray();

        int i = 0;
        int result = 0;
        int v1slen = v1s.length;
        int v2slen = v2s.length;
        int minLen = Math.min(v1slen,v2slen);

        // compare the corresponding char of the version
        while (i < minLen){
            int temp = v2s[i] - v1s[i];
            if(temp > 0) {
                result = 1;
                break;
            } else if(temp < 0) {
                result = -1;
                break;
            } else {
                i++;
                continue;
            }
        }

        // when the chars equal,compare the length of the version
        if (result == 0 && v1slen != v2slen) {
            if (i == v1slen)
                result = 1;
            else if( i == v2slen)
                result = -1;
        }

        return result;
    }

    // the code is from google search
    // compare version1 with version2
    // such as: "1.31.2" > "1.31.0.2"
    // return
    // 1: v2 > v1
    // -1: v2 < v1
    // 0: v2 = v1
    public static int versionCompares(String v1, String v2) {

        char[] v1s = v1.toCharArray();
        char[] v2s = v2.toCharArray();

        int i = 0;
        int result = 0;
        int v1slen = v1s.length;
        int v2slen = v2s.length;
        int maxLen = Math.max(v1slen,v2slen);

        // compare the corresponding char of the version
        while (i < maxLen){

            char temp1 = i < v1slen ? v1s[i] : 0;
            char temp2 = i < v2slen ? v2s[i] : 0;

            result = (temp2 > temp1) ? 1 : ((temp2 < temp1) ? -1 : 0);

            i++;
            if(result != 0)
                break;

        }

        return result;
    }


}
