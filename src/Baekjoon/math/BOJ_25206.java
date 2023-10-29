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
public class BOJ_25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double ans = 0;
        double sum = 0;
        StringTokenizer st;
        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            double nowCost = Double.parseDouble(st.nextToken());
            String value = st.nextToken();
            if (value.equals("P")) continue;
            sum += nowCost;
            if (value.equals("F")) continue;
            char grade = value.charAt(0);
            char plus = value.charAt(1);
            double nowScore = plus == '+' ? 0.5 : 0;

            switch (grade) {
                case 'A' :
                    nowScore += 4.0;
                    break;
                case 'B' :
                    nowScore += 3.0;
                    break;
                case 'C' :
                    nowScore += 2.0;
                    break;
                case 'D' :
                    nowScore += 1.0;
                    break;
            }

            ans += nowScore * nowCost;
        }

        System.out.println(ans / sum);
    }
}
