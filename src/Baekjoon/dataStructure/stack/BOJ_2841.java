package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
public class BOJ_2841 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer>[] stacks = new ArrayDeque[7];
        for (int i = 1; i <= 6; i++) {
            stacks[i] = new ArrayDeque<>();
        }

        int cnt = 0;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> stack = stacks[line];
            while (!stack.isEmpty() && stack.peek() > fret) {
                cnt++;
                stack.pop();
            }

            if (stack.isEmpty() || stack.peek() != fret) {
                cnt++;
                stack.push(fret);
            }
        }

        System.out.println(cnt);
    }
}
