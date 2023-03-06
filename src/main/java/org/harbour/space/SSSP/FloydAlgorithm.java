package org.harbour.space.SSSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FloydAlgorithm {
    static int maxValue = 100000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<int[], Integer> edgeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] matrixRow = br.readLine().split(" ");
            for (int j = 0; j < matrixRow.length; j++) {
                if (Integer.parseInt(matrixRow[j]) != 0) {
                    int[] edge = new int[2];
                    edge[0] = i;
                    edge[1] = j;
                    edgeMap.put(edge, Integer.parseInt(matrixRow[j]));
                }

            }
        }
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], maxValue);
        }
        floydWarshallAlgorithm(edgeMap, d, n);
        Arrays.stream(d).forEach(row -> {
            Arrays.stream(row).forEach(value -> {
                if (value == maxValue) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(value + " ");
                }
            });
            System.out.println();
        });
    }

    static void floydWarshallAlgorithm(Map<int[], Integer> edgeMap, int[][] d, int n) {
        for (int i = 0; i < n; i++) {
            d[i][i] = 0;
        }
        for (Map.Entry<int[], Integer> entry : edgeMap.entrySet()) {
            int u = entry.getKey()[0];
            int v = entry.getKey()[1];
            int w = entry.getValue();
            d[u][v] = Math.min(d[u][v], w);
        }

        for (int t = 0; t < n; t++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++){
                    d[u][v] = Math.min(d[u][v], d[u][t] + d[t][v]);
                }
            }
        }
    }
}
