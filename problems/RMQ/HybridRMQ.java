package problems.RMQ;

/**
 * Hybrid Block Based Solution to Range Minimum Query Problem.
 * 
 * O(n) Time complexity to initialize the structure
 * O(log(n)) Time complexity to query any given range
 */
public class HybridRMQ implements RMQ {

    /** Block Size */
    int b;
    int[] summary;
    int[] arr;

    public HybridRMQ(int[] arr) {
        b = log2(arr.length);
        this.arr = arr;

        if(b == 0) return; 

        summary = new int[(arr.length / b) + arr.length % b];
        
        int idx = 0;
        for (int i = 0; i < arr.length; i += b) {
            int min = arr[i];
            for (int j = i + 1; j < i + b && j < arr.length; j++) {
                min = Math.min(min, arr[j]);
            }
            summary[idx++] = min;
        }
    }

    @Override
    public int query(int lo, int hi) {
        if (lo < 0 || hi >= arr.length || lo > hi)
            return -1;
        if (lo == hi)
            return arr[lo];

        int min = arr[lo];

        for (int i = 0; i < summary.length; i++) {
            int boxLo = i * b;
            int boxHi = boxLo + b - 1;

            if (lo <= boxLo && hi >= boxHi) {
                min = Math.min(min, summary[i]);
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

    private static int log2(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

}