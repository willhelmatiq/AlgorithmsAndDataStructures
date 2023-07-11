package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class G_PathInGraph {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int[] tempArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = i; j < n; j++){
                if (tempArr[j] != 0) {
                    adjacencyList[i].add(j);
                    adjacencyList[j].add(i);
                }
            }
        }
        String[] vertices = br.readLine().split(" ");
        int startVertex = Integer.parseInt(vertices[0]) - 1;
        int targetVertex = Integer.parseInt(vertices[1]) - 1;
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        int[] p = new int[n];
        Arrays.fill(p, -1);
        bfs(adjacencyList, startVertex, d, p);
        System.out.println(d[targetVertex] != Integer.MAX_VALUE ? d[targetVertex] : -1);
    }

    static void bfs(ArrayList<Integer>[] adjacencyList, int s, int[] d, int[] p) {
        p[s] = s;
        d[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int neighbour: adjacencyList[u]) {
                if (p[neighbour] == -1) {
                    queue.add(neighbour);
                    d[neighbour] = d[u] + 1;
                    p[neighbour] = u;
                }
            }
        }
    }
}
