package org.harbour.space.BasicsOfAlgorithm.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class E_ConnectedComponentsSizes {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]) - 1;
            int v = Integer.parseInt(edge[1]) - 1;
            adjacencyList[u].add(v);
            adjacencyList[v].add(u);
        }
        ArrayList<Integer>[] connectedComponents = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            if (connectedComponents[i] == null) {
                connectedComponents[i] = new ArrayList<>();
                dfs(adjacencyList, i, visited, connectedComponents[i], connectedComponents);
            }
        }
        for (ArrayList<Integer> connectedComponent : connectedComponents) {
            System.out.print(connectedComponent.size() + " ");
        }

    }

    static void dfs(ArrayList<Integer>[] adjacencyList, int u, boolean[] visited, ArrayList<Integer> connectedComponent, ArrayList<Integer>[] connectedComponents) {
        if (visited[u]) {
            return;
        }
        connectedComponent.add(u);
        connectedComponents[u] = connectedComponent;
        visited[u] = true;
        for (int v: adjacencyList[u]) {
            if (!visited[v]) {
                dfs(adjacencyList, v, visited, connectedComponent, connectedComponents);
            }
        }
    }
}
