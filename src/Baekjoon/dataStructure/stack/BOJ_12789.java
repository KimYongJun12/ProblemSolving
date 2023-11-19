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
public class BOJ_12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean isNice = true;

        StringTokenizer st = new StringTokenizer(br.readLine());
        fo : for (int i = 1; i <= N; i++) {
            if (!stack.isEmpty() && stack.peek() == i) {
                stack.pop();
            } else {
                while (st.hasMoreTokens()) {
                    int now = Integer.parseInt(st.nextToken());
                    if (i == now) {
                        continue fo;
                    } else {
                        stack.push(now);
                    }
                }
                isNice = false;
                break;
            }
        }
        System.out.println(isNice ? "Nice" : "Sad");
    }
}
