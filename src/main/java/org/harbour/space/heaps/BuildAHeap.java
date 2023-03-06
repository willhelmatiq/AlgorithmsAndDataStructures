package org.harbour.space.heaps;

import java.util.Arrays;
import java.util.Scanner;

public class BuildAHeap {
    static StringBuilder sb = new StringBuilder();
    static int swapCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        arrToHeap(arr);
    }

    static void arrToHeap(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i);
        }
        System.out.println(swapCount);
        System.out.print(sb);
        Arrays.stream(arr).forEach(element -> System.out.print(element + " "));
    }

    static void heapify(int[] arr, int i) {
        while (2 * i + 1 < arr.length) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < arr.length && arr[2 * i + 2] > arr[2 * i + 1]) {
                childInd = 2 * i + 2;
            }
            if (arr[i] >= arr[childInd]) {
                break;
            }
            int temp = arr[childInd];
            arr[childInd] = arr[i];
            arr[i] = temp;
            sb.append((i)).append(" ").append(childInd).append("\n");
            swapCount++;
            i = childInd;
        }
    }
}
