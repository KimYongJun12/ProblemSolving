package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16434 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long ATK = Long.parseLong(st.nextToken());
        long maxH = 0L;
        long DMG = 0L;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());

            if (t == 1) {
                long nowDMG = (h - 1) / ATK;
                DMG += nowDMG * a;
                maxH = Math.max(maxH, DMG);
            } else {
                ATK += a;
                DMG = Math.max(0, DMG - h);
            }
        }

        System.out.println(maxH + 1);
    }
}
