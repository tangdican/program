package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HeapSortTest {
    @Test
    public void heapSortTest(){
        int[] input = {4,2,1,6,2,8,3};

        HeapSort.heapSort(input);

        int[] expected = {1,2,2,3,4,6,8};

        assertArrayEquals(input,expected);
    }
}
