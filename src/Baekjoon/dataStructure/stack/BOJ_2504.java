package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        int ans = 0;
        int value = 1;

        for (int i = 0; i < input.length; i++) {
            char now = input[i];

            if (now == '(') {
                stack.push(now);
                value *= 2;
            } else if (now == '[') {
                stack.push(now);
                value *= 3;
            } else if (now == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    ans = 0;
                    break;
                } else if (input[i - 1] == '(') {
                    ans += value;
                }

                stack.pop();
                value /= 2;
            } else if (now == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    ans = 0;
                    break;
                } else if (input[i - 1] == '[') {
                    ans += value;
                }

                stack.pop();
                value /= 3;
            }
        }

        ans = (stack.isEmpty()) ? ans : 0;
        System.out.println(ans);
    }
}
