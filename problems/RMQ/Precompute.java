package problems.RMQ;

/**
 * Precompute class is a solution to the Range Minimum Sum Query Problem. The
 * time complexity to initialize the RMQ takes O(n^2) which uses Dynamic
 * Programming to compute the minimum between all of the possible ranges in the
 * input array. On the other hand, to query any given range would take O(1)
 * Time.
 */
public class Precompute implements RMQ {
    /**
     * Matrix that stores all that range sums in the input array
     */
    int[][] mat;

    public Precompute(int[] arr) {
        mat = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            mat[i][i] = arr[i];
        }

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

    public static void main(String[] args) {
        int[] arr = { 16, 18, 33, 98 };
        RMQ rmq = new Precompute(arr);
        System.out.println(rmq.query(1, 3));
    }
}
