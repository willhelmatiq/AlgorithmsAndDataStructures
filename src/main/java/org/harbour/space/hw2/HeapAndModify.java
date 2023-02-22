package org.harbour.space.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HeapAndModify {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] sourceArr = new int[arr.length][2];
        int[][] heapArr = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            sourceArr[i][0] = arr[i];
            sourceArr[i][1] = i;
            heapArr[i][0] = arr[i];
            heapArr[i][1] = i;
        }
        for (int i = heapArr.length - 1; i >= 0; i--) {
            heapify(heapArr, i, sourceArr);
        }

        int operationNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < operationNum; i++) {

            String[] commands = br.readLine().split(" ");
            if (Integer.parseInt(commands[0]) == 1) {
                System.out.println(heapArr[0][0]);
            } else {
                int indSource = Integer.parseInt(commands[1]) - 1;
                int newNum = Integer.parseInt(commands[2]);
                int indHeap = sourceArr[indSource][1];
                heapArr[indHeap][0] = newNum;
                sourceArr[indSource][0] = newNum;
                heapify(heapArr, indHeap, sourceArr);
                pushUp(heapArr, indHeap, sourceArr);
            }
        }
    }

    static void heapify(int[][] heapArr, int i, int[][] sourceArr) {
        while (2 * i + 1 < heapArr.length) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < heapArr.length && heapArr[2 * i + 2][0] > heapArr[2 * i + 1][0]) {
                childInd = 2 * i + 2;
            }
            if (heapArr[i][0] >= heapArr[childInd][0]) {
                break;
            }
            swap(heapArr, i, childInd, sourceArr);
            i = childInd;
        }
    }

    static void pushUp(int[][] heapArr, int i, int[][] sourceArr) {
        while (i-1 >= 0) {
            int parent = (i - 1) / 2;
            
            if (heapArr[i][0] <= heapArr[parent][0]) {
                break;
            }
            swap(heapArr, i, parent, sourceArr);
            i = parent;
        }
    }

    static void swap(int[][] heapArr, int firstNode, int secondNode, int[][] sourceArr) {
        int tempVal = heapArr[firstNode][0];
        int tempPos = heapArr[firstNode][1];

        sourceArr[heapArr[secondNode][1]][1] = firstNode;
        sourceArr[heapArr[firstNode][1]][1] = secondNode;

        heapArr[firstNode][0] = heapArr[secondNode][0];
        heapArr[firstNode][1] = heapArr[secondNode][1];
        heapArr[secondNode][0] = tempVal;
        heapArr[secondNode][1] = tempPos;

    }
}
