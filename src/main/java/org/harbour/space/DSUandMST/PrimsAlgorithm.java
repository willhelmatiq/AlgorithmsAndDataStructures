package org.harbour.space.DSUandMST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimsAlgorithm {

    static int maxValue = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int[][] adjacencyList = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjacencyList[i], -1);
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            adjacencyList[Integer.parseInt(edge[0]) - 1][Integer.parseInt(edge[1]) - 1] = Integer.parseInt(edge[2]);
            adjacencyList[Integer.parseInt(edge[1]) - 1][Integer.parseInt(edge[0]) - 1] = Integer.parseInt(edge[2]);
        }
        buildSpanningTree(adjacencyList, n);
    }

    static void buildSpanningTree(int[][] adjacencyList, int n) {
        int[][] d = new int[n][2];
        for (int i = 0; i < n; i++) {
            d[i][0] = maxValue;
            d[i][1] = -1;
        }
        boolean[] visited = new boolean[adjacencyList.length];
        List<int[]> F = new ArrayList<>();
        List<Integer> S = new ArrayList<>();
        S.add(0);
        visited[0] = true;
        updateD(d, adjacencyList, 0, visited);
        for (int i = 1; i < n; i++) {
            findEdgeWithMinWeight(d, adjacencyList, S, F, visited);
        }
        int totalWeight = 0;
        StringBuilder result = new StringBuilder();
        for (int[] edge : F) {
            totalWeight += adjacencyList[edge[0]][edge[1]];
            result.append((edge[0] + 1)).append(" ").append((edge[1] + 1)).append("\n");
        }
        System.out.println(totalWeight);
        System.out.println(result);
    }

    static void findEdgeWithMinWeight(int[][] d, int[][] adjacencyList, List<Integer> S, List<int[]> F, boolean[] visited) {
        int min = maxValue;
        int vertexTo = -1;
        int vertexFrom = -1;
        for (int i = 0; i < adjacencyList.length; i++) {
            if (min > d[i][0] && !visited[i]) {
                min = d[i][0];
                vertexFrom = d[i][1];
                vertexTo = i;
            }
        }
        S.add(vertexTo);
        F.add(new int[]{vertexFrom, vertexTo});
        updateD(d, adjacencyList, vertexTo, visited);
    }

    static void updateD(int[][] d, int[][] adjacencyList, int index, boolean[] visited) {
        visited[index] = true;
        for (int i = 0; i < adjacencyList.length; i++) {
            if (adjacencyList[index][i] != -1 && !visited[i]) {
                if (d[i][0] > adjacencyList[index][i]) {
                    d[i][0] = adjacencyList[index][i];
                    d[i][1] = index;
                }
            }
        }
    }
}
