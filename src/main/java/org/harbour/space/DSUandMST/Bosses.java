package org.harbour.space.DSUandMST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bosses {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int[] p = new int[n];
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            l[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            processQuery(br.readLine().split(" "), p, l);
        }

    }

    static void processQuery(String[] queryParams, int[] p, int[] l) {
        int a = Integer.parseInt(queryParams[1]) - 1;
        if (Integer.parseInt(queryParams[0]) == 1) {
            int b = Integer.parseInt(queryParams[2]) - 1;
            union(a, b, p, l);
        } else {
            System.out.println(find(a, p, l)[1]);
        }
    }

    static int[] find(int a, int[] p, int[] l) {
        if (a == p[a]) {
            return new int[]{a, 0};
        } else {
            int rep = find(p[a], p, l)[0];
            int lenFromP = find(p[a], p, l)[1];
            p[a] = rep;
            l[a] = lenFromP + l[a];
            return new int[]{rep, l[a]};
        }
    }

    static void union(int a, int b, int[] p, int[] l) {
        p[a] = b;
        l[a] = 1;
    }
}
