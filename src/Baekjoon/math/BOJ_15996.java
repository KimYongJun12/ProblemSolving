package Baekjoon.math;
/*
N = 20, A = 2인 경우
N!에서 2의 배수의 개수 = 20 / 2 = 10개
N!에서 2^2의 배수의 개수 = 20 / 2^2 = 5개
N!에서 2^3의 배수의 개수 = 20 / 2^3 = 2개
N!에서 2^4의 배수의 개수 = 20 / 2^4 = 1개
2의 총 개수는 10 + 5 + 2 + 1 = 18개
즉 2^18까지는 가능하다
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15996 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());

        int cnt = 0;
        while (N >= A) {
            cnt += N / A;
            N /= A;
        }

        System.out.println(cnt);
    }
}
