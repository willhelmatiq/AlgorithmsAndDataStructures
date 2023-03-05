package org.harbour.space.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeDiameter {

    static int maxDepth;
    static int maxDepthLeaf;

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
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = n - 1; i > 0; i--) {
            adjacencyList[parents[i]].add(i);
            adjacencyList[i].add(parents[i]);
        }
        boolean[] visited = new boolean[n];
        dfs(0, 0, visited, adjacencyList);
        Arrays.fill(visited, false);
        dfs(maxDepthLeaf, 0, visited, adjacencyList);
        System.out.println(maxDepth);
    }

    static void dfs(int s, int depth, boolean[] visited, List<Integer>[] adjacencyList) {
        visited[s] = true;
        if (depth > maxDepth) {
            maxDepth = depth;
            maxDepthLeaf = s;
        }
        List<Integer> neighbours = adjacencyList[s];
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {
                dfs(neighbour, depth + 1, visited, adjacencyList);
            }
        }
    }
}
