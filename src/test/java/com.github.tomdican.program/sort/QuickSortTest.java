package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void ifIsAlreadySorted() {
        int[] input = {5,3,4,7,1};
        QuickSort.quicksort(input,0,input.length-1);

        int[] expect = {1,3,4,5,7};
        assertArrayEquals(input,expect);
    }
}
