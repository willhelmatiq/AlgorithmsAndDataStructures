package org.harbour.space.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PrefixFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder result = new StringBuilder();
        Arrays.stream(buildPrefixFunction(s)).forEach(value -> result.append(value).append(" "));
        System.out.println(result);
    }

    static int[] buildPrefixFunction(String s) {
        int[] b = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            int k = b[i - 1];
            while (k > 0 && s.charAt(k) != s.charAt(i)){
                k = b[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                b[i] = k + 1;
            }
        }
        return b;
    }
}
