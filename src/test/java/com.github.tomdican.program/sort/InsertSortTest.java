package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertSortTest {
    @Test
    public void ifIsSorted() {
        int[] input = {1,3,6,7,1,4};

        InsertSort.insertSort(input);

        int[] expected = {1,1,3,4,6,7};

        assertArrayEquals(input,expected);

    }
}
