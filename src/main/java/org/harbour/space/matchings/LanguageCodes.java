package org.harbour.space.matchings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LanguageCodes {

    static int  alphabetSize = 26;
    static int charCodeToAlphabetSizeConstant = 97;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n != 0) {
            List<List<String>> dict = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String s = br.readLine().toLowerCase();
                if (dict.isEmpty()){
                    List<String> stringList = new ArrayList<>();
                    stringList.add(s);
                    dict.add(stringList);
                } else {
                    boolean strWasAdded = false;
                    for (List<String> innerList : dict) {
                        if (innerList.get(0).charAt(0) == s.charAt(0)) {
                            innerList.add(s);
                            strWasAdded = true;
                            break;
                        }
                    }
                    if (!strWasAdded) {
                        List<String> stringList = new ArrayList<>();
                        stringList.add(s);
                        dict.add(stringList);
                    }
                }
            }
            int codeCount = 0;
            for (List<String> innerList : dict) {
                if (innerList.size() > 1) {
                    codeCount += processMaxMatching(innerList);
                } else {
                    codeCount++;
                }
            }
            sb.append(codeCount).append("\n");
            n = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }

    static int processMaxMatching(List<String> innerList) {
        int[][] adjacencyList = new int[innerList.size()][alphabetSize];
        for (int i = 0; i < innerList.size(); i++) {
            char[] charArr = innerList.get(i).toCharArray();
            Arrays.fill(adjacencyList[i], -1);
            for (int j = 1; j < charArr.length; j++) {
                adjacencyList[i][(int) charArr[j] - charCodeToAlphabetSizeConstant] = 1;
            }
        }

        int[] M = new  int[alphabetSize];
        Arrays.fill(M, -1);
        boolean[] used = new boolean[innerList.size()];
        maxMatching(M, adjacencyList, used);
        int maxMatchingCount = 0;
        for (int i = 0; i < alphabetSize; i++) {
            if (M[i] != -1) {
                maxMatchingCount ++;
            }
        }
        return maxMatchingCount;
    }

    static void maxMatching(int[] M, int[][] adjacencyList, boolean[] used) {
        for (int i = 0; i < adjacencyList.length; i++) {
            Arrays.fill(used, false);
            tryKuhn(i, M, used, adjacencyList);
        }
    }

    static boolean tryKuhn(int a, int[] M, boolean[] used, int[][] adjacencyList) {
        if (used[a]) {
            return false;
        }
        used[a] = true;
        for (int i = 0; i < adjacencyList[0].length; i++) {
            if (adjacencyList[a][i] != -1){
                if (M[i] == -1 || tryKuhn(M[i], M, used, adjacencyList)) {
                    M[i] = a;
                    return true;
                }
            }
        }
        return false;
    }
}
