package org.harbour.space.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bridges {

    static List<Integer> bridges = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<UtilClass>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            adjacencyList[Integer.parseInt(edge[0]) - 1].add(new UtilClass(Integer.parseInt(edge[1]) - 1, i));
            adjacencyList[Integer.parseInt(edge[1]) - 1].add(new UtilClass(Integer.parseInt(edge[0]) - 1, i));
        }
        boolean[] visited = new boolean[n];
        int[] fup = new int[n];
        int[] dep = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, i, 0, visited, adjacencyList, fup, dep);
            }
        }
        System.out.println(bridges.size());
        if (bridges.size() > 0) {
            bridges.forEach(element -> System.out.print(element + " "));
        }
    }

    static void dfs(int u, int p, int d, boolean[] visited, List<UtilClass>[] adjacencyList, int[] fup, int[] dep) {
        visited[u] = true;
        dep[u] = d;
        fup[u] = d;
        List<UtilClass> neighbours = adjacencyList[u];
        for (UtilClass neighbour : neighbours) {
            if (neighbour.getVertex() == p) {
                continue;
            }
            if (!visited[neighbour.getVertex()]) {
                dfs(neighbour.getVertex(), u, d + 1, visited, adjacencyList, fup, dep);
                if (fup[neighbour.getVertex()] > dep[u]) {
                    bridges.add(neighbour.getIndex() + 1);
                }
                fup[u] = Math.min(fup[u], fup[neighbour.getVertex()]);
            } else {
                fup[u] = Math.min(fup[u], dep[neighbour.getVertex()]);
            }
        }
    }

    static class UtilClass {
        int vertex;
        int index;

        UtilClass(int vertex, int index) {
            this.vertex = vertex;
            this.index = index;
        }

        public int getVertex() {
            return vertex;
        }

        public int getIndex() {
            return index;
        }
    }
}
