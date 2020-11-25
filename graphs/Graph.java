package graphs;

import java.util.LinkedList;
import java.util.HashMap;

public class Graph<E> {

    /**
     * Adjacency List as ArrayList of ArrayList's
     */
    public HashMap<E, LinkedList<E>> adj;

    public Graph() {
        adj = new HashMap<>();
    }

    /**
     * Create a graph data structure
     * 
     * @param v The number of vertices
     */
    public Graph(int size) {
        adj = new HashMap<>();
    }

    /**
     * Add directed edge to the graph
     * 
     * @param v vertex v
     * @param u vertex u
     */
    public void addDirectedEdge(E v, E w) {
        if (!adj.containsKey(v)) {
            adj.put(v, new LinkedList<E>());
        }
        if (!adj.containsKey(w)) {
            adj.put(w, new LinkedList<E>());
        }
        adj.get(v).add(w);
    }

    /**
     * Add undirected edge to the graph
     * 
     * @param v vertex v
     * @param u vertex u
     */
    public void addUndirectedEdge(E v, E w) {
        addDirectedEdge(v, w);
        addDirectedEdge(w, v);
    }

    public String toString() {
        return adj.toString();
    }
}
