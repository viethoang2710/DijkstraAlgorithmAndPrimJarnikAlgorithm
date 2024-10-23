package org.example;

import java.util.*;

public class DijkstraAlgorithm {
    public void dijkstra(int[][] graph, int source) {
        int V = graph.length; // Number of vertices
        int[] dist = new int[V]; // The output array, dist[i] will hold the shortest distance from source to i
        boolean[] visited = new boolean[V]; // visited[i] will be true if vertex i is included in the shortest path tree

        // Initialize all distances as INFINITE and visited[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        // Distance of source vertex from itself is always 0
        dist[source] = 0;

        // Process all vertices
        for (int i = 0; i < V - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            // Update dist[] value of adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the constructed distance array
        printSolution(dist);
    }

    // A utility function to find the vertex with minimum distance value, from the set of vertices not yet processed
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // A utility function to print the constructed distance array
    private void printSolution(int[] dist) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 20, 0, 0, 0},
                {10, 0, 5, 16, 0, 0},
                {20, 5, 0, 20, 1, 0},
                {0, 16, 20, 0, 2, 1},
                {0, 0, 1, 2, 0, 7},
                {0, 0, 0, 1, 7, 0}
        };
        DijkstraAlgorithm t = new DijkstraAlgorithm();
        t.dijkstra(graph, 0); // Find the shortest path starting from vertex 0
    }
}
