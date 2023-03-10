package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MinimumPathCover {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<Integer>[] adjacencyListA = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyListA[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            adjacencyListA[Integer.parseInt(edge[0]) - 1].add(Integer.parseInt(edge[1]) - 1);
        }
        int[] M = new int[n];
        Arrays.fill(M, -1);
        boolean[] used = new boolean[n];
        maxMatching(M, adjacencyListA, used);
        int[] L = new int[n];
        Arrays.fill(L, -1);
        for (int i = 0; i < n; i++) {
            if (M[i] != -1) {
                L[M[i]] = i;
            }
        }
        boolean[] visitedA = new boolean[n];
        int pathCounter = 0;
        StringBuilder result = new StringBuilder();
        List<LinkedList<Integer>> paths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (L[i] != -1 && !visitedA[i]) {
                int k = i;
                ArrayList<Integer> path = new ArrayList<>();
                while (L[k] != -1 && !visitedA[k]) {
                    visitedA[k] = true;
                    if (path.isEmpty()) {
                        path.add(k + 1);
                    }
                    path.add(L[k] + 1);
                    k = L[k];

                }
                visitedA[k] = true;
                updatePath(paths, path);
            }
        }
        for (LinkedList<Integer> path : paths) {
            pathCounter++;
            path.forEach(vertex -> result.append(vertex).append(" "));
            result.append("\n");
        }
        for (int i = 0; i < n; i++) {
            if (!visitedA[i]) {
                pathCounter++;
                result.append((i + 1)).append("\n");
            }
        }
        System.out.println(pathCounter);
        System.out.println(result);
    }

    static void maxMatching(int[] M, List<Integer>[] adjacencyListA, boolean[] used) {
        for (int i = 0; i < adjacencyListA.length; i++) {
            Arrays.fill(used, false);
            tryKuhn(i, M, used, adjacencyListA);
        }
    }

    static boolean tryKuhn(int a, int[] M, boolean[] used, List<Integer>[] adjacencyList) {
        if (used[a]) {
            return false;
        }
        used[a] = true;
        List<Integer> neighbourOfA = adjacencyList[a];
        for (int neighbour : neighbourOfA) {
            if (M[neighbour] == -1 || tryKuhn(M[neighbour], M, used, adjacencyList)) {
                M[neighbour] = a;
                return true;
            }
        }
        return false;
    }

    static void updatePath(List<LinkedList<Integer>> paths, ArrayList<Integer> newPath) {
        boolean updated = false;
        for (LinkedList<Integer> path : paths) {
            if ((int)path.peekFirst() == (int)newPath.get(newPath.size()-1)) {
                for (int i = newPath.size() - 2; i >= 0; i--) {
                    path.addFirst(newPath.get(i));
                }
                updated = true;
                break;
            } else if ((int)path.peekLast() == (int)newPath.get(0)) {
                for (int i = 1; i < newPath.size(); i++) {
                    path.addLast(newPath.get(i));
                }
                updated = true;
                break;
            }
        }

        if (!updated) {
            LinkedList<Integer> newPathLinkedList = new LinkedList<>(newPath);
            paths.add(newPathLinkedList);
        }
    }
}
