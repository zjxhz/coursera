package stanford.algorithms.week2.pickers;

/**
 * Created by huanze on 7/21/2016.
 */
public class LastPivotPicker implements PivotPicker {
    @Override
    public int pick(int[] arr, int start, int end) {
        return end - 1;
    }
}
