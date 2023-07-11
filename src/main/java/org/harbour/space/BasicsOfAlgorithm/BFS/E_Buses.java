package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E_Buses {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] startEnd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        startEnd[0]--;
        startEnd[1]--;
        int r = Integer.parseInt(br.readLine());
        ArrayList<int[]>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < r; i++) {
            int[] route = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            route[0]--;
            route[2]--;
            adjacencyList[route[0]].add(route);
        }
        System.out.println(bfs(adjacencyList, startEnd, visited));

    }

    static int bfs(ArrayList<int[]>[] adjacencyList, int[] startEnd, boolean[] visited) {
        if (startEnd[0] == startEnd[1]) {
            return 0;
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int[] neighbour: adjacencyList[startEnd[0]]) {
            if (neighbour[1] >= 0) {
                queue.add(new int[] {neighbour[2], 0, neighbour[1], neighbour[3]});
            }
        }
        visited[startEnd[0]] = true;
        while (!queue.isEmpty()) {
            int[] tempVillage = queue.poll();
            if (tempVillage[1] < tempVillage[3]) {
                tempVillage[1]++;
                queue.add(tempVillage);
            } else {
                if (tempVillage[0] == startEnd[1]) {
                    return tempVillage[1];
                }
                visited[tempVillage[0]] = true;
                for (int[] neighbour: adjacencyList[tempVillage[0]]) {
                    if (!visited[neighbour[2]] && tempVillage[1] <= neighbour[1]) {
                        queue.add(new int[] {neighbour[2], tempVillage[1], neighbour[1], neighbour[3]});
                    }
                }
            }
        }
        return -1;
    }
}
