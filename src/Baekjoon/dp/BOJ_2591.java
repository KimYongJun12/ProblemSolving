package Baekjoon.dp;
/*
입력 : 길이 40 이하의 숫자로 이루어진 문자열
구해야 할 것 : 해당 문자열을 만들 수 있는 카드 조합의 수
제한 : 카드의 범위는 1 ~ 34, 각 카드는 중복 사용 가능
생각나는 풀이 : dp를 통해 1자리를 만드는 경우의 수, 2자리를 만드는 경우의 수, ... N자리를 만드는 경우의 수 (2차원 dp[시작인덱스][끝인덱스])로 확장 (x)
수정된 풀이 : i번째 자리와 그 앞 자리의 값에 따라 dp[k]의 값 결정
전략
k번째 수를 추가할 때
1. k - 1번째 이후 1장 추가
2. k - 2번째 이후 앞의 한장과 합쳐 2자리 수 추가
점화식 : dp[k] = dp[k - 1] + dp[k - 2]
예외사항 :
    k번째 수가 0인 경우 -> 1번 방법 사용 x
    k-1번째 수가 0인 경우 -> 2번 방법 사용 x
    k-1번째 수와 현재 수를 합친 수가 34 초과일 경우 -> 2번 방법 사용 x
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2591 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] number = br.readLine().toCharArray();
        int N = number.length;
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            if (number[i - 1] != '0') {
                dp[i] += dp[i - 1];
            }

            if (i == 1) continue;
            if (isDoubleDigitNumber(number[i - 2], number[i - 1])) dp[i] += dp[i - 2];
        }

        System.out.println(dp[N]);
    }

    private static boolean isDoubleDigitNumber(char ten, char one) {
        return ten == '1' || ten == '2' || (ten == '3' && one <= '4');
    }
}
