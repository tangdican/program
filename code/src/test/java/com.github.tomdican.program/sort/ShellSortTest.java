package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ShellSortTest {
    @Test
    public void ifIsSorted(){
        int[] input = {7,4,1,9,11,3,88,6,3};
        ShellSort.shellSort(input);
        int[] expected = {1,3,3,4,6,7,9,11,88};
        assertArrayEquals(input,expected);
    }
}
