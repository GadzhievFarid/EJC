package tasks.task_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Создаете коллекцию, ввести с клавиатуры 25 чисел.
 * Выводите коллекцию на экран без сортировки.
 * Делаете Quick Sort и выводите после сортировки.
 * Введите 100 чисел через рандом, 0..1000, Insertion Sort.
 **/
public class QuickAndInsertion {
    public static void main(String[] args) {
        QuickAndInsertion quickAndInsertion = new QuickAndInsertion();
        quickAndInsertion.invokeInsertionSort();
        quickAndInsertion.invokeQuicksort();
    }

    private void invokeInsertionSort() {
        int[] arrayToInsertionSort = new int[100];
        for (int i = 0; i < arrayToInsertionSort.length; i++) {
            arrayToInsertionSort[i] = ThreadLocalRandom.current().nextInt(0, 1000);
        }

        for (int i = 0; i < arrayToInsertionSort.length; i++) {
            System.out.print(arrayToInsertionSort[i] + " ");
        }
        System.out.println();

        insertionSort(arrayToInsertionSort);

        for (int i = 0; i < arrayToInsertionSort.length; i++) {
            System.out.print(arrayToInsertionSort[i] + " ");
        }
        System.out.println();
    }

    private void invokeQuicksort() {
        int[] arrayToQuicksort = new int[25];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < arrayToQuicksort.length; i++) {
                arrayToQuicksort[i] = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < arrayToQuicksort.length; i++) {
            System.out.print(arrayToQuicksort[i] + " ");
        }
        System.out.println();

        quicksort(arrayToQuicksort, 0, arrayToQuicksort.length - 1);

        for (int i = 0; i < arrayToQuicksort.length; i++) {
            System.out.print(arrayToQuicksort[i] + " ");
        }
    }

    void quicksort(int[] arrayToQuicksort, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int index = partition(arrayToQuicksort, begin, end);
        if (begin < index - 1) {
            quicksort(arrayToQuicksort, begin, index - 1);
        }
        if (index + 1 < end) {
            quicksort(arrayToQuicksort, index + 1, end);
        }
    }

    private int partition(int[] arrayToQuicksort, int begin, int end) {
        int leftindex = begin;
        int rightindex = end;
        int pivotindex = arrayToQuicksort[(begin + end) / 2];
        while (leftindex < rightindex) {
            while (arrayToQuicksort[leftindex] < pivotindex) {
                leftindex++;
                if (leftindex == end) {
                    break;
                }
            }
            while (arrayToQuicksort[rightindex] > pivotindex) {
                rightindex--;
                if (rightindex == begin) {
                    break;
                }
            }
            if (leftindex < rightindex) {
                if (arrayToQuicksort[leftindex] == arrayToQuicksort[rightindex]) {
                    leftindex++;
                } else {
                    swap(arrayToQuicksort, leftindex, rightindex);
                }
            }
        }
        return leftindex;
    }

    private void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    void insertionSort(int[] arrayToInsertionSort) {
        for (int i = 0; i < arrayToInsertionSort.length; i++) {
            int currentNumber = arrayToInsertionSort[i];
            int j = i;
            while (j > 0 && currentNumber < arrayToInsertionSort[j - 1]) {
                arrayToInsertionSort[j] = arrayToInsertionSort[j - 1];
                j--;
            }
            arrayToInsertionSort[j] = currentNumber;
        }
    }
}
