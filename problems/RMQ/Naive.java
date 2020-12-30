package problems.RMQ;

/** 
 * Naive solution to the Range Minimum Query Problem. 
 * Simply performs a linear scan for each query range to find the minimum.
 * 
 * O(1) Time complexity to initialize the structure
 * O(n) Time complexity for each query
 */
public class Naive implements RMQ {
    int[] arr;

    public Naive(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int query(int lo, int hi) {
        // Check for invalid range
        if(lo < 0 || lo > hi || hi >= arr.length) return Integer.MIN_VALUE;

        int min = arr[lo];
        // Iterate through the range [lo, hi] to find the minimum
        for(int i = lo + 1; i <= hi; i++){
            min = Math.min(min, arr[i]);
        }

        return min;
    }

}
