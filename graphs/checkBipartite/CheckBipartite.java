package graphs.checkBipartite;

import java.util.Queue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import graphs.Graph;

public class CheckBipartite {

    /**
     * The below algorithm works only if the graph is connected, meaning, there are
     * no isolated nodes in the graph. In the code below, we always start with
     * source 0 and assume that vertices are visited from it.
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

    public static boolean isBipartite(Graph<Integer> g){
        Set<Integer> vertices = g.adj.keySet();
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> colors = new HashMap<>();
        HashMap<Integer, Integer> indices = new HashMap<>();
        
        for(int v : vertices){
            if(colors[v] != -1) continue;

            queue.add(v);
            colors[v] = 0;

            while(!queue.isEmpty()){
                int curr = queue.remove();
                for(int adj : g.getNeighbors(curr)){
                    if(colors[adj] == colors[curr]) return false;
                    colors[adj] = 1 - colors[curr];
                }
            }
            
            idx++;
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
    }
}
