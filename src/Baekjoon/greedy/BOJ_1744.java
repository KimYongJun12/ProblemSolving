package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> minus = new ArrayList<>();
        ArrayList<Integer> plus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());
            if (now > 0) plus.add(now);
            else minus.add(now);
        }

        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus);

        int ans = 0;

        while (!plus.isEmpty()) {
            int a = plus.remove(0);
            if (!plus.isEmpty()) {
                int b = plus.remove(0);

                ans += Math.max(a * b, a + b);
            } else {
                ans += a;
            }
        }

        while (!minus.isEmpty()) {
            int a = minus.remove(0);
            if (!minus.isEmpty()) {
                int b = minus.remove(0);
                ans += a * b;
            } else {
                ans += a;
            }
        }

        System.out.println(ans);
    }
}
