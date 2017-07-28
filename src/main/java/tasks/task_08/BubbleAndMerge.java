package tasks.task_08;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Bubble sort and Merge sort
 * Binary search
 */
public class BubbleAndMerge {
    public static void main(String[] args) {
        BubbleAndMerge bubbleAndMerge = new BubbleAndMerge();
        bubbleAndMerge.sortAndSearch();
    }

    private void swap(int[] array, int firstIndex, int secondIndex) {
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
            int[] leftArray = new int[array.length / 2];
            int[] rightArray = new int[array.length - leftArray.length];

            System.arraycopy(array, 0, leftArray, 0, leftArray.length);
            System.arraycopy(array, leftArray.length, rightArray, 0, rightArray.length);

            leftArray = mergeSort(leftArray);
            rightArray = mergeSort(rightArray);

            merge(array, leftArray, rightArray);
        }
        return array;
    }

    private void merge(int[] destArray, int[] leftArray, int[] rightArray) {
        int destIndex = 0, leftArrayIndex = 0, rightArrayIndex = 0;
        while (leftArrayIndex != leftArray.length &&
                rightArrayIndex != rightArray.length) {
            if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
                destArray[destIndex] = leftArray[leftArrayIndex];
                destIndex++;
                leftArrayIndex++;
            } else {
                destArray[destIndex] = rightArray[rightArrayIndex];
                destIndex++;
                rightArrayIndex++;
            }
        }

        for (int i = leftArrayIndex; i < leftArray.length; i++) {
            destArray[destIndex] = leftArray[i];
            destIndex++;
        }
        for (int i = rightArrayIndex; i < rightArray.length; i++) {
            destArray[destIndex] = rightArray[i];
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

    private void sortAndSearch() {
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
