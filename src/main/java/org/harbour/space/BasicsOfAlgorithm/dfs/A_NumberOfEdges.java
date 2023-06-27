package org.harbour.space.BasicsOfAlgorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A_NumberOfEdges {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            adjacencyMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(countEdges(adjacencyMatrix));
    }

    static int countEdges(int[][] adjacencyMatrix) {
        int edgeCount = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edgeCount++;
                }
            }
        }
        return edgeCount;
    }
}
