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
public class BOJ_28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                stack.push(Integer.parseInt(st.nextToken()));
                continue;
            } else if (cmd == 2) {
                sb.append(stack.isEmpty() ? -1 : stack.pop());
            } else if (cmd == 3) {
                sb.append(stack.size());
            } else if (cmd == 4) {
                sb.append(stack.isEmpty() ? 1 : 0);
            } else {
                sb.append(stack.isEmpty() ? -1 : stack.peek());
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
