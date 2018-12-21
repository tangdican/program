package com.github.tomdican.program.bit;

public class BitOperator {
    public static void main(String [] args){

        println("-1",Integer.toBinaryString(-1)+" ,len: "+ Integer.toBinaryString(-1).length());
        println("-2",Integer.toBinaryString(-2)+" ,len: "+ Integer.toBinaryString(-2).length());
        println("0<<1",Integer.toBinaryString(0<<1)+" ,len: "+ Integer.toBinaryString(0<<1).length());
        println("-1<<1",Integer.toBinaryString(-1<<1)+" ,len: "+ Integer.toBinaryString(-1<<1).length());
        println("0>>1",Integer.toBinaryString(0>>1)+" ,len: "+ Integer.toBinaryString(0>>1).length());
        println("1>>1",Integer.toBinaryString(1>>1)+" ,len: "+ Integer.toBinaryString(1>>1).length());
        println("-1>>1",Integer.toBinaryString(-1>>1)+" ,len: "+ Integer.toBinaryString(-1>>1).length());
        println("-2>>1",Integer.toBinaryString(-2>>1)+" ,len: "+ Integer.toBinaryString(-2>>1).length());
        println("-3>>1",Integer.toBinaryString(-3>>1)+" ,len: "+ Integer.toBinaryString(-3>>1).length());
        println("2>>>1",Integer.toBinaryString((2>>>1))+" ,len: "+ Integer.toBinaryString((2>>>1)).length());
        println("1>>>1",Integer.toBinaryString((1>>>1))+" ,len: "+ Integer.toBinaryString((1>>>1)).length());
        println("0>>>1",Integer.toBinaryString((0>>>1))+" ,len: "+ Integer.toBinaryString((0>>>1)).length());
        println("-1>>>1",Integer.toBinaryString(((-1)>>>1))+" ,len: "+ Integer.toBinaryString(((-1)>>>1)).length());
        println("-2>>>1",Integer.toBinaryString(((-2)>>>1))+" ,len: "+ Integer.toBinaryString(((-2)>>>1)).length());
        println("-3>>>1",Integer.toBinaryString(((-3)>>>1))+" ,len: "+ Integer.toBinaryString(((-3)>>>1)).length());
        println(Integer.toBinaryString((-1)>>>0)+"",Integer.toString((-1)>>>0));
        println(Integer.toBinaryString((-1)>>>1)+"",Integer.toString((-1)>>>1));
        println(Integer.toBinaryString(((-1)>>>1) + 1)+"",Integer.toString(((-1)>>>1) + 1));
        println(Integer.toBinaryString((-1)>>>2)+"",Integer.toString((-1)>>>2));

        int x32  = 0x80000000;
        int x311 = 0x7fffffff;
        String s31 = Integer.toBinaryString(x32);
        String s32 = Integer.toBinaryString(x32>>1);
        String s321 = Integer.toBinaryString(x32>>>1);
        println( "len: " + s31.length() +" bit: "+ s31 + " bit x31",Integer.toString(x32));
        println( "len: " + s32.length() +" bit: "+ s32 + " bit x32>>1","");
        println( "len: " + s321.length() +" bit: "+ s321 + " bit x32>>>1","");

        println("bit x311",Integer.toString(x311));
        println("bit x311",Integer.toBinaryString(x311));
        println("max integer",String.valueOf(Math.pow(2,31)));
       println("max test",String.valueOf(2147483647));
       println("a^b^b is a:",String.valueOf((char)('a'^'b'^'b')));

//       testMaxIntToNegative();
    }

    /**
     * output:
     *
     *   2147483647 bit: 1111111111111111111111111111111 bit length: 31
        -2147483648 bit: 10000000000000000000000000000000 bit length: 32
     *
     */
    private static void testMaxIntToNegative() {
        int i = 0;
        while (true) {
            if (i < 0) {
                String s1 = Integer.toBinaryString(i-1);
                String s2 = Integer.toBinaryString(i);
                System.out.println((i - 1) + " bit: " + s1 + " bit length: " + s1.length());
                System.out.println((i) + " bit: " + s2 +" bit length: " + s2.length());
                break;
            }
            i++;
        }

    }

    public static void println(String name , String str){
        System.out.println("-->"+name +" : "+ str);
    }


}
