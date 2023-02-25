package org.harbour.space.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SegmentsColoring {

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int range = Integer.parseInt(params[0]);
        int n = Integer.parseInt(params[1]);
        List<int[]> colorings = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int[] coloring = Arrays.stream(new StringBuilder(br.readLine()).append(" ").append(i).toString().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            colorings.add(coloring);
        }

        colorings.sort(Comparator.comparing(coloring -> coloring[0]));
        List<Integer>[] colorEndListArray = new ArrayList[range + 1];
        int[][] permutations = new int[n][2];
        for (int i = 0; i < n; i++) {
            permutations[i][0] = i;
            permutations[i][1] = -1;
        }

        int[][] heap = new int[n][2];
        int heapLen = 0;
        int coloringNumber = 0;
        for (int i = 0; i < range; i++) {
            while (coloringNumber < n && colorings.get(coloringNumber)[0] == i) {

                heap[heapLen][0] = colorings.get(coloringNumber)[3];
                heap[heapLen][1] = colorings.get(coloringNumber)[2];
                permutations[colorings.get(coloringNumber)[3]][1] = heapLen;

                pushUp(heap, heapLen, permutations);
                heapLen++;
                if (colorEndListArray[colorings.get(coloringNumber)[1]] == null) {
                    colorEndListArray[colorings.get(coloringNumber)[1]] = new ArrayList<>();
                }
                colorEndListArray[colorings.get(coloringNumber)[1]].add(colorings.get(coloringNumber)[3]);
                coloringNumber++;
            }
            if (colorEndListArray[i] != null) {
                while (!colorEndListArray[i].isEmpty()) {
                    int valueToRemove = colorEndListArray[i].get(colorEndListArray[i].size() - 1);
                    int heapInd = permutations[valueToRemove][1];
                    swap(heap, heapInd, heapLen - 1, permutations);
                    heapLen--;
                    pushUp(heap, heapInd, permutations);
                    heapify(heap, heapInd, heapLen, permutations);

                    colorEndListArray[i].remove(colorEndListArray[i].size() - 1);
                }
                colorEndListArray[i] = null;
            }
            if (heapLen > 0) {
                result.append(heap[0][1]).append(" ");
            } else {
                result.append(0).append(" ");
            }
        }
        System.out.println(result);
    }

    static void pushUp(int[][] heapArr, int i, int[][] pArr) {
        while (i-1 >= 0) {
            int parent = (i - 1) / 2;

            if (heapArr[i][0] <= heapArr[parent][0]) {
                break;
            }
            swap(heapArr, i, parent, pArr);
            i = parent;
        }
    }

    static void swap(int[][] heapArr, int firstNode, int secondNode, int[][] pArr) {
        int tempOrder = heapArr[firstNode][0];
        int tempColor = heapArr[firstNode][1];

        pArr[heapArr[firstNode][0]][1] = secondNode;
        pArr[heapArr[secondNode][0]][1] = firstNode;

        heapArr[firstNode][0] = heapArr[secondNode][0];
        heapArr[firstNode][1] = heapArr[secondNode][1];
        heapArr[secondNode][0] = tempOrder;
        heapArr[secondNode][1] = tempColor;
    }

    static void heapify(int[][] heapArr, int i, int heapLen,  int[][] pArr) {
        while (2 * i + 1 < heapLen) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < heapLen && heapArr[2 * i + 2][0] > heapArr[2 * i + 1][0]) {
                childInd = 2 * i + 2;
            }
            if (heapArr[i][0] >= heapArr[childInd][0]) {
                break;
            }
            swap(heapArr, i, childInd, pArr);
            i = childInd;
        }
    }
}
