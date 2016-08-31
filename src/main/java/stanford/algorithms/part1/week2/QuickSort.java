package stanford.algorithms.part1.week2;


import stanford.algorithms.part1.week2.pickers.FirstPivotPicker;
import stanford.algorithms.part1.week2.pickers.LastPivotPicker;
import stanford.algorithms.part1.week2.pickers.MedianPivotPicker;
import stanford.algorithms.part1.week2.pickers.PivotPicker;

public class QuickSort {
    private int comparisons;
    private PivotPicker pivotPicker;

    public QuickSort(PivotPicker pivotPicker) {
        this.pivotPicker = pivotPicker;
    }

    public int sort(int[] arr) {
        comparisons = 0;
        sort(arr, 0, arr.length);
        System.out.println("Comparisons: " + comparisons);
        return comparisons;
    }

    public static void main(String[] args) {
        new QuickSort(new FirstPivotPicker()).sort(initData());
        new QuickSort(new LastPivotPicker()).sort(initData());
        new QuickSort(new MedianPivotPicker()).sort(initData());
    }

    private static int[] initData() {
        return TestDataFactory.loadData("testdata/stanford/algorithms/part1/week2/quicksort.txt", 10000);
    }


    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        comparisons += (end - start - 1);
        int pivotIndex = pivotPicker.pick(arr, start, end);
        swap(arr, start, pivotIndex);
        int pivot = arr[start];
        int j = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                j++;
                if (i != j) {
                    swap(arr, i, j);
                }
            }
        }
        swap(arr, start, j);
        sort(arr, start, j);
        sort(arr, j + 1, end);
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

