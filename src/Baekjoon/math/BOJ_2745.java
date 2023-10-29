package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 :
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 *
 * 구해야 하는 것
 *
 * 문제 입력
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        int b = Integer.parseInt(st.nextToken());
        int length = s.length();
        int ans = 0;
        for (int i = 0, num = 1; i < length; i++) {
            int now = s.charAt(length - 1 - i);
            if (now >= 'A') now -= 'A' - 10;
            else now -= '0';
            ans += num * now;
            num *= b;
        }

        System.out.println(ans);
    }
}
