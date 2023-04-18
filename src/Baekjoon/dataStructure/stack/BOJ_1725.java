package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] height = new int[N];
        for (int i = 0; i < N; i++) height[i] = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int H = height[stack.pop()];
                int W = (stack.isEmpty()) ? i : i - stack.peek() - 1;

                max = Math.max(max, H * W);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int H = height[stack.pop()];
            int W = (stack.isEmpty()) ? N : N - stack.peek() - 1;

            max = Math.max(max, H * W);
        }

        System.out.println(max);
    }
}
