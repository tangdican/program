package com.github.tomdican.program.bit;

public class BitOperator {
    public static void main(String [] args){
        int x31  = 0x80000000;
        int x311 = 0x7fffffff;
        int x = 0;

        println("-1",Integer.toBinaryString(-1));
        println("-2",Integer.toBinaryString(-2));

        println("0<<1",Integer.toBinaryString(0<<1));
        println("-1<<1",Integer.toBinaryString(-1<<1));

        println("0>>1",Integer.toBinaryString(0>>1));
        println("1>>1",Integer.toBinaryString(1>>1));
        println("-1>>1",Integer.toBinaryString(-1>>1));
        println("-2>>1",Integer.toBinaryString(-2>>1));


        println("2>>>1",Integer.toBinaryString(x= (2>>>1)));
        println("1>>>1",Integer.toBinaryString(x= (1>>>1)));
        println("0>>>1",Integer.toBinaryString(x= (0>>>1)));
        println("-1>>>1",Integer.toBinaryString(x= ((-1)>>>1)));
        println("-2>>>1",Integer.toBinaryString(x= ((-2)>>>1)));
        println("-3>>>1",Integer.toBinaryString(((-3)>>>1)));
        println(Integer.toBinaryString((-1)>>>0)+"",Integer.toString((-1)>>>0));
        println(Integer.toBinaryString((-1)>>>1)+"",Integer.toString((-1)>>>1));
        println(Integer.toBinaryString(((-1)>>>1) + 1)+"",Integer.toString(((-1)>>>1) + 1));
        println(Integer.toBinaryString((-1)>>>2)+"",Integer.toString((-1)>>>2));



        println("bit>>",Integer.toBinaryString(x31>>1));
        println("bit x31",Integer.toString(x31));

        println("bit x311",Integer.toString(x311));
        println("bit x311",Integer.toBinaryString(x311));
        println("max integer",String.valueOf(Math.pow(2,31)));
       println("max test",String.valueOf(2147483647));
       println("a^b^b is a:",String.valueOf((char)('a'^'b'^'b')));

   //    testMaxIntToNegative();
    }

    /**
     * output:
     *
     *   2147483647
         -2147483648
     *
     */
    private static void testMaxIntToNegative() {
        int i = 0;
        while (true) {
            if (i < 0) {
                System.out.println(i - 1);
                System.out.println(i);
                break;
            }
            i++;
        }

    }

    public static void println(String name , String str){
        System.out.println("-->"+name +" : "+ str);
    }


}
