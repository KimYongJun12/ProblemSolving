package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 풀이 시작 : 8:46
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 크리보드의 기능
 * 1. 화면에 A 출력
 * 2. Ctrl-A : 화면 전체 선택
 * 3. Ctrl-C : 전체 선택한 내용을 버퍼에 복사
 * 4. Ctrl-V : 버퍼가 비지 않은 경우 화면에 출력된 문자열 바로 뒤에 버퍼 내용 붙임
 *
 * 구해야 하는 것
 * N번 눌러서 최대 출력 가능한 A의 개수
 *
 * 문제 입력
 * 첫째 줄 : N
 *
 * 제한 요소
 * 1 <= N <= 100
 *
 * 생각나는 풀이
 * bfs? 4^100이라 불가능할듯
 * dp?
 * 화면에 출력하는 경우
 * 1. A를 하나 찍음
 * 2. 전체선택 + 복사 + 붙여넣기함
 * 3. 클립보드에 이미 복사된 값이 있으면 붙여넣기함
 * 3번을 어떻게 처리해야 할까
 * dp[i-3]*2
 * dp[i-4]*3
 * dp[i-5]*4
 * dp[i] = max(dp[i - j]*(j-1), dp[i - 1] + 1)
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_11058 {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        Arrays.fill(dp, -1);
        dp[1] = 1L;
        System.out.println(memo(N));
    }

    private static long memo(int n) {
        if (n <= 0) return 0L;
        if (dp[n] != -1) return dp[n];
        dp[n] = memo(n - 1) + 1;

        for (int i = 3; i < n; i++) {
            // n - i번째 값에서 선택, 그 후부터 계속 그 값 복사
            dp[n] = Math.max(dp[n], memo(n - i) * (i - 1));
        }

        return dp[n];
    }
}
