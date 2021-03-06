package tasks.task_07;

import org.junit.Assert;
import org.junit.Test;

public class QuickAndInsertionTest {
    @Test
    public void testQuicksort() {
        QuickAndInsertion quickAndInsertion = new QuickAndInsertion();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        quickAndInsertion.quicksort(inputArray, 0, inputArray.length - 1);
        Assert.assertArrayEquals("Something went wrong", expectedArray, inputArray);
    }

    @Test
    public void testInsertionSort() {
        QuickAndInsertion quickAndInsertion = new QuickAndInsertion();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        quickAndInsertion.insertionSort(inputArray);
        Assert.assertArrayEquals("Something went wrong", expectedArray, inputArray);
    }
}