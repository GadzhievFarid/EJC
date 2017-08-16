package tasks.task_09;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RadixAndSelection {
    public static void main(String[] args) {
        RadixAndSelection radixAndSelection = new RadixAndSelection();
        int[] array = new int[15];
        int[] array2 = new int[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 1000);
            array2[i] = ThreadLocalRandom.current().nextInt(0, 1000);
        }
        System.out.println("Radix sort: ");
        System.out.println(Arrays.toString(array));
        radixAndSelection.radixSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Selection sort: ");
        System.out.println(Arrays.toString(array2));
        radixAndSelection.selectionSort(array2);
        System.out.println(Arrays.toString(array2));
        
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private void countSort(int[] array, int exp) {
        int[] count = new int[10];
        int[] result = new int[array.length];

        for (int element : array) {
            count[(element / exp) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            count[(array[i] / exp) % 10]--;
            result[count[(array[i] / exp) % 10]] = array[i];
        }

        System.arraycopy(result, 0, array, 0, array.length);
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
