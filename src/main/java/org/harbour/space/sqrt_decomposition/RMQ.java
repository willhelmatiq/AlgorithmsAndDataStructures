package org.harbour.space.sqrt_decomposition;

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
        int[] blockMin = new int[(n - ((n / k) * k)) > 0 ? (n / k) + 1 : n / k];
        int t = 0;
        for (int i = 0; i < blockMin.length; i++) {
            int start = t * k;
            int end = Math.min((t + 1) * k - 1, n - 1);
            blockMin[t] = findMin(arr, start, end);
            t++;
        }
        int queriesNumber = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < queriesNumber; i++) {
            String[] query = br.readLine().split(" ");
            if (Integer.parseInt(query[0]) == 1) {
                assign(arr, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]), blockMin, k);
            } else {
                result.append(getMin(arr, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]) - 1, blockMin, k));
            }
        }
        System.out.println(result);
    }

    static int findMin(int[] arr, int l, int r) {
        int min = arr[l];
        while (l <= r && l < arr.length) {
            if (arr[l] < min) {
                min = arr[l];
            }
            l++;
        }
        return min;
    }

    static void assign(int[] arr, int index, int value, int[] blockMin, int k) {
        int id = index / k;
        blockMin[id] = Integer.MAX_VALUE;
        arr[index] = value;
        for (int i = id * k; i < (id + 1) * k && i < arr.length; i++) {
            if (arr[i] < blockMin[id]) {
                blockMin[id] = arr[i];
            }
        }
    }

    static StringBuilder getMin(int[] arr, int l, int r, int[] blockMin, int k) {
        int currentMin = arr[l];
        while (l <= r) {
            if (l % k == 0 && l + k <= r + 1) {
                if (currentMin > blockMin[l / k]) {
                    currentMin = blockMin[l / k];
                }
                l += k;
            } else {
                if (l < arr.length && arr[l] < currentMin) {
                    currentMin = arr[l];
                }
                if (currentMin == blockMin[l / k] && l + k <= r + 1) {
                    l = ((l / k) * k + k);
                } else {
                    l++;
                }
            }
        }
        return new StringBuilder().append(currentMin).append("\n");
    }
}
