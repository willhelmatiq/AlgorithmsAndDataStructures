package org.harbour.space.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NumberOfInversions {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] minMaxVal = findMinMax(arr);
        int minVal = minMaxVal[0];
        int maxVal = minMaxVal[1];
        int[] bArr = new int[maxVal - minVal + 1];
        int k = (int) Math.round(Math.sqrt(bArr.length));
        long[] blockSum = new long[(bArr.length - ((bArr.length / k) * k)) > 0 ? (bArr.length / k) + 1 : bArr.length / k];
        calcInversions(arr, bArr, blockSum, minVal, maxVal, k);
    }

    static void calcInversions(int[] arr, int[] bArr, long[] blockSum, int minVal, int maxVal, int k) {
        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            assign(bArr, arr[i] - minVal, 1, blockSum, k);
            result += getSum(bArr, arr[i] - minVal + 1, bArr.length - 1, blockSum ,k);
        }
        System.out.println(result);
    }

    static int[] findMinMax(int[] arr) {
        int minVal = arr[0];
        int maxVal = arr[0];
        for (int i = 0; i < arr.length; i++) {
            maxVal = Math.max(maxVal, arr[i]);
            minVal = Math.min(minVal, arr[i]);
        }
        return new int[] {minVal, maxVal};
    }

    static int calcSum(int[] arr, int l, int r) {
        int sum = 0;
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

    static long getSum(int[] arr, int l, int r, long[] blockSum, int k) {
        long sum = 0;
        for (int i = l; i <= r; ) {
            if (i % k == 0 && i + k <= r + 1) {
                sum += blockSum[i / k];
                i += k;
            } else {
                sum += arr[i];
                i++;
            }
        }
        return sum;
    }
}
