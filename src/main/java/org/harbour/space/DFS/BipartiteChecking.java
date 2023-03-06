package org.harbour.space.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BipartiteChecking {

    public static boolean bipartite = true;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            adjacencyList[Integer.parseInt(edge[0]) - 1].add(Integer.parseInt(edge[1]) - 1);
            adjacencyList[Integer.parseInt(edge[1]) - 1].add(Integer.parseInt(edge[0]) - 1);
        }
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                DFSBipartiteChecking(i, 0, colors, adjacencyList);
            }
        }
        if (bipartite) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void DFSBipartiteChecking(int u, int color, int[] colors, List<Integer>[] adjacencyList ) {
        colors[u] = color;
        for (int i: adjacencyList[u]) {
            if (colors[i] == -1) {
                DFSBipartiteChecking(i, (color + 1) % 2, colors, adjacencyList);
            } else {
                if (colors[i] == colors[u]) {
                    bipartite = false;
                }
            }
        }
    }
}