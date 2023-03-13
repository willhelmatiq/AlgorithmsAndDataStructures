package org.harbour.space.DSUandMST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KruskalsAlgorithm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int[][] edgeArr = new int[m][3];
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            edgeArr[i][0] = Integer.parseInt(edge[0]) - 1;
            edgeArr[i][1] = Integer.parseInt(edge[1]) - 1;
            edgeArr[i][2] = Integer.parseInt(edge[2]);
        }
        Arrays.sort(edgeArr, (a, b) -> {
            if (a[2] == b[2]) {
                if (a[0] == b[0]){
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[2], b[2]);
        });
        List<int[]> mstEdges = buildMstEdges(edgeArr, n);
        mstEdges.forEach(edge -> System.out.println((edge[0] + 1) + " " + (edge[1] + 1)));
    }
    static List<int[]> buildMstEdges(int[][] edgeArr, int n) {
        int[] p = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++){
            p[i] = i;
            r[i] = 1;
        }

        long totalWeight = 0;
        List<int[]> mstEdges = new ArrayList<>();
        for (int i = 0; i < edgeArr.length; i++) {
            if (find(edgeArr[i][0], p) != find(edgeArr[i][1], p)) {
                totalWeight += edgeArr[i][2];
                mstEdges.add(new int[]{edgeArr[i][0], edgeArr[i][1], edgeArr[i][2]});
                union(edgeArr[i][0], edgeArr[i][1], p, r);
            }
        }
        System.out.println(totalWeight);
        return mstEdges;
    }
    static int find(int a, int[] p) {
        if (p[a] != a) {
            p[a] = find(p[a], p);
        }
        return p[a];
    }

    static void union(int a, int b, int[] p, int[] r) {
        a = find(a, p);
        b = find(b, p);
        if (r[a] > r[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        p[a] = b;
        if (r[a] == r[b]) {
            r[b]++;
        }
    }
}
