package graphs.shortestPath;

import java.util.HashSet;
import java.util.PriorityQueue;

import graphs.Graph;

/**
 * class that implements Dijkstra's shortest path algorithm, implemented for
 * educational purposes.
 */
public class Dijkstra {

    static class GraphNode<E> {
        E val;
        int dist = Integer.MAX_VALUE;
        GraphNode<E> pred;

        public GraphNode(E val) {
            this.val = val;
            pred = null;
        }

        public String toString() {
            return "" + val;
        }
    }

    public void dijkstra(Graph<GraphNode<Character>> graph, GraphNode<Character> start, GraphNode<Character> finish) {
        PriorityQueue<GraphNode<Character>> minHeap = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        HashSet<GraphNode<Character>> visited = new HashSet<>();
        start.dist = 0;
        minHeap.add(start);

        while (!minHeap.isEmpty()) {
            GraphNode<Character> curr = minHeap.remove();
            visited.add(curr);

            if (curr == finish) {
                System.out.println("Min Distance between " + start + " and " + finish + " is " + curr.dist);
                GraphNode<Character> ptr = finish;

                while (ptr != null) {
                    System.out.print(ptr + "<-");
                    ptr = ptr.pred;
                }
                return;
            }

            for (GraphNode<Character> neighbor : graph.getNeighbors(curr)) {
                if (visited.contains(neighbor))
                    continue;
                neighbor.dist = Math.min(neighbor.dist, curr.dist + graph.getEdge(curr, neighbor).weight);
                minHeap.add(neighbor);
            }
        }

        System.out.println("No path between " + start + " and " + finish);
    }

    public static void main(String[] args) {
        Graph<GraphNode<Character>> graph = new Graph<>();
        GraphNode<Character> a = new GraphNode<>('a');
        GraphNode<Character> b = new GraphNode<>('b');
        GraphNode<Character> c = new GraphNode<>('c');
        GraphNode<Character> d = new GraphNode<>('d');
        GraphNode<Character> e = new GraphNode<>('e');
        GraphNode<Character> f = new GraphNode<>('f');
        GraphNode<Character> g = new GraphNode<>('g');
        GraphNode<Character> z = new GraphNode<>('z');
        graph.addUndirectedEdge(a, b, 4);
        graph.addUndirectedEdge(a, c, 3);
        graph.addUndirectedEdge(b, d, 5);
        graph.addUndirectedEdge(b, c, 2);
        graph.addUndirectedEdge(c, e, 6);
        graph.addUndirectedEdge(c, d, 3);
        graph.addUndirectedEdge(d, e, 1);
        graph.addUndirectedEdge(d, f, 5);
        graph.addUndirectedEdge(e, g, 5);
        graph.addUndirectedEdge(g, f, 2);
        graph.addUndirectedEdge(g, z, 4);
        graph.addUndirectedEdge(f, z, 7);

        Dijkstra dijk = new Dijkstra();
        dijk.dijkstra(graph, b, z);

    }
}
