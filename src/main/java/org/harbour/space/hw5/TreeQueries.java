package org.harbour.space.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TreeQueries {

    static int currentTime;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] parents = new int[n];
        parents[0] = 0;
        String[] parentData = br.readLine().split(" ");
        for (int i = 1; i < n; i++) {
            parents[i] = Integer.parseInt(parentData[i - 1]) - 1;
        }

        List<Integer>[] adjacencyList = new ArrayList[n];
        for (int j = 0; j < n; j++) {
            adjacencyList[j] = new ArrayList<>();
        }
        for (int i = n - 1; i > 0; i--) {
            adjacencyList[parents[i]].add(i);
        }
        int[][] t = new int[n][2];
        boolean[] visited = new boolean[n];
        dfs(0, visited, adjacencyList, t);
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] queryParams = br.readLine().split(" ");
            int a = Integer.parseInt(queryParams[0]) - 1;
            int b = Integer.parseInt(queryParams[1]) - 1;
            processQuery(a, b, t);
        }
    }

    static void dfs(int s, boolean[] visited, List<Integer>[] adjacencyList, int[][] t) {
        visited[s] = true;
        List<Integer> neighbours = adjacencyList[s];
        t[s][0] = currentTime++;
        for (int neighbour: neighbours) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adjacencyList, t);
            }
        }
        t[s][1] = currentTime++;
    }

    static void processQuery(int a, int b, int[][] t) {
        if (t[a][0] < t[b][0] && t[a][1] > t[b] [1]) {
            System.out.println(1);
        } else if (t[a][0] > t[b][0] && t[a][1] < t[b] [1]) {
            System.out.println(2);
        } else {
            System.out.println(3);
        }
    }
}
