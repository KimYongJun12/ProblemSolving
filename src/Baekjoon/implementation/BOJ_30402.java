package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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
public class BOJ_30402 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashSet<Character> set = new HashSet<>();
        set.add('r');
        set.add('o');
        set.add('y');
        set.add('p');
        char c = ' ';

        fo : for (int i = 0; i < 15; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 15; j++) {
                char now = st.nextToken().charAt(0);
                if (!set.contains(now)) {
                    c = now;
                    break fo;
                }
            }
        }

        System.out.println(c == 'w' ? "chunbae" : c == 'b' ? "nabi" : "yeongcheol");
    }
}
