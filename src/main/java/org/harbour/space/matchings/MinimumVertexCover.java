package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumVertexCover {

    static List<Integer> vertexesB = new ArrayList<>();

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
            for (int j = 1; j < edges.length; j++) {
                adjacencyListA[i].add(edges[j] - 1);
            }
        }
        int[] maxMatching = Arrays.stream(br.readLine().split(" ")).mapToInt(value -> Integer.parseInt(value) - 1).toArray();
        int[] pr = new int[m];
        for (int i = 0; i < n; i++) {
            if (maxMatching[i] != -1) {
                pr[maxMatching[i]] = i;
            }
        }
        boolean[] visitedA = new boolean[n];
        boolean[] visitedB = new boolean[m];
        for (int i = 0; i < n; i++) {
            if (maxMatching[i] == -1) {
                dfs(i, visitedA, visitedB, adjacencyListA, maxMatching, 0, pr);
            }
        }
        List<Integer> vertexesA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visitedA[i]) {
                vertexesA.add(i);
            }
        }
        System.out.println(vertexesA.size() + vertexesB.size());
        System.out.print(vertexesA.size() + " ");
        vertexesA.forEach(vertex -> System.out.print((vertex + 1) + " "));
        System.out.println();
        System.out.print(vertexesB.size() + " ");
        vertexesB.stream().sorted().toList().forEach(vertex -> System.out.print((vertex + 1) + " "));
    }

    static void dfs(int s, boolean[] visitedA, boolean[] visitedB, List<Integer>[] adjacencyListA, int[] maxMatching, int graphPart, int[] pr) {
        if (graphPart == 0) {
            if (visitedA[s]) {
                return;
            }
            visitedA[s] = true;
            List<Integer> neighbours = adjacencyListA[s];
            for (Integer neighbour : neighbours) {
                if (neighbour != maxMatching[s]) {
                    dfs(neighbour, visitedA, visitedB, adjacencyListA, maxMatching, (graphPart + 1) % 2, pr);
                }
            }
        } else {
            if (visitedB[s]) {
                return;
            }
            visitedB[s] = true;
            vertexesB.add(s);
            dfs(pr[s], visitedA, visitedB, adjacencyListA, maxMatching, (graphPart + 1) % 2, pr);
        }
    }
}
