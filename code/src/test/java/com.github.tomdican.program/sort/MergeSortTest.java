package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {
    @Test
    public void ifIsSorted() {
        int[] input = {2,4,9,4,1,3,2};
        MergeSort.mergeSort(input);
        int[] expected = {1,2,2,3,4,4,9};
        assertArrayEquals(input,expected);
}

}
