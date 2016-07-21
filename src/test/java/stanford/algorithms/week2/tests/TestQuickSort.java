package stanford.algorithms.week2.tests;

import org.junit.Test;
import stanford.algorithms.week2.QuickSort;
import stanford.algorithms.week2.TestDataFactory;
import stanford.algorithms.week2.pickers.FirstPivotPicker;
import stanford.algorithms.week2.pickers.LastPivotPicker;
import stanford.algorithms.week2.pickers.MedianPivotPicker;
import stanford.algorithms.week2.pickers.PivotPicker;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 7/21/2016.
 */
public class TestQuickSort {
    private QuickSort firstSorter = new QuickSort(new FirstPivotPicker());
    private QuickSort lastSorter = new QuickSort(new LastPivotPicker());
    private QuickSort medianSorter = new QuickSort(new MedianPivotPicker());


    @Test
    public void test1(){
        int [] original = TestDataFactory.loadData("testdata/week2/10.txt", 10);
        int [] data = new int[original.length];
        System.arraycopy(original, 0, data, 0, original.length);
        assertEquals(25, firstSorter.sort(data));
        System.arraycopy(original, 0, data, 0, original.length);
        assertEquals(29, lastSorter.sort(data));
        System.arraycopy(original, 0, data, 0, original.length);
        assertEquals(21, medianSorter.sort(data));

    }
}
