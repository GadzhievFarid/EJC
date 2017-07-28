package tasks.task_08;

import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;

public class BubbleAndMergeTest {

    @Test
    public void testBubbleSort() {
        BubbleAndMerge bubbleAndMerge = new BubbleAndMerge();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        Assert.assertArrayEquals("Something went wrong" + Arrays.toString(bubbleAndMerge.bubbleSort(inputArray)),
                expectedArray, bubbleAndMerge.mergeSort(inputArray));
    }

    @Test
    public void testMergeSort() {
        BubbleAndMerge bubbleAndMerge = new BubbleAndMerge();
        int[] inputArray = {13, 21, 1, 5, 9, 14, 51, 12, 4, 42};
        int[] expectedArray = {1, 4, 5, 9, 12, 13, 14, 21, 42, 51};
        Assert.assertArrayEquals("Something went wrong" + Arrays.toString(bubbleAndMerge.mergeSort(inputArray)),
                expectedArray, bubbleAndMerge.mergeSort(inputArray));
    }

    @Test
    public void testBinarySearch() {
        BubbleAndMerge bubbleAndMerge = new BubbleAndMerge();
        int[] array = {1, 4, 5, 7, 9, 98, 123, 1234, 4322, 4444};
        Assert.assertEquals("Something went wrong", 4,
                bubbleAndMerge.binarySearch(array, 9, 0, array.length - 1));
        Assert.assertEquals("Something went wrong", -1,
                bubbleAndMerge.binarySearch(array, 10, 0, array.length - 1));
    }
}
