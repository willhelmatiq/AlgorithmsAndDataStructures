package org.harbour.space.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Breadth_FirstSearch {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int v1 = Integer.parseInt(params[1]) - 1;
        int v2 = Integer.parseInt(params[2]) - 1;

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            String[] adjacencyMatrixRow = br.readLine().split(" ");
            for (int j = 0; j < adjacencyMatrixRow.length; j++) {
                if (Integer.parseInt(adjacencyMatrixRow[j]) == 1) {
                    list.add(j);
                }
            }
            adjacencyList.add(list);
        }

        int[] parents = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
            d[i] = Integer.MAX_VALUE;
        }

        BFS(adjacencyList, parents, d, v1);
        if (parents[v2] == -1) {
            System.out.println(parents[v2]);
        } else {
            int[] result = new int[d[v2] + 1];
            int k = d[v2];
            for (int i = v2; i != parents[i]; i = parents[i]) {
                result[k] = i + 1;
                k--;
            }
            result[k] = v1 + 1;
            System.out.println(d[v2]);
            Arrays.stream(result).forEach( value -> System.out.print(value + " "));
        }
    }

    static void BFS(List<List<Integer>> adjacencyList, int[] parents, int[] d, int s) {
        parents[s] = s;
        d[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            List<Integer> neighbours = adjacencyList.get(u);
            for (int i = 0; i < neighbours.size(); i++) {
                if (parents[neighbours.get(i)] == -1) {
                    d[neighbours.get(i)] = d[u] + 1;
                    parents[neighbours.get(i)] = u;
                    queue.add(neighbours.get(i));
                }
            }
        }
    }
}
