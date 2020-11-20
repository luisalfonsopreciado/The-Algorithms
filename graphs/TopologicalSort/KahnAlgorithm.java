package graphs.TopologicalSort;

import java.util.*;

public class KahnAlgorithm {

    public Graph g;

    public KahnAlgorithm() {
        g = new Graph(6);
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
        // Index to keep track of the current vertex
        int i = 0;

        for(List<Integer> vList: g.adj){
            inDegrees.put(i, inDegrees.getOrDefault(i, 0));
            for(int v : vList){
                inDegrees.put(v, inDegrees.getOrDefault(v, 0) + 1);
                numTotalNodes++;
            }
            i++;
        }
        
        for(int v: inDegrees.keySet()){
            if(inDegrees.get(v) > 0) continue;
            queue.add(v);
        }

        int visitedNodes = 0;

        while(!queue.isEmpty()){
            int v = queue.remove();
            System.out.print(v + " ");
            for(int u : g.adj.get(v)){
                int inDeg = inDegrees.get(u) - 1;
                if(inDeg == 0) queue.add(u);
                inDegrees.put(u, inDeg);
            }
            visitedNodes++;
        }

        if(visitedNodes != numTotalNodes) {
            System.out.println("Graph is not a DAG");
        }
    }

    // Driver code
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        KahnAlgorithm ka = new KahnAlgorithm();
        ka.g.addEdge(5, 2);
        ka.g.addEdge(5, 0);
        ka.g.addEdge(4, 0);
        ka.g.addEdge(4, 1);
        ka.g.addEdge(2, 3);
        ka.g.addEdge(3, 1);

        System.out.println("Following is a Topological " + "sort of the given graph");
        // Function Call
        ka.topologicalSort();
    }

}