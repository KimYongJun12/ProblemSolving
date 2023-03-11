package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1594 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        if (n == 2) {
            System.out.println(s);
        } else {
            int[][] dp = new int[n + 1][2];
            String[] maxScorePhoneNum = new String[n + 1];

            maxScorePhoneNum[2] = s.substring(0, 2);
            maxScorePhoneNum[3] = s.substring(0, 3);

            dp[1][0] = dp[1][1] = -5000;

            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]);
                char c1 = s.charAt(i - 2);
                char c2 = s.charAt(i - 1);
                dp[i][0] += (c1 == c2) ? 2 : 0;

                if (i == 2) continue;
                dp[i][1] = Math.max(dp[i - 3][0], dp[i - 3][1]);
                char c3 = s.charAt(i - 3);
                if (c1 == c2 || c2 == c3 || c1 == c3) {
                    if (c1 == c2 && c2 == c3) dp[i][1]++;
                    dp[i][1]++;
                }

                if (i < 4) continue;

                String s1 = maxScorePhoneNum[i - 2] + '-' + s.substring(i - 2, i);
                if (dp[i][0] > dp[i][1]) {
                    maxScorePhoneNum[i] = s1;
                } else {
                    String s2 = maxScorePhoneNum[i - 3] + '-' + s.substring(i - 3, i);
                    if (dp[i][0] < dp[i][1]) {
                        maxScorePhoneNum[i] = s2;
                    } else {
                        if (s1.compareTo(s2) <= 0) {
                            maxScorePhoneNum[i] = s1;
                        } else maxScorePhoneNum[i] = s2;
                    }
                }
            }

            System.out.println(maxScorePhoneNum[n]);
        }

    }

}
