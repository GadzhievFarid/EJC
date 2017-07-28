package tasks.task_09;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    @Test
    public void radixSort() {
        Main main = new Main();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        main.radixSort(inputArray);
        Assert.assertArrayEquals("Something went wrong", expectedArray, inputArray);
    }

    @Test
    public void selectionSort() {
        Main main = new Main();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        main.selectionSort(inputArray);
        Assert.assertArrayEquals("Something went wrong", expectedArray, inputArray);
    }

}