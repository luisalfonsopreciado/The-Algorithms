package graphs.traversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import graphs.Graph;

public class BreadthFirstSearch {

    Graph<Integer> g;

    public BreadthFirstSearch() {
        g = new Graph<>();
    }

    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Integer curr = queue.remove();
            System.out.print(curr + " ");

            for (int adj : g.getNeighbors(curr)) {
                if (visited.contains(adj))
                    continue;
                visited.add(adj);
                queue.add(adj);
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.g.addUndirectedEdge(5, 2, 1);
        bfs.g.addUndirectedEdge(5, 0, 1);
        bfs.g.addUndirectedEdge(4, 0, 1);
        bfs.g.addUndirectedEdge(4, 1, 1);
        bfs.g.addUndirectedEdge(2, 3, 1);
        bfs.g.addUndirectedEdge(3, 1, 1);

        System.out.println("Following is a BFS " + "of the given graph");
        bfs.bfs(5);
    }
}
