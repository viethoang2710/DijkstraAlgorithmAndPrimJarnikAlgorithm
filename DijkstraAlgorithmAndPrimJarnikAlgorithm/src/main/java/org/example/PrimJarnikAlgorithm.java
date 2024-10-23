package org.example;

import java.util.Arrays;

public class PrimJarnikAlgorithm {
    private static final int V = 6;

    int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }

        return minIndex;
    }

    void primMST(int[][] graph) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1; // First node is always the root of the MST

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static void main(String[] args) {
        PrimJarnikAlgorithm t = new PrimJarnikAlgorithm();
        int[][] graph = {
                {0, 2, 0, 6, 0, 0},
                {2, 0, 3, 8, 5, 0},
                {0, 3, 0, 0, 7, 0},
                {6, 8, 0, 0, 9, 0},
                {0, 5, 7, 9, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };

        t.primMST(graph);
    }
}
