package Baekjoon.prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:46
 * 풀이 완료 :
 * 풀이 시간 :
 * <p>
 * 문제 해석
 * M * N 크기 보드에서 K * K만큼 잘라서 체스판을 만들려고 할 때
 * 몇 개의 칸을 칠해야 체스판 모양이 되는가?
 *
 * 구해야 하는 것
 * K * K칸의 체스판을 만드는 데 색칠해야 하는 칸의 최소 개수
 *
 * 문제 입력
 * 첫째 줄 : N, M, K
 * 둘째 줄 ~ N개 줄 : 보드의 상태
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 */
public class BOJ_25682 {
    static int N, M, K;
    static int[][] board; // board[i][j] = (1, 1) ~ (i, j)까지 색칠해야 하는 칸 개수
    static char[] col = {'B', 'W'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];

        for (int i = 1, c = 0; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                char color = input.charAt(j - 1);
                if (color != col[c]) {
                    board[i][j] = 1;
                }
                c = 1 - c;
                board[i][j] += board[i - 1][j] + board[i][j - 1] - board[i - 1][j - 1];
            }
            if ((M & 1) == 0) c = 1 - c;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                int nowChangeValue = board[i][j] - (board[i - K][j] + board[i][j - K]) + board[i - K][j - K];
                min = Math.min(min, nowChangeValue);
                max = Math.max(max, nowChangeValue);
            }
        }

        System.out.println(Math.min(K * K - max, min));
    }
}
