package org.harbour.space.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeAPs {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<int[]> apList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] ap = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            apList.add(ap);
        }
        int k = Integer.parseInt(br.readLine());
        long[] result = new long[k];
        long[][] heapMin = buildHeap(apList);
        for (int i = 0; i < k; i++) {
            result[i] = heapMin[0][0];
            addNewElement(heapMin);
        }
        Arrays.stream(result).forEach(element -> System.out.print(element + " "));
    }

    static long[][] buildHeap(List<int[]> apList) {
        long[][] heap = new long[apList.size()][2];
        for (int i = apList.size() - 1; i >= 0; i--) {
            heap[i][0] = apList.get(i)[0];
            heap[i][1] = apList.get(i)[1];
            heapifyMin(heap, i);
        }
        return heap;
    }

    static void heapifyMin(long[][] heapArr, int i) {
        while (2 * i + 1 < heapArr.length) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < heapArr.length && heapArr[2 * i + 2][0] < heapArr[2 * i + 1][0]) {
                childInd = 2 * i + 2;
            }
            if (heapArr[i][0] <= heapArr[childInd][0]) {
                break;
            }
            swap(heapArr, i, childInd);
            i = childInd;
        }
    }

    static void swap(long[][] heapArr, int firstNode, int secondNode) {
        long tempVal = heapArr[firstNode][0];
        long tempRes = heapArr[firstNode][1];
        heapArr[firstNode][0] = heapArr[secondNode][0];
        heapArr[firstNode][1] = heapArr[secondNode][1];
        heapArr[secondNode][0] = tempVal;
        heapArr[secondNode][1] = tempRes;

    }

    static void addNewElement(long[][] heapMin) {
        heapMin[0][0] += heapMin[0][1];
        heapifyMin(heapMin, 0);
    }
}
