package org.harbour.space.BasicsOfAlgorithm.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_PrintAdjacencyLists {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] edgeParams = br.readLine().split(" ");
            int[] edge = new int[]{Integer.parseInt(edgeParams[0]) - 1, Integer.parseInt(edgeParams[1]) - 1};
            edges.add(edge);
        }
        edges.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });
        printAdjacencyList(buildAdjacencyList(edges, n));
    }

    static ArrayList<Integer>[] buildAdjacencyList(List<int[]> edges, int n) {
        ArrayList<Integer>[] adjacencyList = new ArrayList[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
            while (j < edges.size() && edges.get(j)[0] == i) {
                adjacencyList[i].add(edges.get(j)[1]);
                j++;
            }
        }
        return adjacencyList;
    }

    static void printAdjacencyList(ArrayList<Integer>[] adjacencyList) {
        for (ArrayList<Integer> arrayList: adjacencyList) {
            if (arrayList.size() > 0) {
                for (int element: arrayList) {
                    System.out.print(element + 1 + " ");
                }
                System.out.println();
            } else {
                System.out.println();
            }
        }
    }
}
