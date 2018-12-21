package com.github.tomdican.program.matrix;

public class Matrix {

    public static void main(String[] args) {
        int[][] matrix =
            {{5,   1,    2,    3},
            {9,    10,   6,    4},
            {13,   11,   7,    8},
            {14,   15,   16,   12}};

        printMatrix(matrix);
        rotateMatrix(matrix);
        System.out.println("旋转后：");
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(","+matrix[i][j]);
            }
            System.out.println();
        }
    }

    /***
     *
     * inpput:
     *
     *   ,5,1,2,3
         ,9,10,6,4
         ,13,11,7,8
         ,14,15,16,12

         旋转后：
         ,9,5,1,2
         ,13,11,10,3
         ,14,7,6,4
         ,15,16,12,8


        source: https://www.geeksforgeeks.org/rotate-matrix-elements/
     *
     * @param matrix
     */
    private static void rotateMatrix(int[][] matrix) {
        int startRowIndex = 0;
        int endRowIndex = matrix.length-1;
        int startColIndex = 0;
        int endColIndex = matrix[0].length-1;

        while( startColIndex <= endColIndex && startRowIndex <= endRowIndex) {
            int tempVal = 0;
            int lastVal = 0;

            // move top row
            if (startColIndex < endColIndex ) {
                for (int i = startColIndex; i < endColIndex; i++) {
                    tempVal = matrix[startRowIndex][i];
                    matrix[startRowIndex][i] = lastVal;
                    lastVal = tempVal;
                }
                if (startRowIndex == endRowIndex) {
                    tempVal = matrix[startRowIndex][endColIndex];
                    matrix[startRowIndex][endColIndex] = lastVal;
                    lastVal = tempVal;
                }
            }

            // move end column
            if (startRowIndex < endRowIndex ) {
                for (int i = startRowIndex; i < endRowIndex; i++) {
                    tempVal = matrix[i][endColIndex];
                    matrix[i][endColIndex] = lastVal;
                    lastVal = tempVal;
                }

                if (startColIndex == endColIndex ) {
                    tempVal = matrix[endRowIndex][endColIndex];
                    matrix[endRowIndex][endColIndex] = lastVal;
                    lastVal = tempVal;
                }
            }

            // move bottom row
            if (startColIndex < endColIndex && startRowIndex < endRowIndex) {
                for (int i = endColIndex; i > startColIndex; i--) {
                    tempVal = matrix[endRowIndex][i];
                    matrix[endRowIndex][i] = lastVal;
                    lastVal = tempVal;
                }
            }

            // move start column
            if (startRowIndex < endRowIndex && startColIndex < endColIndex) {
                for (int i = endRowIndex; i > startRowIndex; i--) {
                    tempVal = matrix[i][startColIndex];
                    matrix[i][startColIndex] = lastVal;
                    lastVal = tempVal;
                }
            }

            matrix[startRowIndex][startColIndex] = lastVal;

            startColIndex++;
            startRowIndex++;
            endColIndex--;
            endRowIndex--;
        }
    }
}
