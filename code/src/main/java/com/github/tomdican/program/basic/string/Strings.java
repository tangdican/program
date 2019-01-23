package com.github.tomdican.program.basic.string;

public class Strings {
    public static void main(String[] args) {
        String a = "11";
        String b = "11";
        String c = new String("11");
        String d = String.valueOf(c);
        String e = String.valueOf("11");
        System.out.println((a==b) +"," + (a==c) +"," + (a==d)+"," + (a==e));
    }
}
