package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12969 {
    static int N, K;
    static boolean[][][][] dp; // dp[문자열의 길이]['A'의 개수]['B'의 개수][만족하는 순서쌍의 개수]
    static char[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new boolean[N + 1][N + 1][N + 1][450];
        ans = new char[N];

        if (solve(0, 0, 0, 0)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) sb.append(ans[i]);
            System.out.println(sb);
        } else System.out.println("-1");
    }

    private static boolean solve(int depth, int aNum, int bNum, int kNum) {
        if (depth == N) {
            if (kNum == K) return true;
            else return false;
        }

        // 같은 경우에 대해 이전에 메소드가 호출되었다면 중복 호출을 막기 위해 바로 false 반환
        if (dp[depth][aNum][bNum][kNum]) return false;
        dp[depth][aNum][bNum][kNum] = true;

        /**
         * 순서쌍의 개수 kNum은
         * ans[depth] = 'A'인 경우 'A'보다 작은 값이 없기 때문에 이전 값과 동일
         * ans[depth] = 'B'인 경우 'B'보다 작은 값인 'A'가 있기 때문에 이전 값 + 'A'의 개수
         * ans[depth] = 'C'인 경우 'C'보다 작은 값인 'A', 'B'가 있기 때문에 이전 값 + 'A'의 개수 + 'B'의 개수
         */
        ans[depth] = 'A';
        if (solve(depth + 1, aNum + 1, bNum, kNum)) return true;

        ans[depth] = 'B';
        if (solve(depth + 1, aNum, bNum + 1, kNum + aNum)) return true;

        ans[depth] = 'C';
        if (solve(depth + 1, aNum, bNum, kNum + aNum + bNum)) return true;

        return false;
    }
}
