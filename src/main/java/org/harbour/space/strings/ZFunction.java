package org.harbour.space.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ZFunction {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder result = new StringBuilder();
        Arrays.stream(buildZFunction(s)).forEach(value -> result.append(value).append(" "));
        System.out.println(result);
    }

    static int[] buildZFunction(String s) {
        int[] z = new int[s.length()];
        int l = 0;
        int r = 0;
        for (int i = 1; i < s.length(); i++) {
            if (r >= i) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (z[i] + i < s.length() && s.charAt(z[i]) == s.charAt(z[i] + i)) {
                z[i] ++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
}
