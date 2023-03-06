package org.harbour.space.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Labyrinth {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] labyrinthParams = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] labyrinthMap = new int[labyrinthParams[0]][labyrinthParams[1]];
        int[][] dLabyrinthMap = new int[labyrinthParams[0]][labyrinthParams[1]];
        int[] start = new int[2];
        int[] finish = new int[2];
        for (int i = 0; i < labyrinthParams[0]; i++) {
            String labyrinthRow = br.readLine();
            for (int j = 0; j < labyrinthParams[1]; j++) {
                if (labyrinthRow.charAt(j) == '.') {
                    labyrinthMap[i][j] = -1;
                } else if (labyrinthRow.charAt(j) == '#') {
                    labyrinthMap[i][j] = -2;
                } else if (labyrinthRow.charAt(j) == 'S') {
                    labyrinthMap[i][j] = -1;
                    start[0] = i;
                    start[1] = j;
                } else if (labyrinthRow.charAt(j) == 'F') {
                    labyrinthMap[i][j] = -1;
                    finish[0] = i;
                    finish[1] = j;
                }
                dLabyrinthMap[i][j] = -1;
            }
        }
        BFS(labyrinthMap, dLabyrinthMap, start, finish);
        int d = dLabyrinthMap[finish[0]][finish[1]];
        System.out.println(d);
        if (d != -1) {
            Arrays.stream(getMoves(labyrinthMap, finish, dLabyrinthMap[finish[0]][finish[1]])).forEach(System.out::println);
        }
    }

    static void BFS(int[][] labyrinthMap, int[][] dLabyrinthMap, int[] start, int[] finish) {
        int s = fromPosToSingleIndex(start[0], start[1], labyrinthMap[0].length);
        labyrinthMap[start[0]][start[1]] = s;
        dLabyrinthMap[start[0]][start[1]] = 0;
        int finishInd = fromPosToSingleIndex(finish[0], finish[1], labyrinthMap[0].length);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            int[] posU = fromSingleIndexToPos(u, labyrinthMap[0].length);
            List<Integer> neighbourList = new ArrayList<>();
            fillNeighbourList(neighbourList, labyrinthMap, u);
            for (Integer e : neighbourList) {
                int[] tempPos = fromSingleIndexToPos(e, labyrinthMap[0].length);
                if (labyrinthMap[tempPos[0]][tempPos[1]] == -1) {
                    dLabyrinthMap[tempPos[0]][tempPos[1]] = dLabyrinthMap[posU[0]][posU[1]] + 1;
                    labyrinthMap[tempPos[0]][tempPos[1]] = u;
                    if (e == finishInd) {
                        return;
                    }
                    queue.add(e);
                }
            }
        }
    }

    static void fillNeighbourList(List<Integer> neighbourList, int[][] labyrinthMap, int index) {
        int[] pos = fromSingleIndexToPos(index, labyrinthMap[0].length);
        int i = pos[0];
        int j = pos[1];
        if (i > 0 && labyrinthMap[i - 1][j] == -1) {
            neighbourList.add(fromPosToSingleIndex(i - 1, j, labyrinthMap[0].length));
        }
        if (i > 0 && j < labyrinthMap[i].length - 1 && labyrinthMap[i - 1][j + 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i - 1, j + 1, labyrinthMap[0].length));
        }
        if (j < labyrinthMap[i].length - 1 && labyrinthMap[i][j + 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i, j + 1, labyrinthMap[0].length));
        }
        if (i < labyrinthMap.length - 1 && j < labyrinthMap[i].length - 1 && labyrinthMap[i + 1][j + 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i + 1, j + 1, labyrinthMap[0].length));
        }
        if (i < labyrinthMap.length - 1 && labyrinthMap[i + 1][j] == -1) {
            neighbourList.add(fromPosToSingleIndex(i + 1, j, labyrinthMap[0].length));
        }
        if (i < labyrinthMap.length - 1 && j > 0 && labyrinthMap[i + 1][j - 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i + 1, j - 1, labyrinthMap[0].length));
        }
        if (j > 0 && labyrinthMap[i][j - 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i, j - 1, labyrinthMap[0].length));
        }
        if (i > 0 && j > 0 && labyrinthMap[i - 1][j - 1] == -1) {
            neighbourList.add(fromPosToSingleIndex(i - 1, j - 1, labyrinthMap[0].length));
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

    static String[] getMoves(int[][] labyrinthMap, int[] finish, int d) {
        String[] moves = new String[d];
        int[] pos1;
        int[] pos2 = finish;
        while (d > 0) {
            pos1 = fromSingleIndexToPos(labyrinthMap[pos2[0]][pos2[1]], labyrinthMap[0].length);
            moves[d - 1] = detectMove(pos1, pos2);
            pos2 = pos1;
            d--;
        }
        return moves;
    }

    static String detectMove(int[] pos1, int[] pos2) {
        if (pos2[0] - pos1[0] == -1 && pos2[1] - pos1[1] == 0) {
            return "N";
        } else if ((pos2[0] - pos1[0] == -1 && pos2[1] - pos1[1] == 1)) {
            return "NE";
        } else if ((pos2[0] - pos1[0] == 0 && pos2[1] - pos1[1] == 1)) {
            return "E";
        } else if ((pos2[0] - pos1[0] == 1 && pos2[1] - pos1[1] == 1)) {
            return "SE";
        } else if ((pos2[0] - pos1[0] == 1 && pos2[1] - pos1[1] == 0)) {
            return "S";
        } else if ((pos2[0] - pos1[0] == 1 && pos2[1] - pos1[1] == -1)) {
            return "SW";
        } else if ((pos2[0] - pos1[0] == 0 && pos2[1] - pos1[1] == -1)) {
            return "W";
        } else {
            return "NW";
        }
    }
}
