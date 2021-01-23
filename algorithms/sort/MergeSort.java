package algorithms.sort;

/**
 * MergeSort class supports an implementations of the Merge Sort algorithm.
 */
public class MergeSort {

    /**
     * Merge Sort implementation: Sorts elements in ascending order.
     * 
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Merge Sort recursive function.
     * 
     * @param arr An array of integers
     * @param lo  Lower bound for sort
     * @param hi  Upper bound for sort
     */
    public static void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid + 1, hi + 1);
    }

    /**
     * Merge two sorted subarrays contained in arr.
     * 
     * @param arr   an array of integers
     * @param left  start index of the left subarray
     * @param right start index of the right subarray
     * @param hi    index for end of right subarray
     */
    public static void merge(int[] arr, int left, int right, int hi) {
        int[] temp = new int[hi - left];
        int l = left, r = right, idx = 0;

        while (idx < temp.length) {
            if (l == right) {
                temp[idx++] = arr[r++];
                continue;
            }
            if (r == hi) {
                temp[idx++] = arr[l++];
                continue;
            }
            if (arr[l] < arr[r]) {
                temp[idx++] = arr[l++];
            } else {
                temp[idx++] = arr[r++];
            }
        }

        for (int n : temp) {
            arr[left++] = n;
        }
    }
}
