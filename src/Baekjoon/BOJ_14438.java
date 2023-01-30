package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14438 {
    static int[] tree;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, exponent);
        tree = new int[size];
        init(1, n, 1);
        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                update(1, n, 1, first, second);
            } else {
                sb.append(findMin(1, n, 1, first, second)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return tree[node];
        if (start == end) return tree[node] = value;

        int mid = (start + end) / 2;
        return tree[node] = Math.min(update(start, mid, node * 2, index, value),
                update(mid + 1, end, node * 2 + 1, index, value));
    }

    private static int findMin(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = num[start];

        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }

}