package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1086 {
    static int n, k;
    static char[][] nums;
    static long[][] dp;
    static int[][] dpRest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new char[n][];
        for (int i = 0; i < n; i++) {
            nums[i] = br.readLine().toCharArray();
        }
        k = Integer.parseInt(br.readLine());
        dp = new long[k][1 << n];
        dpRest = new int[k][n];

        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(dpRest[i], -1);
        }

        long answerCnt = solve(0, 0);
        long factorial = getFactorial(n);

        if (answerCnt == 0) {
            factorial = 1;
        } else {
            long gcd = getGCD(answerCnt, factorial);
            factorial /= gcd;
            answerCnt /= gcd;
        }

        System.out.println(answerCnt + "/" + factorial);
    }

    /**
     * 정답의 개수를 세는 메소드
     * dp와 비트마스킹 이용 dp[나머지][이용한 인덱스] = 정답의 개수
     * dp[0][0]이 답(원소를 하나도 사용하지 않았을 때 나머지가 0인 경우의 수)
     */
    private static long solve(int rest, int flag) {
        if (dp[rest][flag] != -1) return dp[rest][flag];

        if (flag == (1 << n) - 1) {
            return dp[rest][flag] = (rest == 0) ? 1 : 0;
        }

        long sum = 0;   // sum = 현재 이용한 가짓수에서 나머지가 rest가 되게 하는 경우의 수
        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) == 0) {
                sum += solve(getRest(rest, i), flag | (1 << i));
            }
        }

        return dp[rest][flag] = sum;
    }

    /**
     * 나머지 구하는 메소드
     * 수 하나의 크기가 최대 10^50 - 1까지이므로 int나 long으로는 계산 불가
     * 나누는 값인 k < 100이므로 한 자리씩 모듈러 연산을 통해 나머지 계산 가능
     * dpRest[나머지][인덱스] = 나머지 + nums[인덱스]를 k로 나누었을 때의 나머지
     * ex) nums[3] = 98765, k = 30일 때 dp[21][3] = 2198765 % 30을 저장
     * 나머지도 메모이제이션하여 중복 제거
     */
    private static int getRest(int rest, int idx) {
        if (dpRest[rest][idx] != -1) return dpRest[rest][idx];
        int ret = rest;

        for (int i = 0; i < nums[idx].length; i++) {
            ret *= 10;
            ret += nums[idx][i] - '0';
            ret %= k;
        }

        return dpRest[rest][idx] = ret;
    }

    /**
     * n개를 선택하는 모든 경우의 수(정답에서 분모에 해당)를 구하는 메소드
     * nPn = n! / (n-n)! == n!
     */
    private static long getFactorial(int n) {
        if (n == 1) return 1;
        return n * getFactorial(n - 1);
    }

    /**
     * 최대공약수 구하는 메소드
     * 정답을 기약분수 형태로 출력해야 하기 때문에 분자, 분모를 최대공약수로 나누어준다
     */
    private static long getGCD(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

}