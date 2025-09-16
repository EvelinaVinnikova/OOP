package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {

    @Test
    @DisplayName("Test with a typical unsorted array")
    void testStandardSort() {
        int[] actual = {5, 1, 4, 2, 8};
        int[] expected = {1, 2, 4, 5, 8};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with an already sorted array")
    void testAlreadySorted() {
        int[] actual = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with a reverse-sorted array")
    void testReverseSorted() {
        int[] actual = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with an array containing duplicate elements")
    void testWithDuplicates() {
        int[] actual = {5, 8, 5, 2, 2, 8};
        int[] expected = {2, 2, 5, 5, 8, 8};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with an empty array")
    void testEmptyArray() {
        int[] actual = {};
        int[] expected = {};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with a single-element array")
    void testSingleElement() {
        int[] actual = {42};
        int[] expected = {42};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with negative numbers")
    void testWithNegativeNumbers() {
        int[] actual = {-5, 1, -4, 0, 8};
        int[] expected = {-5, -4, 0, 1, 8};
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with a large random array")
    void testLargeRandomArray() {
        int size = 5000;
        int[] actual = new int[size];
        int[] expected = new int[size];

        Random random = new Random(12345L);

        for (int i = 0; i < size; i++) {
            int randomNumber = random.nextInt();
            actual[i] = randomNumber;
            expected[i] = randomNumber;
        }

        HeapSort.sort(actual);
        Arrays.sort(expected);

        assertArrayEquals(expected, actual);
    }
}