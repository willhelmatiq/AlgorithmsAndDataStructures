package org.harbour.space.BasicsOfAlgorithm.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class F_IsItATree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        if (m != n - 1) {
            System.out.println("NO");
            return;
        }
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
        int connectedComponents = dfs(adjacencyList, 0, visited);
        System.out.println(connectedComponents == n ? "YES" : "NO");
    }

    static int dfs(ArrayList<Integer>[] adjacencyList, int u, boolean[] visited) {
        if (visited[u]) {
            return 0;
        }
        int vertexCount = 1;
        visited[u] = true;
        for (int v: adjacencyList[u]){
            if (!visited[v]) {
                vertexCount += dfs(adjacencyList, v, visited);
            }
        }
        return vertexCount;
    }
}
