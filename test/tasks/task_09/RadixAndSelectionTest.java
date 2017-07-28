package tasks.task_09;

import org.junit.Assert;
import org.junit.Test;

public class RadixAndSelectionTest {
    @Test
    public void radixSort() {
        RadixAndSelection radixAndSelection = new RadixAndSelection();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        radixAndSelection.radixSort(inputArray);
        Assert.assertArrayEquals("Something went wrong", expectedArray, inputArray);
    }

    @Test
    public void selectionSort() {
        RadixAndSelection radixAndSelection = new RadixAndSelection();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        radixAndSelection.selectionSort(inputArray);
        Assert.assertArrayEquals("Something went wrong", expectedArray, inputArray);
    }

}