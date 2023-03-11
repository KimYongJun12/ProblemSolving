package Baekjoon.graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2233 {
    static int n;
    static int[] parent;
    static int[] depth;
    static int[] match;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        depth = new int[n + 1];
        match = new int[2 * n + 1];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        String s = br.readLine();
        for (int i = 1, idx = 1; i <= 2 * n; i++) {
            if (s.charAt(i - 1) == '0') {
                parent[idx] = stack.peek();
                match[i] = idx;
                depth[idx] = stack.size();
                stack.push(idx++);
            } else {
                match[i] = stack.peek();
                stack.pop();
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        findLCA(match[x], match[y]);
    }

    private static void findLCA(int x, int y) {
        int xDepth = depth[x];
        int yDepth = depth[y];

        while (xDepth > yDepth) {
            x = parent[x];
            xDepth--;
        }

        while (xDepth < yDepth) {
            y = parent[y];
            yDepth--;
        }

        while (x != y) {
            x = parent[x];
            y = parent[y];
        }

        int[] ans = new int[2];

        for (int i = 1, cnt = 0; i <= 2 * n; i++) {
            if (match[i] == x) {
                ans[cnt++] = i;
            }
            if (cnt == 2) break;
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}