package org.harbour.space.heaps;

import java.util.Arrays;
import java.util.Scanner;

public class K_Minimums {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long a0 = scanner.nextLong();
        long a1 = scanner.nextLong();
        long A = scanner.nextLong();
        long B = scanner.nextLong();
        long C = scanner.nextLong();
        int M = scanner.nextInt();
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        int k = scanner.nextInt();

        int[] heap = new int[k];
        int heapElementCount = 0;
        for (int i = 0; i <= r; i++) {
            long a = 0;
            if (i == 0) {
                a = a0;
            }
            if (i == 1) {
                a = a1;
            }
            if (i > 1) {
                a = ((A * a0) + (B * a1) + C) % M;
                a0 = a1;
                a1 = a;
            }
            if (i >= l) {
                if (heapElementCount < k) {
                    heap[heap.length - 1 - heapElementCount] = (int)a;
                    heapify(heap, heap.length - 1 - heapElementCount);
                    heapElementCount++;
                } else {
                    if (a < heap[0]) {
                        heap[0] = (int)a;
                        heapify(heap, 0);
                    }
                }

            }
        }
        Arrays.stream(heap).sorted().forEach(value -> System.out.print(value + " "));
    }


    static void heapify(int[] heapArr, int i) {
        while (2 * i + 1 < heapArr.length) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < heapArr.length && heapArr[2 * i + 2] > heapArr[2 * i + 1]) {
                childInd = 2 * i + 2;
            }
            if (heapArr[i] >= heapArr[childInd]) {
                break;
            }
            swap(heapArr, i, childInd);
            i = childInd;
        }
    }

    static void swap(int[] heapArr, int firstNode, int secondNode) {
        int tempVal = heapArr[firstNode];
        heapArr[firstNode] = heapArr[secondNode];
        heapArr[secondNode] = tempVal;
    }
}
