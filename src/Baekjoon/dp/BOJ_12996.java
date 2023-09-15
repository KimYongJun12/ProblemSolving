package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:52
 * 풀이 완료 : 9:22
 * 풀이 시간 : 30분
 *
 * 문제 해석
 * 앨범에 곡이 S곡 수록됨
 * 가수 3명
 * 각각의 곡은 한 명 이상이 불러야 함
 * 세 사람이 녹음해야 하는 곡 수가 주어졌을 때 앨범을 만들 수 있는 방법의 수를 구해야 함
 *
 * 구해야 하는 것
 * 세 사람이 녹음해야 하는 곡 수가 주어졌을 때 앨범을 만들 수 있는 방법의 수를 구해야 함 (모듈러 연산)
 *
 * 문제 입력
 * 첫째 줄 : S, 각 가수가 불러야 하는 곡 수 N[i]
 *
 * 제한 요소
 * 1 <= S <= 50
 * 1 <= N[i] <= S
 *
 * 생각나는 풀이
 * DP
 * 한 개의 곡을 부르는 경우
 *  - 1 0 0, 0 1 0, 0 0 1
 *
 * 두 개의 곡을 부르는 경우의 수
 * 12 0 0, 0 12 0, 0 0 12, 1 2 0, 1 0 2, 0 1 2, 2 1 0, 2 0 1, 0 2 1
 *
 * 각 가수가 부를 수 있는 곡의 수를 A, B, C라고 하면
 * 1. A + B + C >= 50이어야 함
 * 2. i번 곡을 부르는 경우 = (i - 1)곡 부르는 경우 + 추가로 한 곡 부르는 경우
 * dp[a][b][c][i]  =
 * dp[a - 1][b][c][i - 1] + dp[a][b - 1][c][i - 1] + dp[a][b][c - 1][i - 1]
 * + dp[a - 1][b - 1][c][i - 1] + dp[a - 1][b][c - 1][i - 1]
 * + dp[a][b - 1][c - 1][i] + dp[a - 1][b - 1][c - 1][i - 1]
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_12996 {
    static final int MOD = 1_000_000_007;
    static int N, A, B, C;
    static int[][][][] dp;
    static int[] da = {-1, 0, 0, -1, -1, 0, -1};
    static int[] db = {0, -1, 0, 0, -1, -1, -1};
    static int[] dc = {0, 0, -1, -1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dp = new int[A + 1][B + 1][C + 1][N + 1];
        for (int i = 0; i <= A; i++) {
            for (int j = 0; j <= B; j++) {
                for (int k = 0; k <= C; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        dp[1][0][0][1] = dp[0][1][0][1] = dp[0][0][1][1] = dp[1][0][1][1] = dp[1][1][0][1] = dp[0][1][1][1] = dp[1][1][1][1] = 1;
        System.out.println(memo(A, B, C, N));
    }

    private static int memo(int a, int b, int c, int n) {
        if (a == -1 || b == -1 || c == -1 || n == -1) return 0;
        if (dp[a][b][c][n] != -1) return dp[a][b][c][n];
        dp[a][b][c][n] = 0;

        for (int i = 0; i < 7; i++) {
            dp[a][b][c][n] += memo(a + da[i], b + db[i], c + dc[i], n - 1);
            dp[a][b][c][n] %= MOD;
        }

        return dp[a][b][c][n] % MOD;
    }
}
