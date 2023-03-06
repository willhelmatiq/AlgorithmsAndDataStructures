package org.harbour.space.segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RMQ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = (int) Math.round(Math.sqrt(n));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] T = new int[(arr.length) * 4];
        build(arr, T, 0, 0, arr.length - 1);

        int queriesNumber = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < queriesNumber; i++) {
            String[] query = br.readLine().split(" ");
            if (Integer.parseInt(query[0]) == 1) {
                change(T, 0, 0, n - 1, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]));
            } else {
                result.append(getMin(T, 0, 0, n - 1, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]) - 1)).append("\n");
            }
        }
        System.out.println(result);
    }

    static void build(int[] arr, int[] T, int v, int l, int r) {
        if (l == r) {
            T[v] = arr[l];
        } else {
            int m = (l + r) / 2;
            build(arr, T, 2 * v + 1, l, m);
            build(arr, T, 2 * v + 2, m + 1, r);
            T[v] = Math.min(T[2 * v + 1], T[2 * v + 2]);

        }
    }

    static void change(int[] T, int v, int l, int r, int index, int t) {
        if (l == r) {
            T[v] = t;
        } else {
            int m = (l + r) / 2;
            if (index <= m) {
                change(T, 2 * v + 1, l, m, index, t);
            } else {
                change(T, 2 * v + 2, m + 1, r, index, t);
            }
            T[v] = Math.min(T[2 * v + 1], T[2 * v + 2]);
        }
    }

    static int getMin(int[] T, int v, int l, int r, int L, int R) {
        if (R < l || L > r) {
            return Integer.MAX_VALUE;
        } else if (l >= L && r <= R) {
            return T[v];
        }
        int m = (l + r) / 2;
        return Math.min(getMin(T, 2 * v + 1, l, m, L, R), getMin(T, 2 * v + 2, m + 1, r, L, R));
    }
}
