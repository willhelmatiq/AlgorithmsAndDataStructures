package org.harbour.space.BasicsOfAlgorithm.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class C_TransitiveClosure {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int[] colors = new int[n];
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

        long transitiveClosureEdgesCount = 0;
        for (int i = 0; i < n; i++) {
            long[] vertexEdgeFromDsf = dfsCountTransitiveClosure(adjacencyList, i, i, colors);
            if (vertexEdgeFromDsf[0] > 2) {
                transitiveClosureEdgesCount += ((vertexEdgeFromDsf[0] * (vertexEdgeFromDsf[0] - 1)) / 2) - vertexEdgeFromDsf[1];
            }
        }
        System.out.println(transitiveClosureEdgesCount);
    }

    static long[] dfsCountTransitiveClosure(ArrayList<Integer>[] adjacencyList, int u, int p, int[] colors) {
        if (colors[u] == 2) {
            return new long[]{0,0};
        }
        if (colors[u] == 1) {
            return new long[]{0,1};
        }
        long[] vertexEdge = new long[2];
        if (colors[u] == 0) {
            colors[u] = 1;
        }
        vertexEdge[0] = 1;
        vertexEdge[1] = 0;
        for (int neighbour: adjacencyList[u]) {
            if (colors[neighbour] != 2) {
                long[] vertexEdgeFromDsf = dfsCountTransitiveClosure(adjacencyList, neighbour, u, colors);
                vertexEdge[0] += vertexEdgeFromDsf[0];
                vertexEdge[1] += vertexEdgeFromDsf[1];
            }
        }
        colors[u] = 2;
        return vertexEdge;
    }
}
