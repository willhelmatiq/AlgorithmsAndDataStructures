package org.harbour.space.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Depth_FirstSearch {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int a = Integer.parseInt(params[1]) - 1;
        int b = Integer.parseInt(params[2]) - 1;
        boolean[] visited = new boolean[n];
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
        List<Integer> resultList = new ArrayList<>();
        DFS(a, b, visited, adjacencyList, resultList);

        if (resultList.get(resultList.size() - 1) != b + 1) {
            System.out.println(-1);
        } else {
            System.out.println(resultList.size() - 1);
            resultList.forEach(u -> System.out.print(u + " "));
        }

    }

    static void DFS(int u, int end, boolean[] visited, List<List<Integer>> adjacencyList, List<Integer> result) {
        result.add(u + 1);
        visited[u] = true;
        if (u == end) {
            return;
        }
        adjacencyList.get(u).forEach(v -> {
            if (!visited[v]) {
                DFS(v, end, visited, adjacencyList, result);
                if (result.get(result.size() - 1) != end + 1) {
                    result.remove(result.size() - 1);
                }
            }
        });
    }
}
