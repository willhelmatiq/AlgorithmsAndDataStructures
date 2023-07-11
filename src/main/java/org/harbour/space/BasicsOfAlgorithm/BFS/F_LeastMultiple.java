package org.harbour.space.BasicsOfAlgorithm.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class F_LeastMultiple {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int dSize = Integer.parseInt(br.readLine());
        if (dSize > 0) {
            int[] digits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(digits);
            bfs(params[0], Integer.parseInt(params[1]), digits);
        }
        else {
            System.out.println(getReminder(params[0], Integer.parseInt(params[1])) > 0 ? -1 : params[0]);
        }

    }

    static void bfs(String x, int k, int[] digits){
        int baseNum = getReminder(x, k);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(baseNum);
        int[][] parent = new int[k][2];
        for (int i = 0; i < k; i++) {
            parent[i][0] = -1;
        }

        parent[baseNum][0] = baseNum;
        parent[baseNum][1] = -1;
        while (!queue.isEmpty()) {
            int tempNum = queue.poll();
            for (int digit: digits) {
                int newNum = increaseBaseNum(tempNum, digit) % k;
                if (parent[newNum][0] == -1) {
                    parent[newNum][0] = tempNum;
                    parent[newNum][1] = digit;
                    queue.add(newNum);
                }
            }
        }
        if (parent[0][0] == -1) {
            System.out.println(-1);
        } else {
            printResult(x, parent);
        }

    }

    static int getReminder(String x, int k) {
        int charIndex = 0;
        int baseNum = 0;
        while (charIndex < x.length()) {
            baseNum = increaseBaseNum(baseNum, Character.digit(x.charAt(charIndex), 10)) % k;
            charIndex++;
        }
        return baseNum;
    }

    static int increaseBaseNum(int baseNum, int nextDigit) {
        return baseNum * 10 + nextDigit;
    }

    static void printResult(String x, int[][] parent) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        while (parent[start][0] != start) {
            result.insert(0, parent[start][1]);
            start = parent[start][0];
        }
        result.insert(0, x);
        System.out.println(result);
    }
}
