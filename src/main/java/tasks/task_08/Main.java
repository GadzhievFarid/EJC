package tasks.task_08;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Bubble sort and Merge sort
 * Binary search
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.sortAndSearch();
    }

    void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                return array;
            }
        }
        return array;
    }

    int[] mergeSort(int[] array) {
        if (array.length > 1) {
            int[] firstArray = new int[array.length / 2];
            int[] secondArray = new int[array.length - firstArray.length];

            for (int i = 0; i < firstArray.length; i++) {
                firstArray[i] = array[i];
            }
            for (int i = 0; i < secondArray.length; i++) {
                secondArray[i] = array[firstArray.length + i];
            }

            firstArray = mergeSort(firstArray);
            secondArray = mergeSort(secondArray);

            merge(array, firstArray, secondArray);
        }
        return array;
    }

    void merge(int[] destArray, int[] firstSourceArray, int[] secondSourceArray) {
        int destIndex = 0, firstSourceIndex = 0, secondSourceIndex = 0;
        while (firstSourceIndex != firstSourceArray.length &&
                secondSourceIndex != secondSourceArray.length) {
            if (firstSourceArray[firstSourceIndex] < secondSourceArray[secondSourceIndex]) {
                destArray[destIndex] = firstSourceArray[firstSourceIndex];
                destIndex++;
                firstSourceIndex++;
            } else {
                destArray[destIndex] = secondSourceArray[secondSourceIndex];
                destIndex++;
                secondSourceIndex++;
            }
        }

        for (int i = firstSourceIndex; i < firstSourceArray.length; i++) {
            destArray[destIndex] = firstSourceArray[i];
            destIndex++;
        }
        for (int i = secondSourceIndex; i < secondSourceArray.length; i++) {
            destArray[destIndex] = secondSourceArray[i];
            destIndex++;
        }
    }

    int binarySearch(int[] array, int key, int left, int right) {
        if (right >= left) {
            int middle = left + (right - left) / 2;

            if (key == array[middle]) {
                return middle;
            } else if (key > array[middle]) {
                return binarySearch(array, key, middle + 1, right);
            } else {
                return binarySearch(array, key, left, middle - 1);
            }
        }
        return -1;
    }

    void sortAndSearch() {
        int[] array = new int[100];
        int[] array1 = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 500);
            array1[i] = ThreadLocalRandom.current().nextInt(0, 500);
        }
        System.out.println("Bubble sort: ");
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Merge sort: ");
        System.out.println(Arrays.toString(array1));
        mergeSort(array1);
        System.out.println(Arrays.toString(array1));

        System.out.println("BinarySearch: ");
        int randomKey = ThreadLocalRandom.current().nextInt(0, 500);
        System.out.print("Random key: ");
        System.out.println(randomKey);
        System.out.print("Index of random key in first array: ");
        System.out.println(binarySearch(array, randomKey, 0, array.length - 1));
        System.out.print("Index of random key in second array: ");
        System.out.println(binarySearch(array1, randomKey, 0, array1.length - 1));
    }
}
