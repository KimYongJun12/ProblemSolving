package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891 {
    static boolean[][] gear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gear = new boolean[4][8];

        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = input.charAt(j) == '1';
            }
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            boolean prev = gear[idx][2];
            int nowIdx = idx + 1;
            int nowDir = -dir;
            while (nowIdx < 4 && prev != gear[nowIdx][6]) {
                prev = gear[nowIdx][2];
                spin(nowIdx, nowDir);
                nowDir *= -1;
                nowIdx++;
            }

            prev = gear[idx][6];
            nowIdx = idx - 1;
            nowDir = -dir;
            while (nowIdx >= 0 && prev != gear[nowIdx][2]) {
                prev = gear[nowIdx][6];
                spin(nowIdx, nowDir);
                nowDir *= -1;
                nowIdx--;
            }

            spin(idx, dir);
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i][0]) {
                ans += 1 << i;
            }
        }

        System.out.println(ans);
    }

    private static void spin(int idx, int dir) {
        boolean[] temp = new boolean[8];
        for (int i = 1, prev = 0; i <= 8; i++) {
            int next = (8 + i * dir) % 8;
            temp[next] = gear[idx][prev];
            prev = next;
        }

        gear[idx] = temp.clone();
    }
}
