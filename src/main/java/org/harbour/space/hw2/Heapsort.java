package org.harbour.space.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Heapsort {

    static int swapCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        applyHeapsort(arr);
        System.out.println(swapCount);
        Arrays.stream(arr).forEach(element -> System.out.print(element + " "));
    }

    static void applyHeapsort(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        while (n > 0) {
            int temp = arr[n - 1];
            arr[n - 1] = arr[0];
            arr[0] = temp;
            n--;
            heapify(arr, n, 0);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        while (2 * i + 1 < n) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < n && arr[2 * i + 2] > arr[2 * i + 1]) {
                childInd = 2 * i + 2;
            }
            if (arr[i] >= arr[childInd]) {
                break;
            }
            int temp = arr[childInd];
            arr[childInd] = arr[i];
            arr[i] = temp;
            swapCount++;
            i = childInd;
        }
    }
}
