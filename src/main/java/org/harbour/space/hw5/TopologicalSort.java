package org.harbour.space.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopologicalSort {

    static int currentTime;
    static boolean foundCycle = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<Integer>[] adjacencyList = new ArrayList[n];
        for (int j = 0; j < n; j++) {
            adjacencyList[j] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            adjacencyList[Integer.parseInt(edge[0])].add(Integer.parseInt(edge[1]));
        }
        int[][] t = new int[n][2];
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                dsf(i, color, adjacencyList, t);
            }
        }
        if (foundCycle) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            int[][] result = new int[n][2];
            for (int i = 0; i < n; i++) {
                result[i][0] = i;
                result[i][1] = t[i][1];
            }
            Arrays.sort(result, (b, a) -> Integer.compare(a[1], b[1]));
            Arrays.stream(result).forEach(arr -> System.out.print(arr[0] + " "));
        }
    }

    static void dsf(int s, int[] color, List<Integer>[] adjacencyList, int[][] t) {
        color[s] = 0;
        t[s][0] = currentTime++;
        List<Integer> neighbours = adjacencyList[s];
        for (int neighbour : neighbours) {
            if (color[neighbour] == -1) {
                dsf(neighbour, color, adjacencyList, t);
            } else {
                if (color[neighbour] == 0) {
                    foundCycle = true;
                }
            }
        }
        color[s] = 1;
        t[s][1] = currentTime++;
    }
}
