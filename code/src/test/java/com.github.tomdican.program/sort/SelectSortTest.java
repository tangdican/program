package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SelectSortTest {
    @Test
    public void ifIsSorted() {
        int[] input = {1,2,9,4,5,6};

        SelectSort.selectSort(input);

        int[] expected = {1,2,4,5,6,9};

        assertArrayEquals(input,expected);
    }
}
