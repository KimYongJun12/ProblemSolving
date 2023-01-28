package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2357 {
    static int[] minTree, maxTree, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exponent);
        minTree = new int[treeSize];
        maxTree = new int[treeSize];

        initMin(1, n, 1);
        initMax(1, n, 1);

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(findMin(1, n, 1, left, right)).append(' ').append(findMax(1, n, 1, left, right)).append('\n');
        }

        System.out.println(sb);
    }

    private static int findMin(int start, int end, int node, int left, int right) {
        if (right < start || left > end) return Integer.MAX_VALUE;
        if (left <= start && right >= end) return minTree[node];

        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
    }

    private static int findMax(int start, int end, int node, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return maxTree[node];

        int mid = (start + end) / 2;
        return Math.max(findMax(start, mid, node * 2, left, right), findMax(mid + 1, end, node * 2 + 1, left, right));
    }

    private static int initMin(int start, int end, int node) {
        if (start == end) return minTree[node] = num[start];

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
    }

    private static int initMax(int start, int end, int node) {
        if (start == end) return maxTree[node] = num[start];
        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, node * 2 + 1));
    }

}