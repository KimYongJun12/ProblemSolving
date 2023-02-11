package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2268 {
    static long[] num;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        num = new long[n + 1];

        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exponent);
        tree = new long[treeSize];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            long second = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                num[first] = second;
                update(1, n, 1, first, second);
            } else {
                int ssecond = (int) second;
                if (first > ssecond) {
                    int temp = first;
                    first = ssecond;
                    ssecond = temp;
                }

                sb.append(sum(1, n, 1, first, ssecond)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static long update(int start, int end, int node, int idx, long value) {
        if (idx < start || end < idx) return tree[node];

        if (start == end) return tree[node] = value;

        int mid = (start + end) / 2;
        return tree[node] = update(start, mid, node * 2, idx, value) + update(mid + 1, end, node * 2 + 1, idx, value);
    }
}