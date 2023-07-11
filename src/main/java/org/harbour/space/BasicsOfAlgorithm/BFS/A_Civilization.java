package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class A_Civilization {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mapParams = br.readLine().split(" ");
        int n = Integer.parseInt(mapParams[0]);
        int m = Integer.parseInt(mapParams[1]);
        int xStart = Integer.parseInt(mapParams[2]) - 1;
        int yStart = Integer.parseInt(mapParams[3]) - 1;
        int xTarget = Integer.parseInt(mapParams[4]) - 1;
        int yTarget = Integer.parseInt(mapParams[5]) - 1;
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int[][] parent = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(parent[i], -1);
        }
        bfs(map, new int[]{xStart, yStart, 0}, new int[]{xTarget, yTarget}, parent);
        printResult(map, parent, new int[]{xStart, yStart, 0}, new int[]{xTarget, yTarget});
    }

    static void bfs(char[][] map, int[] start, int[] target, int[][] parent) {
        parent[start[0]][start[1]] = posToNum(start, map[start[0]].length);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            if (currPos[0] == target[0] && currPos[1] == target[1]) {
               break;
            }
            generateMoves(queue, map, parent, currPos);
        }
    }


    static void generateMoves(Queue<int[]> queue, char[][] map, int[][] parent, int[] currPos ) {
        if (map[currPos[0]][currPos[1]] == 'W' && currPos[2] == 0) {
            currPos[2] = 1;
            queue.add(currPos);
        } else {
            if (currPos[0] > 0 && map[currPos[0] - 1][currPos[1]] != '#' && parent[currPos[0] - 1][currPos[1]] == -1) {
                queue.add(new int[] {currPos[0] - 1, currPos[1], 0});
                parent[currPos[0] - 1][currPos[1]] = posToNum(currPos, map[currPos[0]].length);
            }
            if (currPos[1] < map[currPos[0]].length - 1 && map[currPos[0]][currPos[1] + 1] != '#' && parent[currPos[0]][currPos[1] + 1] == -1) {
                queue.add(new int[] {currPos[0], currPos[1] + 1, 0});
                parent[currPos[0]][currPos[1] + 1] = posToNum(currPos, map[currPos[0]].length);
            }
            if (currPos[0] < map.length - 1 && map[currPos[0] + 1][currPos[1]] != '#' && parent[currPos[0] + 1][currPos[1]] == -1) {
                queue.add(new int[] {currPos[0] + 1, currPos[1], 0});
                parent[currPos[0] + 1][currPos[1]] = posToNum(currPos, map[currPos[0]].length);
            }
            if (currPos[1] > 0 && map[currPos[0]][currPos[1] - 1] != '#' && parent[currPos[0]][currPos[1] - 1] == -1) {
                queue.add(new int[] {currPos[0], currPos[1] - 1, 0});
                parent[currPos[0]][currPos[1]  - 1] = posToNum(currPos, map[currPos[0]].length);
            }
        }

    }

    static int posToNum(int[] pos, int m) {
        return pos[0] * m + pos[1];
    }

    static int[] numToPos(int p, int m){
        return new int[] {p / m, p % m};
    }

    static void printResult(char[][] map, int[][] parent,int[] start, int[] target) {
        if (parent[target[0]][target[1]] == -1) {
            System.out.println(-1);
        } else {
            int movesCount = 0;
            StringBuilder path = new StringBuilder();
            while (start[0] != target[0] || start[1] != target[1]) {
                int[] previousP = numToPos(parent[target[0]][target[1]], map[0].length);
                if (previousP[0] > target[0]) {
                    path.insert(0, 'N');
                }
                if (previousP[0] < target[0]) {
                    path.insert(0, 'S');
                }
                if (previousP[1] > target[1]) {
                    path.insert(0, 'W');
                }
                if (previousP[1] < target[1]) {
                    path.insert(0, 'E');
                }
                movesCount++;
                if (map[previousP[0]][previousP[1]] == 'W') {
                    movesCount++;
                }
                target = previousP;
            }
            System.out.println(movesCount);
            System.out.println(path);
        }
    }
}
