package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1053 {
    static int n;
    static char[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        n = input.length;
        int min = findMin();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (input[i] == input[j]) continue;
                swap(i, j);
                min = Math.min(min, findMin() + 1);
                swap(i, j);
            }
        }

        System.out.println(min);
    }

    private static int findMin() {
        int[][] dp = new int[n][n];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j + i < n; j++) {
                int k = (input[j] == input[i + j]) ? 0 : 1;
                dp[j][i + j] = Math.min(Math.min(dp[j + 1][i + j], dp[j][i + j - 1]) + 1, dp[j + 1][i + j - 1] + k);
            }
        }

        return dp[0][n - 1];
    }

    private static void swap(int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
