import java.util.*;

public class task8_routingtable {

    // The total number of vertices in the graph
    private static final int Total__vertices = 5;

    // A method that runs Dijkstra's algorithm on a given graph and source node
    private void run_dijkstra(int[][] graph, int source) {
        // Initialize arrays for distance, parent and visited nodes, and fill them with default values
        int[] distance = new int[Total__vertices];
        int[] parent = new int[Total__vertices];
        boolean[] visited = new boolean[Total__vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);

        // Set the distance of the source node to 0
        distance[source] = 0;

        // Loop over all vertices except the source node
        for (int count = 0; count < Total__vertices - 1; count++) {
            // Find the vertex with the minimum distance that has not been visited yet
            int u = findMinimumDistanceVertex(distance, visited);
            visited[u] = true;

            // Update the distance and parent arrays for all adjacent vertices of the current vertex
            for (int v = 0; v < Total__vertices; v++) {
                if (!visited[v] && graph[u][v] != -1 &&
                        distance[u] != Integer.MAX_VALUE &&
                        distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        // Print the shortest path from the source node to all other nodes
        printpath(distance, parent);
    }

    // A method that finds the vertex with the minimum distance that has not been visited yet
    private int findMinimumDistanceVertex(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;

        for (int v = 0; v < Total__vertices; v++) {
            if (!visited[v] && distance[v] <= minDistance) {
                minDistance = distance[v];
                minDistanceVertex = v;
            }
        }

        return minDistanceVertex;
    }

    // A method that prints the shortest path from the source node to all other nodes
    private void printpath(int[] distance, int[] parent) {
        System.out.println("Shortest path from source node to the other nodes: ");

        // Create a mapping from vertex indices to node labels
        char[] node = { 'A', 'B', 'C', 'D', 'E' };

        // Loop over all vertices and print their shortest path
        for (int v = 0; v < Total__vertices; v++) {
            // Print the shortest distance and path to the current vertex
            System.out.print("To " + node[v] + ": shortest distance = " + distance[v] + ", path = ");
            Stack<Integer> path = new Stack<>();
            int current = v;

            // Reconstruct the path from the source node to the current vertex using the parent array
            while (parent[current] != -1) {
                path.push(current);
                current = parent[current];
            }

            path.push(current);

            // Print the path in reverse order
            while (!path.isEmpty()) {
                System.out.print(node[path.pop()] + " ");
            }

            System.out.println();
        }
    }

    // The main method, which initializes a graph and runs Dijkstra's algorithm on it
    public static void main(String[] args) {
        int[][] graph = { {-1,1,-1,1,1},
                {-1,-1,1,-1,-1},
                {-1,-1,-1,-1,1},
                {-1,-1,1,-1,1},
                {-1,-1,-1,-1,-1} };

        task8_routingtable dijkstra = new task8_routingtable();
        dijkstra.run_dijkstra(graph, 0);
    }
}