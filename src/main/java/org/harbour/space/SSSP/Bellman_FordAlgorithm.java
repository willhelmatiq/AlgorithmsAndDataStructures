package org.harbour.space.SSSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Bellman_FordAlgorithm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        Map<int[], Integer> edgeMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] edgeParams = br.readLine().split(" ");
            int[] edge = new int[2];
            edge[0] = Integer.parseInt(edgeParams[0]) - 1;
            edge[1] = Integer.parseInt(edgeParams[1]) - 1;
            int weight = Integer.parseInt(edgeParams[2]);
            edgeMap.put(edge, weight);
        }

        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = -1;
        }
        BFA(0, d, p, edgeMap);
        for (int i = 1; i < n; i++) {
            if (d[i] < Integer.MAX_VALUE) {
                System.out.println(d[i]);
            } else {
                System.out.println("NO");
            }
        }
    }

    static void BFA(int s, int[] d, int[] p, Map<int[], Integer> edgeMap) {
        d[s] = 0;
        p[s] = s;
        for (int i = 0; i < d.length - 1; i++) {
            for (Map.Entry<int[], Integer> entry : edgeMap.entrySet()) {
                int u = entry.getKey()[0];
                int v = entry.getKey()[1];
                int w = entry.getValue();
                if (d[u] < Integer.MAX_VALUE && d[v] > d[u] + w) {
                    d[v] = d[u] + w;
                    p[v] = u;
                }
            }
        }
    }
}
