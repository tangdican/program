package com.github.tomdican.program.trycatch;

public class TryCatch {

    public static void main(String[] args) {
       int x = testFinally();
       System.out.println(x);
    }

    private static int testFinally() {
        int x = 1;
        try {
            x = 2;
          //  x = 1/0;
            return x;
        } catch (Exception e) {
            e.printStackTrace();
            x = 3;
            return x;
        } finally {
            x = 4;
           // x = 1/0;
            System.out.println("finally: " +  x);
          //  return x;
        }
    }

}
