package graphs.TopologicalSort;

// A Java program to print topological sorting of a DAG 
import java.util.*;
import graphs.Graph;

public class TopologicalSort {

    public Graph<Integer> g;

    public TopologicalSort() {
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
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < g.adj.size(); i++) {
            if (visited.contains(i))
                continue;
            topologicalSortUtil(i, visited, stack);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    // Driver code
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        TopologicalSort ts = new TopologicalSort();
        ts.g.addDirectedEdge(5, 2);
        ts.g.addDirectedEdge(5, 0);
        ts.g.addDirectedEdge(4, 0);
        ts.g.addDirectedEdge(4, 1);
        ts.g.addDirectedEdge(2, 3);
        ts.g.addDirectedEdge(3, 1);

        System.out.println("Following is a Topological " + "sort of the given graph");
        // Function Call
        ts.topologicalSort();
    }
}