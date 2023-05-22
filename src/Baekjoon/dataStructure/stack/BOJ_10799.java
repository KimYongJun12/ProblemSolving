package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(input[0]);

        int ans = 0;

        for (int i = 1; i < input.length; i++) {
            char now = input[i];
            if (now == '(') {
                stack.push(now);
            } else {
                stack.pop();
                if (input[i - 1] == '(') {
                    ans += stack.size();
                } else {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
