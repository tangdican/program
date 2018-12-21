package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RadixSortTest {
    @Test
    public void ifIsSorted() {
        int[] input = {21,1111,334,8,43,67,245,245,234,345};

        RadixSort.radixSort(input);

        int[] expected = {8,21,43,67,234,245,245,334,345,1111};
        assertArrayEquals(input,expected);
    }
}
