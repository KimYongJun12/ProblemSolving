package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11505 {
    static long[] num;
    static long[] tree;
    static long mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        num = new long[n + 1];
        for (int i = 1; i <= n; i++) num[i] = Long.parseLong(br.readLine());

        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, exponent);
        tree = new long[size];

        init(1, n, 1);

        while (query-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            long second = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                num[first] = second;
                update(1, n, 1, first, second);
            } else {
                sb.append(getMultiple(1, n, 1, first, (int) second)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) return tree[node] = num[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1) % mod;
    }

    private static long update(int start, int end, int node, int idx, long value) {
        if (idx < start || idx > end) return tree[node];
        if (start == end) return tree[node] = value;

        int mid = (start + end) / 2;
        return tree[node] = (update(start, mid, node * 2, idx, value) * update(mid + 1, end, node * 2 + 1, idx, value)) % mod;

    }

    private static long getMultiple(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return (getMultiple(start, mid, node * 2, left, right) * getMultiple(mid + 1, end, node * 2 + 1, left, right)) % mod;

    }

}
