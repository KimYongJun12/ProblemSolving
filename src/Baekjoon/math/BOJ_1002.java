package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            int ans;
            double d = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
            if (x1 == x2 && y1 == y2 && r1 == r2) {
                ans = -1;
            } else if (d + Math.min(r1, r2) < Math.max(r1, r2)) {
                ans = 0;
            } else if (d == r1 + r2 || d == Math.abs(r1 - r2)) {
                ans = 1;
            } else if (d < r1 + r2) {
                ans = 2;
            } else {
                ans = 0;
            }

            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }
}
