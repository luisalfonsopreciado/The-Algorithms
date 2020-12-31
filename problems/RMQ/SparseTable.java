package problems.RMQ;

/**
 * Sparse Table solution to the Range Minimum Query Problem.
 * 
 * O(n * log(n)) Time to initialize the structure
 * O(1) queries
 */
public class SparseTable implements RMQ {

    int[][] matrix;
    int[] arr;

    public SparseTable(int[] arr) {
        // Count the number of powers of two that fit in the arr length
        int width = highestPowerof2(arr.length);
        this.arr = arr;
        matrix = new int[arr.length][];

        for (int i = 0; i < arr.length; i++) {
            matrix[i] = new int[getMaxPowTwo(arr.length - i)];
            matrix[i][0] = arr[i];
        }

        for (int col = 1; col < width; col++) {
            for (int row = 0; row < matrix.length; row++) {
                if (col >= matrix[row].length)
                    continue;
                int bottomRow = ((int) Math.pow(2, col) / 2) + row;
                int bottom = matrix[bottomRow][col - 1];
                int left = matrix[row][col - 1];
                matrix[row][col] = Math.min(bottom, left);
            }
        }
    }

    private int getMaxPowTwo(int n) {
        return Integer.toBinaryString(n).length();
    }

    @Override
    public int query(int lo, int hi) {
        if (lo > hi)
            return -1;
        if (lo == hi)
            return arr[lo];
        int pow = highestPowerof2(hi - lo + 1);
        int out = arr[lo], i = lo;

        while (i + pow - 1 <= hi) {
            out = Math.min(out, matrix[i][log2(pow)]);
            i++;
        }

        return out;
    }

    private static int highestPowerof2(int n) {
        int p = (int) log2(n);
        return (int) Math.pow(2, p);
    }

    private static int log2(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }
}
