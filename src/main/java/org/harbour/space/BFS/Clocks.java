package org.harbour.space.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Clocks {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] clickParams = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] startTime = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] endTime = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] timeState = new int[24][60];
        int[][] dTimeState = new int[24][60];
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                timeState[i][j] = -1;
                dTimeState[i][j] = 1440;
            }
        }

        BFS(timeState, dTimeState, startTime, endTime, clickParams);
        if (timeState[endTime[0]][endTime[1]] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(dTimeState[endTime[0]][endTime[1]]);
        }
    }

    static int[] click(int hour, int minute, int[] time) {
        int[] result = new int[2];
        result[0] = (time[0] + hour) % 24;
        result[1] = (time[1] + minute) % 60;
        return result;
    }

    static void BFS(int[][] timeState, int[][] dTimeState, int[] startTime, int[] endTime, int[] clickParams) {
        timeState[startTime[0]][startTime[1]] = 1;
        dTimeState[startTime[0]][startTime[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startTime);
        while (!queue.isEmpty()) {
            int[] tempTime = queue.poll();
             List<int[]> neighbourList = new ArrayList<>();
             neighbourList.add(click(clickParams[0], clickParams[1], tempTime));
             neighbourList.add(click(clickParams[2], clickParams[3], tempTime));
             for (int[] neighbour: neighbourList) {
                 if (timeState[neighbour[0]][neighbour[1]] == -1) {
                     dTimeState[neighbour[0]][neighbour[1]] = dTimeState[tempTime[0]][tempTime[1]] + 1;
                     timeState[neighbour[0]][neighbour[1]] = 1;
                     if (neighbour[0] == endTime[0] && neighbour[1] == endTime[1]){
                         return;
                     }
                     queue.add(neighbour);
                 }
             }
        }
    }
}
