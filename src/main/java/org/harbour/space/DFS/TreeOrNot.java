package org.harbour.space.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TreeOrNot {
    public static boolean foundCycle = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
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
        DFSTreeCheckLoop(0, -1, visited, adjacencyList);
        boolean connectCheck = connectCheckAfterDFS(visited);
        if (!foundCycle && connectCheck) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    static void DFSTreeCheckLoop(int u, int p, boolean[] visited, List<List<Integer>> adjacencyList) {
        visited[u] = true;
        List<Integer> neighbours = adjacencyList.get(u);
        for (int i = 0; i < neighbours.size(); i++) {
            if (!visited[neighbours.get(i)]) {
                DFSTreeCheckLoop(neighbours.get(i), u, visited, adjacencyList);
            } else {
                if (neighbours.get(i) != p ) {
                    foundCycle = true;
                }
            }
        }
    }

    static boolean connectCheckAfterDFS(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
