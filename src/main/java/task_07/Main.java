package task_07;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Создаете коллекцию, ввести с клавиатуры 25 чисел.
 * Выводите коллекцию на экран без сортировки.
 * Делаете Quick Sort и выводите после сортировки.
 * Введите 100 чисел через рандом, 0..1000, Insertion Sort.
 **/
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.invokeInsertionSort();
        main.invokeQuicksort();
    }

    void invokeInsertionSort() {
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

    void invokeQuicksort() {
        int[] arrayToQuicksort = new int[25];

       /* try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < arrayToQuicksort.length; i++) {
                arrayToQuicksort[i] = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        for (int i = 0; i < arrayToQuicksort.length; i++) {
            arrayToQuicksort[i] = ThreadLocalRandom.current().nextInt(0, 1000);
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

    int partition(int[] arrayToQuicksort, int begin, int end) {
        int left = begin;
        int right = end;
        int pivot = arrayToQuicksort[(begin + end) / 2];
        while (left < right) {
            while (arrayToQuicksort[left] < pivot) {
                left++;
                if (left == end) {
                    break;
                }
            }
            while (arrayToQuicksort[right] > pivot) {
                right--;
                if (right == begin) {
                    break;
                }
            }
            if (left < right) {
                swap(arrayToQuicksort, left, right);
            }

        }
        return left;
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
