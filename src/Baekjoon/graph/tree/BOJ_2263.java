package Baekjoon.graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2263 {
    static int n;
    static int[] inOrder;
    static int[] postOrder;
    static int[] preOrder;
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];
        preOrder = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) inOrder[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) postOrder[i] = Integer.parseInt(st.nextToken());

        addPreOrder(0, n - 1, 0, n - 1);
        for (int i = 0; i < n; i++) sb.append(preOrder[i]).append(' ');
        System.out.println(sb);
    }

    private static void addPreOrder(int inS, int inE, int poS, int poE) {
        if (inS > inE) return;

        preOrder[idx++] = postOrder[poE];
        int root = inS;
        for (; root <= inE; root++) {
            if (inOrder[root] == postOrder[poE]) break;
        }

        addPreOrder(inS, root - 1, poS, poS + root - inS - 1);
        addPreOrder(root + 1, inE, poS + root - inS, poE - 1);
    }

}