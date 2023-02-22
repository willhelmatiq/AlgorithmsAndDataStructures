package org.harbour.space.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MergeArrays {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraysCount = scanner.nextInt();
        List<int[]> arraysList = new ArrayList<>();
        int resultLength = 0;
        for (int i = 0; i < arraysCount; i++) {
            int[] arr = new int[scanner.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scanner.nextInt();
                resultLength++;
            }
            arraysList.add(arr);
        }
        int[] indexes = new int[arraysList.size()];
        Arrays.stream(merge(arraysList, resultLength, indexes)).forEach(element -> System.out.print(element + " "));
    }

    static void heapifyMin(int[][] arr, int n, int i) {
        while (2 * i + 1 < n) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < n && arr[2 * i + 2][0] < arr[2 * i + 1][0]) {
                childInd = 2 * i + 2;
            }
            if (arr[i][0] <= arr[childInd][0]) {
                break;
            }
            swap(arr, i, childInd);
            i = childInd;
        }
    }

    static void swap(int[][] arr, int indexFrom, int indexTo ) {
        int temp0 = arr[indexFrom][0];
        int temp1 = arr[indexFrom][1];
        arr[indexFrom][0] = arr[indexTo][0];
        arr[indexFrom][1] = arr[indexTo][1];
        arr[indexTo][0] = temp0;
        arr[indexTo][1] = temp1;
    }

    static int[] merge(List<int[]> arraysList, int resultLength, int[] indexes) {
        int[] result = new int[resultLength];
        int n = 0;
        int[][] minHeap = new int[arraysList.size()][2];
        int minHeapLen = minHeap.length;
        for (int i = minHeapLen - 1; i >= 0; i--) {
            minHeap[i][0] = arraysList.get(i)[0];
            minHeap[i][1] = i;
            heapifyMin(minHeap, minHeapLen,  i);
        }

        while (n < resultLength) {
            result[n] = minHeap[0][0];
            int arrNumb = minHeap[0][1];
            if (indexes[arrNumb] < arraysList.get(arrNumb).length - 1){
                indexes[arrNumb]++;
                minHeap[0][0] = arraysList.get(arrNumb)[indexes[arrNumb]];
            } else {
                minHeap[0][0] = minHeap[minHeapLen - 1][0];
                minHeap[0][1] = minHeap[minHeapLen - 1][1];
                minHeapLen--;
            }
            n++;
            heapifyMin(minHeap, minHeapLen, 0);
        }
        return result;
    }
}
