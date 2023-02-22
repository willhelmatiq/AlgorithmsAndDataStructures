package org.harbour.space.hw2;

import java.util.Scanner;

public class HeapAndModify {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] sourceArr = new int[n];
        for (int i = 0; i < n; n++) {
            sourceArr[i] = scanner.nextInt();
        }
        int operationNum = scanner.nextInt();
        for (int i = 0; i < operationNum; i++) {
            int command = scanner.nextInt();
            if (command == 1) {

            } else {
                int ind = scanner.nextInt();
                int newNum = scanner.nextInt();
            }
        }
    }
}
