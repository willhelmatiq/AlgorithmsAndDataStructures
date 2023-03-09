package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumEdgeCover {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<Integer>[] adjacencyListA = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyListA[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < edges.length - 1; j++) {
                adjacencyListA[i].add(edges[j] - 1);
            }
        }
        int[] M = new int[m];
        Arrays.fill(M, -1);
        boolean[] used = new boolean[n];
        maxMatching(M, adjacencyListA, used);
        boolean[] visitedA = new boolean[n];
        boolean[] visitedB = new boolean[m];
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (M[i] != -1) {
                visitedA[M[i]] = true;
                visitedB[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            List<Integer> neighbours = adjacencyListA[i];
            for (int neighbour : neighbours) {
                if (!visitedA[i] || !visitedB[neighbour]){
                    edges.add(new int[]{i, neighbour});
                    visitedA[i] = true;
                    visitedB[neighbour] = true;
                }
            }
        }
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (M[i] != -1) {
                counter++;
                result.append(M[i] + 1).append(" ").append((i + 1)).append("\n");
            }
        }
        if (!edges.isEmpty()) {
           counter += edges.size();
           edges.forEach(edge -> result.append((edge[0] + 1)).append(" ").append((edge[1] + 1)).append("\n"));
        }
        System.out.println(counter);
        System.out.println(result);
    }

    static void maxMatching(int[] M, List<Integer>[] adjacencyListA, boolean[] used) {
        for (int i = 0; i < adjacencyListA.length; i++) {
            Arrays.fill(used, false);
            tryKuhn(i, M, used, adjacencyListA);
        }
    }

    static boolean tryKuhn(int a, int[] M, boolean[] used, List<Integer>[] adjacencyList) {
        if (used[a]) {
            return false;
        }
        used[a] = true;
        List<Integer> neighbourOfA = adjacencyList[a];
        for (int neighbour : neighbourOfA) {
            if (M[neighbour] == -1 || tryKuhn(M[neighbour], M, used, adjacencyList)) {
                M[neighbour] = a;
                return true;
            }
        }
        return false;
    }
}
