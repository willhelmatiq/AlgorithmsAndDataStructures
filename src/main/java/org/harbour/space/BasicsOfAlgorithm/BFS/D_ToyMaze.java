package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class D_ToyMaze {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mazeParams = br.readLine().split(" ");
        int n = Integer.parseInt(mazeParams[0]);
        int m = Integer.parseInt(mazeParams[1]);
        int[][] mazeMatrix = new int[n][m];
        int[][] mazeDistance = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mazeDistance[i], -1);
        }
        for (int i = 0; i < n; i++){
            mazeMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        List<int[]> moves = new ArrayList<>();
        moves.add(new int[]{-1, 0});
        moves.add(new int[]{0, 1});
        moves.add(new int[]{1, 0});
        moves.add(new int[]{0, -1});
        System.out.println(bfs(mazeMatrix, new int[]{0, 0}, moves, mazeDistance));
    }

    static int bfs(int[][] mazeMatrix, int[] start, List<int[]> moves, int[][] mazeDistance) {
        mazeDistance[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            for (int[] move: moves) {
                int[] tempPos = new int[] {currPos[0], currPos[1]};
                while (tempPos[0] + move[0] >= 0 && tempPos[0] + move[0] < mazeMatrix.length &&
                        tempPos[1] + move[1] >= 0 && tempPos[1] + move[1] < mazeMatrix[tempPos[0]].length &&
                        mazeMatrix[tempPos[0] + move[0]][tempPos[1] + move[1]] != 1){
                    tempPos[0] += move[0];
                    tempPos[1] += move[1];
                    if (mazeMatrix[tempPos[0]][tempPos[1]] == 2) {
                        break;
                    }
                }
                if ((tempPos[0] != currPos[0] || tempPos[1] != currPos[1]) && mazeDistance[tempPos[0]][tempPos[1]] == -1 ){
                    mazeDistance[tempPos[0]][tempPos[1]] = mazeDistance[currPos[0]][currPos[1]] + 1;
                    queue.add(tempPos);
                }
                if (mazeMatrix[tempPos[0]][tempPos[1]] == 2) {
                    return mazeDistance[tempPos[0]][tempPos[1]];
                }

            }
        }
        return -1;
    }
}


