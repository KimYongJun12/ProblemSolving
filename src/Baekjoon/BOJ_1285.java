package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1285 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        boolean[][] coins = new boolean[n][n];
        int minCnt = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                coins[i][j] = s.charAt(j) == 'T';
            }
        }

        for (int i = 0; i < (1 << n); i++) {
            minCnt = Math.min(minCnt, countTails(i, coins));
        }

        System.out.println(minCnt);
    }

    private static int countTails(int bit, boolean[][] coins) {
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            int nowCnt = 0;
            for (int i = 0; i < n; i++) {
                boolean now = coins[i][j];
                if ((bit & (1 << i)) != 0) {
                    now = !now;
                }
                if (now) nowCnt++;
            }
            cnt += Math.min(nowCnt, n - nowCnt);
        }

        return cnt;
    }

}