package graphs.TopologicalSort;

import java.util.ArrayList;

public class Graph {
    // Adjacency List as ArrayList of ArrayList's
    public ArrayList<ArrayList<Integer>> adj;

    // Constructor
    Graph(int v) {
        adj = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj.get(v).add(w);
    }
}
