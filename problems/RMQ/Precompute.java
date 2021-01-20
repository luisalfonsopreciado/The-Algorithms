package problems.RMQ;

/**
 * Precompute class is a solution to the Range Minimum Sum Query Problem.
 * 
 * O(n^2) Time complexity to initialize the structure. 
 * O(1) Time complexity for each query
 */
public class Precompute implements RMQ {
    /**
     * Matrix that stores all that range sums in the input array
     */
    int[][] mat;

    public Precompute(int[] arr) {
        // Initialize the matrix that holds the min for all possible ranges
        mat = new int[arr.length][arr.length];

        // When the upper and lower bounds of range are same, minimum is the value in
        // the array
        for (int i = 0; i < arr.length; i++) {
            mat[i][i] = arr[i];
        }

        // Displacement
        int disp = 1;

        while (disp < arr.length) {
            for (int row = 0; (row + disp) < arr.length; row++) {
                mat[row][row + disp] = Math.min(mat[row][row + disp - 1], mat[row + 1][row + disp]);
            }
            disp++;
        }
    }

    public int query(int lo, int hi) {
        if (lo > hi)
            return -1;
        if (lo < 0 || lo >= mat.length)
            return -1;
        if (hi < 0 || hi >= mat.length)
            return -1;
        return mat[lo][hi];
    }
}
