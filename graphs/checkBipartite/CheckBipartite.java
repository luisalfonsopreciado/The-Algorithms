package graphs.checkBipartite;

import java.util.Queue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedList;
import graphs.Graph;

public class CheckBipartite {

    /**
     * Check if a graph is bipartite, only works if the input graph is connected.
     * 
     * @param g
     * @return
     */
    public static boolean isBipartiteConnected(Graph<Integer> g) {
        int colors[] = new int[g.getNumVertices()];
        Arrays.fill(colors, -1);

        Queue<Integer> queue = new LinkedList<>();

        // Insert the first node in graph to queue
        queue.add(0);
        colors[0] = 1;

        while (!queue.isEmpty()) {
            Integer curr = queue.remove();

            for (int neighbor : g.getNeighbors(curr)) {
                if (colors[neighbor] == -1) {
                    colors[neighbor] = 1 - colors[curr];
                    queue.add(neighbor);
                } else if (colors[curr] == colors[neighbor]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Check if an input graph is bipartite, works for connected and non-connected
     * graphs.
     * 
     * @param g Graph Object
     * @return
     */
    public static boolean isBipartite(Graph<Integer> g) {
        Set<Integer> vertices = g.adj.keySet();
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> colors = new HashMap<>();

        for (int v : vertices) {
            // Skip the already colored vertices
            if (colors.containsKey(v))
                continue;

            queue.add(v);
            // Color the starting node as 0
            colors.put(v, 0);

            while (!queue.isEmpty()) {
                int curr = queue.remove();
                int currColor = colors.get(curr);

                for (int adj : g.getNeighbors(curr)) {
                    int adjColor = colors.getOrDefault(adj, 1 - currColor);
                    // If we cannot color the graph, return false
                    if (adjColor != 1 - currColor)
                        return false;
                    colors.put(adj, adjColor);
                    queue.add(adj);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(0, 4, 1);
        g.addUndirectedEdge(4, 1, 1);
        g.addUndirectedEdge(4, 3, 1);
        g.addUndirectedEdge(3, 2, 1);
        g.addUndirectedEdge(3, 1, 1);
        g.addUndirectedEdge(2, 1, 1);

        System.out.println(g);

        System.out.println(isBipartiteConnected(g)); // false
        System.out.println(isBipartite(g)); // false
    }
}
