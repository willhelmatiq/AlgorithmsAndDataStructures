package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class C_Beads {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]) - 1;
            int v = Integer.parseInt(edge[1]) - 1;
            adjacencyList[u].add(v);
            adjacencyList[v].add(u);
        }
        boolean[] visited = new boolean[n];
        int[] firstBfsResult = bfs(adjacencyList, 0, visited);
        Arrays.fill(visited, false);
        int[] secondBfsResult = bfs(adjacencyList, firstBfsResult[0], visited);
        System.out.println(secondBfsResult[1]);

    }

    static int[] bfs(ArrayList<Integer>[] adjacencyList, int start, boolean[] visited) {
        int d = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start, d});
        visited[start] = true;
        int[] lastElement = new int[] {-1, -1};
        while (!queue.isEmpty()) {
            int[] currBead = queue.poll();
            for (int neighbour: adjacencyList[currBead[0]]) {
                if (!visited[neighbour]) {
                    lastElement = new int[]{neighbour, currBead[1] + 1};
                    queue.add(lastElement);
                    visited[neighbour] = true;
                }
            }
        }
        return lastElement;
    }
}
