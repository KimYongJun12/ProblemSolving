package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 7:14
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 높이가 1 ~ N인 막대가 일렬로 배치
 * 막대를 왼쪽이나 오른쪽에서 보면 큰 막대가 작은 막대를 가림
 * 막대의 개수 N과 왼쪽, 오른쪽에서 보이는 막대의 개수 L, R이 주어졌을 때
 * 이러한 결과를 만드는 배치의 개수
 *
 * 구해야 하는 것
 * 배치의 개수
 *
 * 문제 입력
 * 첫째 줄 : tc 개수 t
 * 테케당 입력
 * 첫째 줄 : N, L, R이 공백을 두고 주어짐
 *
 * 제한 요소
 * 1 <= L, R <= N <= 20
 * 20! = 2,432,902,008,176,640,000 < Long.MAX_VALUE
 *
 * 생각나는 풀이
 * 조합의 응용일 것 같음 => 근데 20!씩 계산하는건 말이 안됨
 * 왼쪽에서 보이는 막대의 개수 = 왼쪽 LIS의 길이
 * 오른쪽에서 보이는 막대의 개수 = 오른쪽 LIS의 길이
 * 모든 경우에 대해 LIS를 구할 수 없음
 * 뭔가 수학적 접근이 필요할듯
 * 블록 1개부터 접근
 * 1개
 *  1
 *  l = 1, r = 1
 * 2개
 *  1-2
 *      l = 2, r = 1
 *  2-1
 *      l = 1, r = 2
 * 3개
 *  1-2-3
 *      l = 3, r = 1
 *  1-3-2
 *      l = 2, r = 2
 *  3-1-2
 *      l = 1, r = 3
 *  2-1-3
 *      l = 2, r = 1
 *  2-3-1
 *      l = 2, r = 2
 *  3-2-1
 *      l = 1, r = 3
 *
 * dp[N][l][r]로 풀어야 하지 않을까
 * 가장 큰 블록을 기존 블록 조합에 배치하는 방법은 제일 처음 ~ 제일 마지막까지 N번
 * => 나머지 블록을 모두 세웠다 가정하고 가장 작은 블록을 배치하는 방법으로 생각해야 하는 거 같음...
 *
 * 2 ~ N까지는 모두 놓여 있고, 마지막으로 높이 1짜리 블록을 놓는 경우
 *  1. 가장 왼쪽에 놓는 경우 -> 1가지
 *      dp[N][L][R] += dp[N-1][L-1][R] (N - 1개의 건물을 놓는 경우의 수에서 L값만 +1되는 거)
 *  2. 가장 오른쪽에 놓는 경우 -> 1가지
 *      dp[N][L][R] += dp[N - 1][L][R - 1] (N - 1개의 건물을 놓는 경우의 수에서 R값만 +1 되는 거)
 *  3. 중간에 놓는 경우 -> 양 끝에 놓는 경우를 제외한 모든 경우 -> (N - 2)가지
 *      dp[N][L][R] += dp[N - 1][L][R] (N - 1개의 건물을 놓는 경우의 수에서 L, R값 변화 없음)
 * => 즉 dp[N][L][R] = dp[N - 1][L - 1][R] + dp[N - 1][L][R - 1] + dp[N - 1][L][R] * (N - 2)
 * 구현해야 하는 기능
 *
 */
public class BOJ_8895_막대배치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        long[][][] dp = new long[21][21][21];
        dp[1][1][1] = 1L;
        for (int i = 2; i <= 20; i++) {
            for (int l = 1; l <= 20; l++) {
                for (int r = 1; r <= 20; r++) {
                    dp[i][l][r] = dp[i - 1][l - 1][r] + dp[i - 1][l][r - 1] + (i - 2) * dp[i - 1][l][r];
                }
            }
        }

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            sb.append(dp[N][L][R]).append('\n');
        }

        System.out.println(sb);
    }
}
