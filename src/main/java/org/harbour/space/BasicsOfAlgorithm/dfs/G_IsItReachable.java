package org.harbour.space.BasicsOfAlgorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class G_IsItReachable {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int a = Integer.parseInt(params[2]) - 1;
        int b = Integer.parseInt(params[3]) - 1;
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
        System.out.println(dfs(adjacencyList, a, b, visited)? "YES" : "NO");


    }

    static boolean dfs(ArrayList<Integer>[] adjacencyList, int u, int target, boolean[] visited) {
        if (visited[u]) {
            return false;
        }
        boolean targetFound = false;
        if (u == target) {
            return true;
        }
        visited[u] = true;
        for (int v: adjacencyList[u]){
            if (!visited[v]) {
                targetFound |= dfs(adjacencyList, v, target, visited);
            }
        }
        return targetFound;
    }
}
