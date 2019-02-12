package com.github.tomdican.program.search;

public class JumpSearch  {
    public static void main(String[] args) {
        int arr[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21,
                34, 55, 89, 144, 233, 377, 610};
        int x = 55;
        int index = jumpSearch(arr, x);
        System.out.println(arr[index]);
    }

    private static int jumpSearch(int[] arr, int x) {
        int index = -1;
        int step = (int)Math.floor(Math.sqrt(arr.length));

        int i = 0;
        while (i < arr.length) {
            if (x > arr[i]) {
                i += step;
            } else {
                break;
            }
        }

        int loc = i;
        while ((i - --loc) < step) {
            if (arr[loc] == x) {
                index = loc;
                break;
            }
        }

        return index;
    }
}
