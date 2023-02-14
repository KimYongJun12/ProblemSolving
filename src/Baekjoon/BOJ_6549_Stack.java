package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_6549_Stack {
    static int n;
    static long[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String s;

        while (!(s = br.readLine()).equals("0")) {
            Stack<Integer> stack = new Stack<>();
            st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            num = new long[n];
            for (int i = 0; i < n; i++) num[i] = Long.parseLong(st.nextToken());
            long maxArea = 0;

            for (int i = 0; i < n; i++) {
                /*
                 이전 높이보다 현재 높이가 작거나 같은 경우
                 현재 높이보다 작은 이전 높이들을 pop하면서 넓이를 구한다
                 */
                while ((!stack.isEmpty()) && num[stack.peek()] >= num[i]) {
                    long height = num[stack.pop()];
                    /*
                    다음으로 pop 하는 인덱스의 높이는 현재 pop한 높이보다 반드시 낮으므로
                    너비는 i부터 다음 pop 할 인덱스 전까지가 된다
                    스택에 값이 없다면 1 ~ i이므로 i가 된다
                     */
                    long weight = (stack.isEmpty()) ? i : (i - stack.peek() - 1);
                    maxArea = Math.max(maxArea, height * weight);
                }
                stack.push(i);
            }

            /*
            스택에 값이 남은 경우가 있으므로 위의 과정을 한 번 더 수행한다
             */
            while (!stack.isEmpty()) {
                long height = num[stack.pop()];
                long weight = (stack.isEmpty()) ? n : (n - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * weight);
            }

            sb.append(maxArea).append('\n');
        }

        System.out.println(sb);
    }

}
