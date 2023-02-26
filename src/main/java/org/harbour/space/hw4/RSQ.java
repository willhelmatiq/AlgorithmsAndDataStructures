package org.harbour.space.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RSQ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] queryParams = br.readLine().split(" ");
        int n = Integer.parseInt(queryParams[0]);
        int m = Integer.parseInt(queryParams[1]);
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long[] T = new long[(arr.length) * 4];
        build(arr, T, 0, 0, arr.length - 1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] query = br.readLine().split(" ");
            if (Integer.parseInt(query[0]) == 1) {
                change(T, 0, 0, n - 1,Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]));
            } else {
                result.append(getSum(T, 0, 0, n - 1, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]) - 1)).append("\n");
            }
        }
        System.out.println(result);
    }

    static void build(int[] arr, long[] T, int v, int l, int r) {
        if (l == r) {
            T[v] = arr[l];
        } else {
            int m = (l + r) / 2;
            build(arr, T, 2 * v + 1, l, m);
            build(arr, T, 2 * v + 2, m + 1, r);
            T[v] = T[2 * v + 1] + T[2 * v + 2];
        }
    }

    static void change(long[] T, int v, int l, int r, int index, int t) {
        if (l <= index && index <= r) {
            if (l == r) {
                T[v] = t;
            } else {
                int m = (l + r) / 2;
                if (index <= m) {
                    change(T, 2 * v + 1, l, m, index, t);
                } else {
                    change(T, 2 * v + 2, m + 1, r, index, t);
                }
                T[v] = T[2 * v + 1] + T[2 * v + 2];
            }
        }
    }

    static long getSum(long[] T, int v, int l, int r, int L, int R) {
        if (R < l || L > r) {
            return 0;
        } else if (l >= L && r <= R) {
            return T[v];
        }
        int m = (l + r) / 2;
        return getSum(T, 2 * v + 1, l, m, L, R) + getSum(T, 2 * v + 2, m + 1, r, L, R);
    }
}
