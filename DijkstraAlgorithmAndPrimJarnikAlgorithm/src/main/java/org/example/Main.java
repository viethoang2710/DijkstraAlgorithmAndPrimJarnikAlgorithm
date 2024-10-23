package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {

    // Dijkstra's Algorithm Implementation
    static class DijkstraAlgorithm {
        public void dijkstra(int[][] graph, int source) {
            int V = graph.length; // Number of vertices
            int[] dist = new int[V]; // The output array
            boolean[] visited = new boolean[V]; // Track visited vertices

            Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances
            Arrays.fill(visited, false);
            dist[source] = 0; // Distance of source from itself is always 0

            for (int i = 0; i < V - 1; i++) {
                int u = minDistance(dist, visited); // Pick the minimum distance vertex
                visited[u] = true;

                // Update distance values of adjacent vertices
                for (int v = 0; v < V; v++) {
                    if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                            dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }

            printSolution(dist); // Print the constructed distance array
        }

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

        private void printSolution(int[] dist) {
            System.out.println("Vertex \t\t Distance from Source");
            for (int i = 0; i < dist.length; i++) {
                System.out.println(i + " \t\t " + dist[i]);
            }
        }
    }

    // Prim's Algorithm Implementation
    static class PrimJarnikAlgorithm {
        private static final int V = 6;

        int minKey(int[] key, boolean[] mstSet) {
            int min = Integer.MAX_VALUE, minIndex = -1;

            for (int v = 0; v < V; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        void primMST(int[][] graph) {
            int[] parent = new int[V];
            int[] key = new int[V];
            boolean[] mstSet = new boolean[V];

            Arrays.fill(key, Integer.MAX_VALUE);
            key[0] = 0; // First node is always the root of the MST
            parent[0] = -1; // Root node has no parent

            for (int count = 0; count < V - 1; count++) {
                int u = minKey(key, mstSet);
                mstSet[u] = true; // Add the picked vertex to the MST

                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                        parent[v] = u; // Update parent
                        key[v] = graph[u][v]; // Update key
                    }
                }
            }

            printMST(parent, graph); // Print the MST
        }

        void printMST(int[] parent, int[][] graph) {
            System.out.println("Edge \tWeight");
            for (int i = 1; i < V; i++) {
                System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            }
        }
    }

    public static void main(String[] args) {
        // Dijkstra's Algorithm Example
        System.out.println("Dijkstra's Algorithm:");
        int[][] graphDijkstra = {
                {0, 10, 20, 0, 0, 0},
                {10, 0, 5, 16, 0, 0},
                {20, 5, 0, 20, 1, 0},
                {0, 16, 20, 0, 2, 1},
                {0, 0, 1, 2, 0, 7},
                {0, 0, 0, 1, 7, 0}
        };
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        dijkstra.dijkstra(graphDijkstra, 0); // Starting from vertex 0

        // Prim-Jarnik Algorithm Example
        System.out.println("\nPrim-Jarnik Algorithm:");
        PrimJarnikAlgorithm prim = new PrimJarnikAlgorithm();
        int[][] graphPrim = {
                {0, 2, 0, 6, 0, 0},
                {2, 0, 3, 8, 5, 0},
                {0, 3, 0, 0, 7, 0},
                {6, 8, 0, 0, 9, 0},
                {0, 5, 7, 9, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };
        prim.primMST(graphPrim);
    }
}
