package org.harbour.space.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectedComponents {

    static List<Integer> connectedVertex = new ArrayList<>();
    static List<List<Integer>> adjacencyList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int a = Integer.parseInt(edge[0]) - 1;
            int b = Integer.parseInt(edge[1]) - 1;
            adjacencyList.get(a).add(b);
            adjacencyList.get(b).add(a);
        }

        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                DFS(i, i, parents, adjacencyList);
                int connectedComponentSize = connectedVertex.size();
                while (!connectedVertex.isEmpty()) {
                    result[connectedVertex.get(connectedVertex.size() - 1)] = connectedComponentSize;
                    connectedVertex.remove(connectedVertex.size() - 1);
                }
            }
        }

        Arrays.stream(result).forEach(element -> System.out.print(element + " "));
    }

    static void DFS(int u, int p, int[] parents, List<List<Integer>> adjacencyList) {
        parents[u] = p;
        connectedVertex.add(u);
        adjacencyList.get(u).forEach(v -> {
            if (parents[v] == -1) {
                DFS(v, u, parents, adjacencyList);
            }
        });
    }
}
