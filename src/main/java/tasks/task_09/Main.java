package tasks.task_09;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] array = new int[15];
        int[] array2 = new int[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 1000);
            array2[i] = ThreadLocalRandom.current().nextInt(0, 1000);
        }
        System.out.println("Radix sort: ");
        System.out.println(Arrays.toString(array));
        main.radixSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Selection sort: ");
        System.out.println(Arrays.toString(array2));
        main.selectionSort(array2);
        System.out.println(Arrays.toString(array2));

    }

    int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    void countSort(int[] array, int exp) {
        int[] count = new int[10];
        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            count[(array[i] / exp) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            result[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = result[i];
        }
    }

    void radixSort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(array, exp);
        }
    }

    void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
