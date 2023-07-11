package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B_OneHungryHorse {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] startPos = br.readLine().split(" ");
        int xStart = Integer.parseInt(startPos[0]) - 1;
        int yStart = Integer.parseInt(startPos[1]) - 1;
        String[] targetPos = br.readLine().split(" ");
        int xTarget = Integer.parseInt(targetPos[0]) - 1;
        int yTarget = Integer.parseInt(targetPos[1]) - 1;
        int[][] desk = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(desk[i], -1);
        }
        bfs(desk, new int[] {xStart, yStart}, new int[] {xTarget, yTarget});
        printResult(desk, new int[] {xStart, yStart}, new int[] {xTarget, yTarget});
    }

    static void bfs(int[][] desk, int[] start, int[] target) {
        desk[start[0]][start[1]] = posToNum(start, desk.length);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            if (currPos[0] == target[0] && currPos[1] == target[1]) {
                break;
            }
            generateMoves(queue, desk, currPos);
        }
    }

    static int posToNum(int[] pos, int m) {
        return pos[0] * m + pos[1];
    }

    static int[] numToPos(int p, int m){
        return new int[] {p / m, p % m};
    }

    static void generateMoves(Queue<int[]> queue, int[][] desk, int[] curPos) {
        if (curPos[0] > 1) {
            if (curPos[1] > 0 && desk[curPos[0] - 2][curPos[1] - 1] == -1) {
                queue.add(new int[] {curPos[0] - 2, curPos[1] - 1});
                desk[curPos[0] - 2][curPos[1] - 1] = posToNum(curPos, desk.length);
            }
            if (curPos[1] < desk.length - 1 && desk[curPos[0] - 2][curPos[1] + 1] == -1) {
                queue.add(new int[] {curPos[0] - 2, curPos[1] + 1});
                desk[curPos[0] - 2][curPos[1] + 1] = posToNum(curPos, desk.length);
            }
        }
        if (curPos[1] < desk.length - 2) {
            if (curPos[0] > 0 && desk[curPos[0] - 1][curPos[1] + 2] == -1) {
                queue.add(new int[] {curPos[0] - 1, curPos[1] + 2});
                desk[curPos[0] - 1][curPos[1] + 2] = posToNum(curPos, desk.length);
            }
            if (curPos[0] < desk.length - 1 && desk[curPos[0] + 1][curPos[1] + 2] == -1) {
                queue.add(new int[] {curPos[0] + 1, curPos[1] + 2});
                desk[curPos[0] + 1][curPos[1] + 2] = posToNum(curPos, desk.length);
            }
        }
        if (curPos[0] < desk.length - 2) {
            if (curPos[1] < desk.length - 1 && desk[curPos[0] + 2][curPos[1] + 1] == -1) {
                queue.add(new int[] {curPos[0] + 2, curPos[1] + 1});
                desk[curPos[0] + 2][curPos[1] + 1] = posToNum(curPos, desk.length);
            }
            if (curPos[1] > 0 && desk[curPos[0] + 2][curPos[1] - 1] == -1) {
                queue.add(new int[] {curPos[0] + 2, curPos[1] - 1});
                desk[curPos[0] + 2][curPos[1] - 1] = posToNum(curPos, desk.length);
            }
        }
        if (curPos[1] > 1) {
            if (curPos[0] < desk.length - 1 && desk[curPos[0] + 1][curPos[1] - 2] == -1) {
                queue.add(new int[] {curPos[0] + 1, curPos[1] - 2});
                desk[curPos[0] + 1][curPos[1] - 2] = posToNum(curPos, desk.length);
            }
            if (curPos[0] > 0 && desk[curPos[0] - 1][curPos[1] - 2] == -1) {
                queue.add(new int[] {curPos[0] - 1, curPos[1] - 2});
                desk[curPos[0] - 1][curPos[1] - 2] = posToNum(curPos, desk.length);
            }
        }
    }

    static void printResult(int[][] desk, int[] start, int[] target) {
        int movesCount = 0;
        StringBuilder path = new StringBuilder();
        path.insert(0, "\n" + (target[0] + 1)  + " " + (target[1] + 1));
        while (start[0] != target[0] || start[1] != target[1]) {
            int[] previousP = numToPos(desk[target[0]][target[1]], desk.length);
            path.insert(0, "\n" + (previousP[0] + 1) + " " + (previousP[1] + 1));
            target = previousP;
            movesCount++;
        }
        System.out.println(movesCount);
        System.out.println(path);
    }
}
