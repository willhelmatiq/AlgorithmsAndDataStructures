package org.harbour.space.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclicityChecking {

    static boolean foundCycle = false;
    static int loopStart;
    static int loopEnd;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] testCase = br.readLine().split(" ");
            int n = Integer.parseInt(testCase[0]);
            int m = Integer.parseInt(testCase[1]);
            int[] color = new int[n];
            Arrays.fill(color, -1);
            int[] parents = new int[n];
            Arrays.fill(parents, -1);
            List<Integer>[] adjacencyList = new ArrayList[n];
            for (int j = 0; j < n; j++) {
                adjacencyList[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                String[] edge = br.readLine().split(" ");
                adjacencyList[Integer.parseInt(edge[0]) - 1].add(Integer.parseInt(edge[1]) - 1);
            }
            for (int j = 0; j < n; j++) {
                if (color[j] == -1) {
                    dfsCyclicityChecking(j, j, color, parents, adjacencyList);
                }
            }
            if (!foundCycle) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
                LinkedList<Integer> linkedList = new LinkedList<>();
                while (loopEnd != loopStart) {
                    linkedList.addFirst(loopEnd + 1);
                    loopEnd = parents[loopEnd];
                }
                linkedList.addFirst(loopEnd + 1);
                System.out.println(linkedList.size());
                linkedList.add(loopStart + 1);
                linkedList.forEach(element -> System.out.print(element + " "));
            }
            foundCycle = false;
        }
    }

    static void dfsCyclicityChecking(int u, int p, int[] color, int[] parents, List<Integer>[] adjacencyList) {
        color[u] = 0;
        parents[u] = p;
        List<Integer> neighbours = adjacencyList[u];
        for (int neighbour: neighbours) {
            if (color[neighbour] == -1) {
                dfsCyclicityChecking(neighbour, u, color, parents, adjacencyList);
            } else {
                if (color[neighbour] == 0) {
                    loopStart = neighbour;
                    loopEnd = u;
                    foundCycle = true;
                }
            }
        }
        color[u] = 1;
    }
}
