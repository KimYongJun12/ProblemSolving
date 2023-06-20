package Baekjoon.dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        double[] value = new double[N];
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            value[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char now = input.charAt(i);
            if (now >= 'A') {
                stack.push(value[now - 'A']);
            } else {
                double a = stack.pop();
                double b = stack.pop();
                double cal;
                if (now == '+') cal = b + a;
                else if (now == '-') cal = b - a;
                else if (now == '*') cal = b * a;
                else cal = b / a;

                stack.push(cal);
            }
        }

        System.out.printf("%.2f%n", stack.pop());
    }
}
