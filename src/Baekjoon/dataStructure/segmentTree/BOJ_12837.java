package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12837 {
    static long[] num;
    static long[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        int exp = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exp);
        segTree = new long[treeSize];

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                int p = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());

                update(1, N, 1, p, value);
            } else {
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());

                sb.append(moneyChange(1, N, 1, p, q)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long moneyChange(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return segTree[node];
        int mid = (start + end) / 2;
        return moneyChange(start, mid, 2 * node, left, right) + moneyChange(mid + 1, end, 2 * node + 1, left, right);

    }

    private static long update(int start, int end, int node, int idx, long value) {
        if (idx < start || end < idx) return segTree[node];
        if (start == end) return segTree[node] += value;

        int mid = (start + end) / 2;
        return segTree[node] = update(start, mid, 2 * node, idx, value) + update(mid + 1, end, 2 * node + 1, idx, value);
    }
}
