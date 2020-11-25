package graphs.traversal;

import java.util.HashSet;
import java.util.Stack;

import graphs.Graph;

public class DepthFirstSearch {

    public Graph<Integer> g;

    public DepthFirstSearch(int numVertices) {
        g = new Graph<>(numVertices);
    }

    public void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            visited.add(curr);
            System.out.print(curr + " ");

            for (int neighbor : g.adj.get(curr)) {
                if (visited.contains(neighbor))
                    continue;
                stack.push(neighbor);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        DepthFirstSearch dfs = new DepthFirstSearch(6);
        dfs.g.addUndirectedEdge(5, 2);
        dfs.g.addUndirectedEdge(5, 0);
        dfs.g.addUndirectedEdge(4, 0);
        dfs.g.addUndirectedEdge(4, 1);
        dfs.g.addUndirectedEdge(2, 3);
        dfs.g.addUndirectedEdge(3, 1);

        System.out.println("Following is a DFS " + "of the given graph");
        dfs.dfs(5);
    }
}
