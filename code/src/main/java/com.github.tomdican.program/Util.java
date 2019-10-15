package com.github.tomdican.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Util {
    public static void println(String str){
        System.out.println(str);
    }

    public static void print(Object str){
        System.out.print(str);
    }

    public static void printArray(int[] a){
        printArray(a,0,a.length-1);
    }

    public static void printArray(int[] a,int begin,int end){
        for (int i = begin; i <= end; i++) {
            Util.print(a[i]+",");
        }
        Util.println("");
    }

    public static void printLine(int n){
        while (n > 0){
            Util.print("--->");
            n--;
        }
        Util.println("");
    }

    public static void exchange(int[] a, int i ,int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void exchange(char[] a, int i ,int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void exchange(int i , int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    public static void printArray(char[] a) {
        int begin = 0;
        int end = a.length - 1;
        for (int i = begin; i <= end; i++) {
            Util.print(a[i]+",");
        }
        Util.print(" | ");
    }

    public static void reverse(char[] chars, int start, int end) {
        while ( start < end) {
            exchange(chars, start++, end--);
        }
    }

    public static void reverse(int[] chars, int start, int end) {
        while ( start < end) {
            exchange(chars, start++, end--);
        }
    }

    private static void printArray(String[] names) {
        int begin = 0;
        int end = names.length - 1;
        for (int i = begin; i <= end; i++) {
            Util.print(names[i]+",");
        }
    }
}
