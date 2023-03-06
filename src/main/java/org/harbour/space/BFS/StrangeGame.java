package org.harbour.space.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StrangeGame {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int a = Integer.parseInt(params[1]);
        long b = Integer.parseInt(params[2]);
        Map<Long, Integer> dMap = new HashMap<>();
        Map<Long, Long> parentMap = new HashMap<>();
        BFS(a, b, dMap, parentMap, n);
        if (!dMap.containsKey(b)) {
            System.out.println(-1);
        } else {
            long[] result = new long[dMap.get(b) + 1];
            int k = dMap.get(b);
            for (long i = b; i != parentMap.get(i); i = parentMap.get(i)) {
                result[k] = i;
                k--;
            }
            result[k] = a;
            System.out.println(dMap.get(b));
            Arrays.stream(result).forEach( value -> System.out.print(value + " "));
        }
    }

    static long generateFirstValue(long x, long n) {
        return (x + 1) % n;
    }

    static long generateSecondValue(long x, long n) {
        return ((x * x) + 1) % n;
    }

    static long generateThirdValue(long x, long n) {
        return ((x * x * x) + 1) % n;
    }

    static void BFS(long start, long end, Map<Long, Integer> dMap, Map<Long, Long> parentMap, long n) {
        parentMap.put(start, start);
        dMap.put(start, 0);
        Queue<Long> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            long u = queue.poll();
            List<Long> neighbourList = new ArrayList<>();
            fillNeighbourList(neighbourList, u, n);
            for (Long neighbour: neighbourList) {
                if (!parentMap.containsKey(neighbour)) {
                    dMap.put(neighbour, dMap.get(u) + 1);
                    parentMap.put(neighbour, u);
                    if (neighbour == end) {
                        return;
                    }
                    queue.add(neighbour);
                }
            }
        }
    }

    static void fillNeighbourList(List<Long> neighbourList, long x, long n) {
        long neighbour1 = generateFirstValue(x, n);
        long neighbour2 = generateSecondValue(x, n);
        long neighbour3 = generateThirdValue(x, n);
        neighbourList.add(neighbour1);
        neighbourList.add(neighbour2);
        neighbourList.add(neighbour3);
    }
}
