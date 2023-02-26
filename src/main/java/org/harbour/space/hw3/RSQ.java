package org.harbour.space.hw3;

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
        int k = (int) Math.round(Math.sqrt(n));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long[] blockSum = createBlockSum(arr, n, k);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] query = br.readLine().split(" ");
            if (Integer.parseInt(query[0]) == 1) {
                assign(arr, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]), blockSum, k);
            } else {
                result.append(getSum(arr, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[2]) - 1, blockSum, k));
            }
        }
        System.out.println(result);
    }

    static long[] createBlockSum(int[] arr, int n, int k) {
        long[] blockSum = new long[(n - ((n / k) * k)) > 0 ? (n / k) + 1 : n / k];
        for (int i = 0; i < blockSum.length; i++) {
            int start = i * k;
            int end = Math.min((i + 1) * k - 1, n - 1);
            blockSum[i] = calcSum(arr, start, end);
        }
        return blockSum;
    }

    static long calcSum(int[] arr, int l, int r) {
        long sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static void assign(int[] arr, int index, int value, long[] blockSum, int k) {
        blockSum[index / k] -= arr[index];
        arr[index] = value;
        blockSum[index / k] += arr[index];
    }

    static StringBuilder getSum(int[] arr, int l, int r, long[] blockSum, int k) {
        long sum = 0;
        for (int i = l; i <= Math.min(r, arr.length-1); ) {
            if (i % k == 0 && i + k <= r + 1) {
                sum += blockSum[i / k];
                i += k;
            } else {
                sum += arr[i];
                i++;
            }
        }
        return new StringBuilder().append(sum).append("\n");
    }
}
