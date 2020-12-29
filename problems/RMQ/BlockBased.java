package problems.RMQ;

/**
 * Use a Block Based Approach to solve the range minimum query problem.
 * 
 * O(n) Time to initialize the RMQ, O(n^1/2) Time to query.
 */
public class BlockBased implements RMQ {

    int[] ranges;
    int[] arr;
    /** Length of range */
    int b;

    public BlockBased(int[] arr) {
        this.arr = arr;

        if (arr.length == 0)
            return;

        b = (int) Math.sqrt(arr.length);
        // The length of ranges is the amount of times b fits
        // into the arr.length plus the remainder
        ranges = new int[(arr.length / b) + arr.length % b];
        int idx = 0;
        for (int i = 0; i < arr.length; i += b) {
            int min = arr[i];
            for (int j = i + 1; j < i + b && j < arr.length; j++) {
                min = Math.min(arr[j], min);
            }
            ranges[idx++] = min;
        }
    }

    @Override
    public int query(int lo, int hi) {
        if (lo < 0 || hi >= arr.length || lo > hi)
            return -1;
        if (lo == hi)
            return arr[lo];

        int min = arr[lo];

        for (int i = 0; i < ranges.length; i++) {
            int boxLo = i * b;
            int boxHi = boxLo + b - 1;

            if (lo <= boxLo && hi >= boxHi) {
                min = Math.min(min, ranges[i]);
            } else if (lo > boxLo) {
                for (int j = lo; j <= boxHi && j <= hi; j++) {
                    min = Math.min(min, arr[j]);
                }
            } else if (hi < boxHi) {
                for (int j = Math.max(lo, boxLo); j <= hi; j++) {
                    min = Math.min(min, arr[j]);
                }
            }
        }

        return min;
    }
}
