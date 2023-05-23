package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            left.push(s.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            String cmd = br.readLine();
            char c = cmd.charAt(0);
            if (c == 'L') {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }
            } else if (c == 'D') {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
            } else if (c == 'B') {
                if (!left.isEmpty()) {
                    left.pop();
                }
            } else {
                char alphabet = cmd.charAt(2);
                left.push(alphabet);
            }

        }

        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty()) {
            right.push(left.pop());
        }

        while (!right.isEmpty()) {
            sb.append(right.pop());
        }

        System.out.println(sb);
    }
}
