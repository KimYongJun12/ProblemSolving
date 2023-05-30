package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_5397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String input = br.readLine();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();

            for (int i = 0; i < input.length(); i++) {
                char now = input.charAt(i);

                if (now == '<') {
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }
                } else if (now == '>') {
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                } else if (now == '-') {
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                } else {
                    left.push(now);
                }
            }

            while (!left.isEmpty()) {
                right.push(left.pop());
            }

            while (!right.isEmpty()) {
                sb.append(right.pop());
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}
