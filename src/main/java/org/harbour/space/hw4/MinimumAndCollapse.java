package org.harbour.space.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumAndCollapse {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arrStr = br.readLine().split(" ");
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
           arr[i][0] = Integer.parseInt(arrStr[i]);
           arr[i][1] = i;
        }
        int[][] T = new int[(arr.length) * 4][2];
        build(arr, T, 0, 0, arr.length - 1);
        int[] bTreeArr = new int[n * 4];
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int index = T[0][1];
            changeSumB(bTreeArr, 0, 0, n - 1, index, 1);
            change(T, 0, 0, n - 1, index, Integer.MAX_VALUE);
            result.append(index + 1 - getSumB(bTreeArr, 0, 0 , n - 1, 0, index - 1)).append(" ");
        }
        System.out.println(result);
    }

    static void build(int[][] arr, int[][] T, int v, int l, int r) {
        if (l == r) {
            T[v][0] = arr[l][0];
            T[v][1] = arr[l][1];
        } else {
            int m = (l + r) / 2;
            build(arr, T, 2 * v + 1, l, m);
            build(arr, T, 2 * v + 2, m + 1, r);
            T[v][0] = Math.min(T[2 * v + 1][0], T[2 * v + 2][0]);
            T[v][1] = getIndexMin(T[2 * v + 1], T[2 * v + 2])[1];
        }
    }

    static int[] getIndexMin(int[] first, int[] second) {
        if (first[0] < second[0]) {
            return first;
        }
        return second;
    }
    static void change(int[][] T, int v, int l, int r, int index, int t) {
        if (l == r) {
            T[v][0] = t;
        } else {
            int m = (l + r) / 2;
            if (index <= m) {
                change(T, 2 * v + 1, l, m, index, t);
            } else {
                change(T, 2 * v + 2, m + 1, r, index, t);
            }
            T[v][0] = Math.min(T[2 * v + 1][0], T[2 * v + 2][0]);
            T[v][1] = getIndexMin(T[2 * v + 1], T[2 * v + 2])[1];
        }
    }

    static void changeSumB(int[] T, int v, int l, int r, int index, int t) {
        if (l <= index && index <= r) {
            if (l == r) {
                T[v] = t;
            } else {
                int m = (l + r) / 2;
                if (index <= m) {
                    changeSumB(T, 2 * v + 1, l, m, index, t);
                } else {
                    changeSumB(T, 2 * v + 2, m + 1, r, index, t);
                }
                T[v] = T[2 * v + 1] + T[2 * v + 2];
            }
        }
    }

    static long getSumB(int[] T, int v, int l, int r, int L, int R) {
        if (R < l || L > r) {
            return 0;
        } else if (l >= L && r <= R) {
            return T[v];
        }
        int m = (l + r) / 2;
        return getSumB(T, 2 * v + 1, l, m, L, R) + getSumB(T, 2 * v + 2, m + 1, r, L, R);
    }
}
