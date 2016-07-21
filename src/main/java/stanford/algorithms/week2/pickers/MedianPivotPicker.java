package stanford.algorithms.week2.pickers;

/**
 * Created by huanze on 7/21/2016.
 */
public class MedianPivotPicker implements PivotPicker {
    @Override
    public int pick(int[] arr, int start, int end) {
        int m = (end - 1 + start) / 2;
        int pivotIndex = start;
        if ((arr[m] >= arr[start] && arr[m] <= arr[end - 1])
                || (arr[m] <= arr[start] && arr[m] >= arr[end - 1])) {
            pivotIndex = m;
        } else if ((arr[end - 1] >= arr[m] && arr[end - 1] <= arr[start])
                || (arr[end - 1] <= arr[m] && arr[end - 1] >= arr[start])) {
            pivotIndex = end - 1;
        }
        return pivotIndex;
    }
}
