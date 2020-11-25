package graphs.TopologicalSort;

import java.util.*;
import java.util.Map.Entry;

import graphs.Graph;

public class KahnAlgorithm {

    public Graph<Integer> g;

    public KahnAlgorithm() {
        g = new Graph<>();
    }

    // A recursive function used by topologicalSort
    public void topologicalSortUtil(int v, HashSet<Integer> visited, Stack<Integer> stack) {
        visited.add(v);
        for (int n : g.adj.get(v)) {
            if (visited.contains(n))
                continue;
            topologicalSortUtil(n, visited, stack);
        }
        stack.add(v);
    }

    // The function to do Topological Sort.
    // It uses recursive topologicalSortUtil()
    public void topologicalSort() {
        HashMap<Integer, Integer> inDegrees = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int numTotalNodes = 0;

        for (Entry<Integer, LinkedList<Integer>> entry : g.adj.entrySet()) {
            LinkedList<Integer> vList = entry.getValue();
            int currentVertex = entry.getKey();

            inDegrees.put(currentVertex, inDegrees.getOrDefault(currentVertex, 0));
            for (int v : vList) {
                inDegrees.put(v, inDegrees.getOrDefault(v, 0) + 1);
                numTotalNodes++;
            }
        }

        for (int v : inDegrees.keySet()) {
            if (inDegrees.get(v) > 0)
                continue;
            queue.add(v);
        }

        int visitedNodes = 0;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            System.out.print(v + " ");
            for (int u : g.adj.get(v)) {
                int inDeg = inDegrees.get(u) - 1;
                if (inDeg == 0)
                    queue.add(u);
                inDegrees.put(u, inDeg);
            }
            visitedNodes++;
        }

        if (visitedNodes != numTotalNodes) {
            System.out.println("Graph is not a DAG");
        }
    }

    // Driver code
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        KahnAlgorithm ka = new KahnAlgorithm();
        ka.g.addDirectedEdge(5, 2);
        ka.g.addDirectedEdge(5, 0);
        ka.g.addDirectedEdge(4, 0);
        ka.g.addDirectedEdge(4, 1);
        ka.g.addDirectedEdge(2, 3);
        ka.g.addDirectedEdge(3, 1);

        System.out.println(ka.g);

        System.out.println("Following is a Topological " + "sort of the given graph");
        // Function Call
        ka.topologicalSort();
    }

}