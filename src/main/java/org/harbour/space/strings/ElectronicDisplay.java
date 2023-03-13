package org.harbour.space.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ElectronicDisplay {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String sHelper = s1 + "#" + s2;
        int[] b = buildPrefixFunction(sHelper);
        System.out.println(s1.length() - b[b.length - 1]);
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
