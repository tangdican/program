package com.github.tomdican.program;

public class BitOperator {
    public static void main(String [] args){
        int i = -1;
        int x31  = 0x80000000;
        int x311 = 0x7fffffff;

        println("i<<1",Integer.toBinaryString(i<<1));
        println("bit>>",Integer.toBinaryString(x31>>1));
        println("bit>>>",Integer.toBinaryString(i>>>=1));
        println("bit x31",Integer.toString(x31));

        println("bit x311",Integer.toString(x311));
        println("max integer",String.valueOf(Math.pow(2,31)));
       println("max test",String.valueOf(2147483647));
    }

    public static void println(String name , String str){
        System.out.println("-->"+name +" : "+ str);

    }
}
