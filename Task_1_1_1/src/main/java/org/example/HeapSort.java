package org.example;

public class HeapSort {
    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return; // Нечего сортировать
        }

        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != rootIndex) {
            int swap = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = swap;
            
            heapify(array, heapSize, largest);
        }
    }
    public static void main(String[] args) {
        int[] data = {5, 1, 9, 3, 7, 4, 6, 2, 8};
        System.out.println("Original array: " + java.util.Arrays.toString(data));

        HeapSort.sort(data);

        System.out.println("Sorted array: " + java.util.Arrays.toString(data));
    }
}