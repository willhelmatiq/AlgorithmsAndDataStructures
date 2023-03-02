package org.harbour.space.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabyrinthAnalysis {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] labyrinthParams = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] labyrinthMap = new int[labyrinthParams[0]][labyrinthParams[1]];
        for (int i = 0; i < labyrinthParams[0]; i++) {
            String labyrinthRow = br.readLine();
            for (int j = 0; j < labyrinthParams[1]; j++) {
                if (labyrinthRow.charAt(j) == '.') {
                    labyrinthMap[i][j] = -1;
                } else {
                    labyrinthMap[i][j] = -2;
                }
            }
        }

        List<Integer> connectedVertex = new ArrayList<>();
        List<Integer>  componentSize = new ArrayList<>();
        for (int i = 0; i < labyrinthParams[0] * labyrinthParams[1]; i++) {
            int[] tempPos = fromSingleIndexToPos(i, labyrinthMap[0].length);
            if (labyrinthMap[tempPos[0]][tempPos[1]] == -1) {
                DFS(i, labyrinthMap, connectedVertex);
                componentSize.add(connectedVertex.size());
                connectedVertex.clear();
            }
        }
        System.out.println(componentSize.size());
        componentSize.stream().sorted().forEach(element -> System.out.print(element + " "));
    }

    static void DFS(int u, int[][] labyrinthMap, List<Integer> connectedVertex) {
        int[] start = fromSingleIndexToPos(u, labyrinthMap[0].length);
        while (labyrinthMap[start[0]][start[1]] != -1) {
            u++;
            start = fromSingleIndexToPos(u, labyrinthMap[0].length);
        }
        labyrinthMap[start[0]][start[1]] = u;
        connectedVertex.add(u);
        List<Integer> neighbourList = new ArrayList<>();
        fillNeighbourList(neighbourList, labyrinthMap, u);
        for (Integer v : neighbourList) {
            int[] tempPos = fromSingleIndexToPos(v, labyrinthMap[0].length);
            if (labyrinthMap[tempPos[0]][tempPos[1]] == -1) {
                DFS(v, labyrinthMap, connectedVertex);
            }
        }
    }

    static int fromPosToSingleIndex(int i, int j, int n) {
        return (n * i) + j;
    }

    static int[] fromSingleIndexToPos(int index, int n) {
        int[] pos = new int[2];
        pos[0] = index / n;
        pos[1] = index % n;
        return pos;
    }

    static void fillNeighbourList(List<Integer> neighbourList, int[][] labyrinthMap, int index) {
        int[] pos = fromSingleIndexToPos(index, labyrinthMap[0].length);
        int i = pos[0];
        int j = pos[1];
        if (i > 0 && labyrinthMap[i - 1][j] == -1) {
            neighbourList.add(fromPosToSingleIndex(i - 1, j, labyrinthMap[0].length));
        }
//        if (i > 0 && j < labyrinthMap[i].length - 1 && labyrinthMap[i - 1][j + 1] == -1) {
//            neighbourList.add(fromPosToSingleIndex(i - 1, j + 1, labyrinthMap[0].length));
//        }
        if (j < labyrinthMap[i].length - 1 && labyrinthMap[i][j + 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i, j + 1, labyrinthMap[0].length));
        }
//        if (i < labyrinthMap.length - 1 && j < labyrinthMap[i].length - 1 && labyrinthMap[i + 1][j + 1] == -1) {
//            neighbourList.add(fromPosToSingleIndex(i + 1, j + 1, labyrinthMap[0].length));
//        }
        if (i < labyrinthMap.length - 1 && labyrinthMap[i + 1][j] == -1) {
            neighbourList.add(fromPosToSingleIndex(i + 1, j, labyrinthMap[0].length));
        }
//        if (i < labyrinthMap.length - 1 && j > 0 && labyrinthMap[i + 1][j - 1] == -1) {
//            neighbourList.add(fromPosToSingleIndex(i + 1, j - 1, labyrinthMap[0].length));
//        }
        if (j > 0 && labyrinthMap[i][j - 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i, j - 1, labyrinthMap[0].length));
        }
//        if (i > 0 && j > 0 && labyrinthMap[i - 1][j - 1] == -1) {
//            neighbourList.add(fromPosToSingleIndex(i - 1, j - 1, labyrinthMap[0].length));
//        }
    }
}
