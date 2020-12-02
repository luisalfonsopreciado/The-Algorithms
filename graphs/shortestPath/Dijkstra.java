package graphs.shortestPath;

import java.util.HashSet;
import java.util.PriorityQueue;

import graphs.Graph;

public class Dijkstra {

    static class GraphNode {
        int val;
        int dist = Integer.MAX_VALUE;
        GraphNode pred;

        public GraphNode(int val) {
            this.val = val;
            pred = null;
        }

        public String toString(){
            return "" + val;
        }
    }

    public void dijkstra(Graph<GraphNode> graph, GraphNode start, GraphNode finish) {
        PriorityQueue<GraphNode> minHeap = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        HashSet<GraphNode> visited = new HashSet<>();
        start.dist = 0;
        minHeap.add(start);

        while (!minHeap.isEmpty()) {
            GraphNode curr = minHeap.remove();
            visited.add(curr);

            if (curr == finish) {
                System.out.println("Min Distance between " + start + " and " + finish + " is " + curr.dist);
                GraphNode ptr = finish;

                while(ptr != null){
                    System.out.print(ptr + "<-");
                    ptr = ptr.pred;
                }
                return;
            }

            for (GraphNode neighbor : graph.getNeighbors(curr)) {
                if (visited.contains(neighbor))
                    continue;
                neighbor.dist = curr.dist + 1;
                minHeap.add(neighbor);
            }
        }

        System.out.println("No path between " + start + " and " + finish);
    }

    public static void main(String[] args) {
        Graph<GraphNode> g = new Graph<>();
        GraphNode start = new GraphNode(10);
        GraphNode finish = new GraphNode(1);
        g.addUndirectedEdge(start, finish, 1);
        Dijkstra d = new Dijkstra();
        d.dijkstra(g, start, finish);
    }
}
