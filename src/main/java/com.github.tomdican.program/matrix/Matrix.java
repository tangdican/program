package com.github.tomdican.program.matrix;

public class Matrix {

    public static void main(String[] args) {
        int[][] matrix =
            {{5,   1,    2,    3},
            {9,    10,   6,    4},
            {13,   11,   7,    8},
            {14,   15,   16,   12}};

        rotateMatrix(matrix);
    }

    private static void rotateMatrix(int[][] matrix) {
        int startRowIndex = 0;
        int endRowIndex = matrix.length-1;
        int startColIndex = 0;
        int endColIndex = matrix[0].length-1;

        int tempVal = 0;
        // move top row
        for (int i = startColIndex; i < endColIndex; i++) {
            tempVal = matrix[startRowIndex][i+1];
            matrix[startRowIndex][i+1] = matrix[startRowIndex][i];
        }
        int lastVal = tempVal;

        // move end column
        for (int i = startRowIndex; i < endRowIndex; i++) {
            tempVal = matrix[i+1][endColIndex];
            matrix[i+1][endColIndex] = matrix[i][endColIndex];
        }
        matrix[startRowIndex][endColIndex] = lastVal;
        lastVal = tempVal;

        // move bottom row
        for (int i = endColIndex; i > startColIndex; i--) {
            tempVal = matrix[endRowIndex][i-1];
            matrix[endRowIndex][i-1] = matrix[endRowIndex][i];
        }
        matrix[startRowIndex][endColIndex] = lastVal;
        lastVal = tempVal;

        // move start column
        for (int i = endRowIndex; i > startRowIndex; i--) {
            tempVal = matrix[i+1][endColIndex];
            matrix[i+1][endColIndex] = matrix[i][endColIndex];
        }
        matrix[startRowIndex][endColIndex] = lastVal;
        lastVal = tempVal;
    }


}
