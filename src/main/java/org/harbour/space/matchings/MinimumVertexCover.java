package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumVertexCover {

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
        int[] pl = Arrays.stream(br.readLine().split(" ")).mapToInt(value -> Integer.parseInt(value) - 1).toArray();
        int[] pr = new int[m];
        Arrays.fill(pr, -1);
        for (int i = 0; i < n; i++) {
            if (pl[i] != -1) {
                pr[pl[i]] = i;
            }
        }
        boolean[] visitedA = new boolean[n];
        boolean[] visitedB = new boolean[m];
        for (int i = 0; i < n; i++) {
            if (pl[i] == -1) {
                tryKuhn(i, visitedA, visitedB, adjacencyListA, pl, pr);
            }
        }
        List<Integer> vertexesA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visitedA[i]) {
                vertexesA.add(i);
            }
        }
        List<Integer> vertexesB = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (visitedB[i]) {
                vertexesB.add(i);
            }
        }
        System.out.println(vertexesA.size() + vertexesB.size());
        System.out.print(vertexesA.size() + " ");
        vertexesA.forEach(vertex -> System.out.print((vertex + 1) + " "));
        System.out.println();
        System.out.print(vertexesB.size() + " ");
        vertexesB.stream().sorted().toList().forEach(vertex -> System.out.print((vertex + 1) + " "));
    }

    static boolean tryKuhn(int s, boolean[] visitedA, boolean[] visitedB, List<Integer>[] adjacencyListA, int[] pl, int[] pr) {
        if (visitedA[s]) {
            return false;
        }
        visitedA[s] = true;
        List<Integer> neighbours = adjacencyListA[s];
        for (Integer neighbour : neighbours) {
            visitedB[neighbour] = true;
            if (pr[neighbour] == -1 || tryKuhn(pr[neighbour], visitedA, visitedB, adjacencyListA, pl, pr)) {
                pr[neighbour] = s;
                pl[s] = neighbour;
                return true;
            }
        }
        return false;
    }
}
