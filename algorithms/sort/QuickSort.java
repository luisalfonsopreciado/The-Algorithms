package algorithms.sort;

/**
 * QuickSort class supports various implementations of the Quick Sort algorithm.
 */
public class QuickSort {

    /**
     * Quick Sort implementation: Sorts elements in ascending order.
     * 
     * @param arr
     */
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Quick Sort recursive Function.
     * 
     * @param arr An Array of Integers
     * @param lo  Lower bound for sort
     * @param hi  Upper bound for sort
     */
    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int pivot = quickSortHelper(arr, lo, hi);
        quickSort(arr, lo, pivot - 1);
        quickSort(arr, pivot + 1, hi);
    }

    /**
     * Quick Sort Helper Function: Picks the hi element as the partition and places
     * greater elements to the right and all elements less than the partition to the
     * left within the given bounds (lo, hi).
     * 
     * @param arr
     * @param lo
     * @param hi
     * @return
     */
    private static int quickSortHelper(int[] arr, int lo, int hi) {
        int idx = lo - 1;
        int pivot = arr[hi];

        for (int i = lo; i < hi; i++) {
            if (arr[i] < pivot) {
                idx++;
                swap(arr, idx, i);
            }
        }

        swap(arr, idx + 1, hi);

        return idx + 1;
    }

    /**
     * Swaps 2 elements within the array, given their index. Must provide valid
     * indices.
     * 
     * @param arr Array of int's
     * @param i   Index of first element to be swapped
     * @param j   Index of second element to be swapped
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 8, 4, 2, 5, 7, 5, 3, 8, 4, -1, 32, 4 };
        sort(arr);
        for (int n : arr)
            System.out.print(n + " ");
    }
}
