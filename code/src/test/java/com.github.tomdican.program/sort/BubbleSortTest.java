package com.github.tomdican.program.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest {
    @Test
    public void ifIsSorted() {
        int[] input = {2,34,4,2,3,4,99,1};

        BubbleSort.bubbleSort(input);

        int[] expected = {1,2,2,3,4,4,34,99};

        assertArrayEquals(input,expected);
    }
}
