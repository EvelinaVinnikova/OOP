package org.example;

import java.util.Arrays;

/**
 * Класс, реализующий алгоритм пирамидальной сортировки (Heap Sort).
 */
public class HeapSort {

    /**
     * Основной метод, который запускает сортировку массива.
     *
     * @param array массив для сортировки.
     */
    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return; // Нечего сортировать
        }

        int n = array.length;

        // 1. Построение первичной "max-кучи" из массива.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // 2. Один за другим извлекаем элементы из кучи и сортируем.
        for (int i = n - 1; i > 0; i--) {
            // Перемещаем текущий корень (самый большой элемент) в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем heapify на уменьшенной куче, чтобы найти новый максимум.
            heapify(array, i, 0);
        }
    }

    /**
     * Преобразует поддерево с корнем i в "max-кучу".
     *
     * @param array массив.
     * @param heapSize размер кучи.
     * @param rootIndex индекс корневого узла поддерева.
     */
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

    /**
     * Главный метод для демонстрации работы сортировки.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        int[] data = {5, 1, 9, 3, 7, 4, 6, 2, 8};
        System.out.println("Original array: " + Arrays.toString(data));
        HeapSort.sort(data);
        System.out.println("Sorted array: " + Arrays.toString(data));
    }
}