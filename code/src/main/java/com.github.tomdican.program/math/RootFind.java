package com.github.tomdican.program.math;

public class RootFind {
    public static void main(String[] args) {
       sqrt(4);
    }

    public static double sqrt(double c) {

        if (c < 0) {

            return Double.NaN;
        }

        double e = 1e-15;
        double x = c;
        double y = (x + c / x) / 2;
        int i=0;
        while (Math.abs(x - y) > e) {
            x = y;
            y = (x + c / x) / 2;
            i++;
        }
        return x;
    }
}
