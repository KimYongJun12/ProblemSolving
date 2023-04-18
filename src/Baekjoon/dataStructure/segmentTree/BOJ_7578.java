package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_7578 {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int exp = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exp);
        tree = new long[treeSize];

        int[] A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> BIdx = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(st.nextToken());
            BIdx.put(value, i);
        }

        long ans = 0;

        for (int i = 1; i <= N; i++) {
            int idx = BIdx.get(A[i]);
            update(1, N, 1, idx);
            ans += sum(1, N, 1, idx + 1, N);
        }

        System.out.println(ans);
    }

    private static void update(int start, int end, int node, int idx) {
        if (idx < start || end < idx) return;

        tree[node] += 1;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, 2 * node, idx);
        update(mid + 1, end, 2 * node + 1, idx);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 0L;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, 2 * node, left, right) + sum(mid + 1, end, 2 * node + 1, left, right);
    }
}
