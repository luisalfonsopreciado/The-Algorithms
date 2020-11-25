package ds;

public class UnionFind {

    // id[i] points to the parent of i
    private int[] id;

    // Track the size of each component
    private int[] sz;

    public int numComponents;

    public UnionFind(int size) {
        if (size < 1)
            throw new IllegalArgumentException("Size must be greater than 0");

        id = new int[size];
        sz = new int[size];
        numComponents = size;

        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // Find the root of a given node
    public int find(int k) {
        if (k == id[k])
            return k;
        return find(id[k]);
    }

    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if (root1 == root2)
            return;

        // Merge into largest group
        if (sz[root1] < sz[root2]) {
            id[root1] = root2;
            sz[root2] += sz[root1];
        } else {
            id[root2] = root1;
            sz[root1] += sz[root2];
        }

        numComponents--;
    }
}