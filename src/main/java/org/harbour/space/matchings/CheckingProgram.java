package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckingProgram {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        List<Integer>[] adjacencyListA = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyListA[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < edges.length; j++) {
                adjacencyListA[i].add(edges[j] - 1);
            }
        }
        int[] maxMatchingGiven = Arrays.stream(br.readLine().split(" ")).mapToInt(value -> Integer.parseInt(value) - 1).toArray();
        int[] M = new int[m];
        Arrays.fill(M, -1);
        for (int i = 0; i < n; i++) {
            if (maxMatchingGiven[i] != -1) {
                M[maxMatchingGiven[i]] = i;
            }
        }
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (maxMatchingGiven[i] == -1) {
                tryKuhn(i, M, used, adjacencyListA);
            }
        }
        int[] maxMatchingCompute = new int[n];
        Arrays.fill(maxMatchingCompute, -1);
        for (int i = 0; i < m; i++) {
            if (M[i] != -1) {
                maxMatchingCompute[M[i]] = i;
            }
        }
        if (Arrays.compare(maxMatchingGiven, maxMatchingCompute) == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
            int l = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (maxMatchingCompute[i] != -1) {
                    l++;
                    sb.append(i + 1).append(" ").append(maxMatchingCompute[i] + 1).append("\n");
                }
            }
            System.out.println(l);
            System.out.println(sb);
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
}
