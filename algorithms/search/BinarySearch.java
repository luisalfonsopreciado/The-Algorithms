package algorithms.search;

/**
 * BinarySearch class demonstrates various implementations of binary search in a
 * sorted array
 */
public class BinarySearch {

    // Search for index of target integere in sorted array in increasing orders
    public int binarySearch(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.binarySearch(arr, 3));
    }
}
