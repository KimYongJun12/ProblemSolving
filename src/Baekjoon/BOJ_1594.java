package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1594 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] number = br.readLine().toCharArray();

        if (number.length == 2) {
            System.out.println(number[0] + "" + number[1]);
        } else {
            int n = number.length;
            dp = new int[n + 1][2];
            String[] maxScorePhoneNum = new String[n + 1];

            maxScorePhoneNum[2] = number[0] + "" + number[1];
            maxScorePhoneNum[3] = maxScorePhoneNum[2] + number[2];

            dp[1][0] = dp[1][1] = -5000;
            dp[2][0] = (number[0] == number[1]) ? 2 : 0;
            if (number[0] == number[1] || number[0] == number[2] || number[1] == number[2]) {
                if (number[0] == number[1] && number[1] == number[2]) dp[3][1] = 2;
                else dp[3][1] = 1;
            }

            for (int i = 4; i <= n; i++) {
                dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]);
                dp[i][0] += (number[i - 2] == number[i - 1]) ? 2 : 0;

                dp[i][1] = Math.max(dp[i - 3][0], dp[i - 3][1]);
                if (number[i - 3] == number[i - 2] || number[i - 3] == number[i - 1] || number[i - 2] == number[i - 1]) {
                    if (number[i - 3] == number[i - 2] && number[i - 2] == number[i - 1]) dp[i][1] += 2;
                    else dp[i][1] += 1;
                }

                if (dp[i][0] > dp[i][1]) {
                    maxScorePhoneNum[i] = maxScorePhoneNum[i - 2] + '-' + number[i - 2] + number[i - 1];
                } else if (dp[i][0] < dp[i][1]) {
                    maxScorePhoneNum[i] = maxScorePhoneNum[i - 3] + '-' + number[i - 3] + number[i - 2] + number[i - 1];
                } else {
                    String s1 = maxScorePhoneNum[i - 2] + '-' + number[i - 2] + number[i - 1];
                    String s2 = maxScorePhoneNum[i - 3] + '-' + number[i - 3] + number[i - 2] + number[i - 1];

                    if (s1.compareTo(s2) <= 0) {
                        maxScorePhoneNum[i] = s1;
                    } else maxScorePhoneNum[i] = s2;
                }
            }

            System.out.println(maxScorePhoneNum[n]);
        }

    }

}
