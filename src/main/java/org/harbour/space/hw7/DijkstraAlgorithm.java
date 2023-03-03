package org.harbour.space.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DijkstraAlgorithm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int start = Integer.parseInt(params[2]) - 1;
        int end = Integer.parseInt(params[3]) - 1;
        Map<int[], Integer> edgeMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] edgeParams = br.readLine().split(" ");
            int[] edge = new int[2];
            edge[0] = Integer.parseInt(edgeParams[0]) - 1;
            edge[1] = Integer.parseInt(edgeParams[1]) - 1;
            int[] edgeReverse = new int[2];
            edgeReverse[0] = Integer.parseInt(edgeParams[1]) - 1;
            edgeReverse[1] = Integer.parseInt(edgeParams[0]) - 1;
            int weight = Integer.parseInt(edgeParams[2]);
            edgeMap.put(edge, weight);
            edgeMap.put(edgeReverse, weight);
        }

        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        int[] p = new int[n];
        Arrays.fill(p, -1);
        boolean[] visited = new boolean[n];
        dijkstra(start, d, p, visited, edgeMap);
        if (d[end] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(d[end]);
        }
    }

    static void dijkstra(int s, int[] d, int[] p, boolean[] visited, Map<int[], Integer> edgeMap) {
        d[s] = 0;
        p[s] = s;
        visited[s] = false;
        while (true) {
            int u = findU(visited, d);
            if (u == -1) {
                break;
            }
            visited[u] = true;
            for (Map.Entry<int[], Integer> entry : edgeMap.entrySet()) {
                if (entry.getKey()[0] == u){
                    int v = entry.getKey()[1];
                    int w = entry.getValue();
                    if (d[v] > d[u] + w) {
                        d[v] = d[u] + w;
                        p[v] = u;
                    }
                }
            }
        }

    }

    static int findU(boolean[] visited, int[] d) {
        int minDOfU = Integer.MAX_VALUE;
        int u = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && d[i] < minDOfU) {
                minDOfU = d[i];
                u = i;
            }
        }
        return u;
    }
}
