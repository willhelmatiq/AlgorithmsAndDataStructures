package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximalByInclusionMatching {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int[] M = new int[m];
        Arrays.fill(M, -1);
        boolean[] used = new boolean[n];
        List<Integer>[] adjacencyList = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < edges.length - 1; j++) {
                adjacencyList[i].add(edges[j] - 1);
            }
        }

        maxByInclusion(M, adjacencyList, used);
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (M[i] != -1) {
                counter++;
                result.append(M[i] + 1).append(" ").append((i + 1)).append("\n");
            }
        }
        System.out.println(counter);
        System.out.println(result);
    }

    static void maxByInclusion(int[] M, List<Integer>[] adjacencyList, boolean[] used) {
        for (int i = 0; i < adjacencyList.length; i++) {
            dfs(i, M, used, adjacencyList);
        }
    }
    static void dfs(int a, int[] M, boolean[] used, List<Integer>[] adjacencyList) {
        if (used[a]) {
            return;
        }
        used[a] = true;
        List<Integer> neighbourOfA = adjacencyList[a];
        for (int neighbour: neighbourOfA) {
            if (M[neighbour] == -1) {
                M[neighbour] = a;
                return;
            }
        }
    }
}
