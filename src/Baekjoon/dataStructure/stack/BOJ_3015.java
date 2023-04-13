package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_3015 {
    static class Pair {
        int cnt, value;

        public Pair(int cnt, int value) {
            this.cnt = cnt;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long ans = 0;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());
            int cnt = 1; // 자신을 포함하여 자신보다 앞에 있는 사람 중 키가 같은 사람의 수

            while (!stack.isEmpty() && stack.peek().value <= now) {
                Pair pop = stack.pop();
                ans += pop.cnt; // 키가 같은 사람들의 수만큼 값 더함
                if (pop.value == now) cnt += pop.cnt; // 자신보다 앞에 있는 사람 중 키가 같은 사람의 수를 더함
            }

            if (!stack.isEmpty()) ans++; // stack에서 자신보다 큰 첫번째 사람
            stack.push(new Pair(cnt, now));
        }

        System.out.println(ans);
    }
}
