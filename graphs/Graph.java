package graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Graph<E> {

    public static class Edge<E> {
        int weight;
        public E dest;

        public Edge(E dest) {
            this.dest = dest;
        }

        public Edge(E dest, int weight) {
            this(dest);
            this.weight = weight;
        }

        public String toString() {
            return dest + "";
        }
    }

    /**
     * Adjacency List as a HashMap
     */
    public HashMap<E, LinkedList<Edge<E>>> adj;

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
    public void addDirectedEdge(E v, E w, int weight) {
        if (!adj.containsKey(v)) {
            adj.put(v, new LinkedList<>());
        }
        if (!adj.containsKey(w)) {
            adj.put(w, new LinkedList<>());
        }
        adj.get(v).add(new Edge<>(w, weight));
    }

    /**
     * Add undirected edge to the graph
     * 
     * @param v vertex v
     * @param u vertex u
     */
    public void addUndirectedEdge(E v, E w, int weight) {
        addDirectedEdge(v, w, weight);
        addDirectedEdge(w, v, weight);
    }

    /**
     * Gets all Neighbors of a given input vertex
     * 
     * @param src vertex
     */
    public List<E> getNeighbors(E src) {
        LinkedList<E> out = new LinkedList<>();
        if (!adj.containsKey(src))
            return out;

        for (Edge<E> e : adj.get(src)) {
            out.add(e.dest);
        }

        return out;
    }

    /**
     * Gets all Edges in the Graph as a List
     * 
     */
    public List<Edge<E>> getEdges() {
        List<Edge<E>> out = new LinkedList<>();
        for (Map.Entry<E, LinkedList<Edge<E>>> entry : adj.entrySet()) {
            for (Edge<E> e : entry.getValue())
                out.add(e);
        }
        return out;
    }

    public String toString() {
        return adj.toString();
    }
}
