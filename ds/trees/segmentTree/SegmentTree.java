package ds.trees.segmentTree;

/**
 * Segment Tree implementation
 */
public class SegmentTree {
    /**
     * The array that store the tree's nodes (Segment Tree)
     */
    int[] st;

    int[] arr;

    /**
     * Length of the original array
     */
    int n;

    /**
     * Constructs a segment tree from a given array. This constructor allocates
     * memory for a segment tree and calls constructSTUtil to fill the allocated
     * memory.
     * 
     * @param arr array
     * @param n   length of array
     */
    public SegmentTree(int[] arr) {
        n = arr.length;
        this.arr = arr.clone();

        // Height of segment tree is log2(n), below is the log base conversion
        int x = log2(n);

        // Maximum size of the segment tree is 2 * (2^n - 1)
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size];

        constructSTUtil(arr, 0, 0, n - 1);
    }

    /**
     * A recursive function that construct Segment tree for array [ss..se] si is
     * index of current node in segment tree
     * 
     * @param arr
     * @param ss
     * @param se
     * @param si
     * @return
     */
    public int constructSTUtil(int arr[], int si, int lo, int hi) {
        // We have reached a leaf node
        if (lo == hi) {
            st[si] = arr[lo];
            return arr[lo];
        }

        int mid = lo + (hi - lo) / 2;

        st[si] = constructSTUtil(arr, getLeftIndex(si), lo, mid) + constructSTUtil(arr, getRightIndex(si), mid + 1, hi);

        return st[si];
    }

    /**
     * Log Base two
     * 
     * @param n
     * @return
     */
    private int log2(int n) {
        return (int) (Math.ceil(Math.log(n) / Math.log(2)));
    }

    /**
     * Get the right index in the segment tree given an index.
     * 
     * @param index
     * @return
     */
    private int getRightIndex(int index) {
        return (2 * index) + 2;
    }

    /**
     * Get the left index in the segment tree given an index.
     * 
     * @param index
     * @return
     */
    private int getLeftIndex(int index) {
        return (2 * index) + 1;
    }

    public int getSum(int lo, int hi) {
        // Validate Range
        if (hi < lo || lo < 0 || hi >= st.length)
            return -1;
        return getSumUtil(0, lo, hi, 0, n - 1);
    }

    private int getSumUtil(int si, int lo, int hi, int sl, int sh) {
        if (sl == lo && sh == hi)
            return st[si];
        int sum = 0;
        int mid = sl + (sh - sl) / 2;
        // If we require info on the left side
        if (lo <= mid) {
            sum += getSumUtil(getLeftIndex(si), lo, Math.min(mid, hi), sl, mid);
        }
        // If we require info on the right side
        if (hi > mid) {
            sum += getSumUtil(getRightIndex(si), mid + 1, Math.max(mid, hi), mid + 1, sh);
        }
        return sum;
    }

    /**
     * Update a given index of the segment tree
     * 
     * @param index
     * @param val
     */
    public void update(int index, int val) {
        int diff = val - arr[index];
        arr[index] = val;
        updateUtil(0, diff, index, 0, n - 1);
    }

    /**
     * Update Utility Function
     * 
     * @param si
     * @param diff
     * @param index
     * @param sl
     * @param sh
     */
    private void updateUtil(int si, int diff, int index, int sl, int sh) {
        if (index < sl || index > sh || si < 0 || si >= st.length)
            return;
        st[si] += diff;
        int mid = sl + (sh - sl) / 2;
        updateUtil(getLeftIndex(si), diff, index, sl, mid);
        updateUtil(getRightIndex(si), diff, index, mid + 1, sh);
    }

    /**
     * Returns a String representation of the segment tree
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int n : st) {
            str.append(n + ", ");
        }
        str.append("]");
        return str.toString();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 5, 6, 7, 9 };
        SegmentTree tree = new SegmentTree(arr);
        System.out.println(tree);
        System.out.println(tree.getSum(2, 4));
        tree.update(3, 14);
        System.out.println(tree);
    }
}
