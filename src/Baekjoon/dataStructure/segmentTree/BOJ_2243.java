package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2243 {
    static int[] segTree;
    static int MAX_FLAVOR = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int exp = (int) Math.ceil(Math.log(MAX_FLAVOR) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exp);
        segTree = new int[treeSize];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                int idx = Integer.parseInt(st.nextToken());
                int ans = binarySearch(idx);
                update(1, MAX_FLAVOR, 1, ans, -1);
                sb.append(ans).append('\n');
            } else {
                int idx = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(1, MAX_FLAVOR, 1, idx, value);
            }
        }

        System.out.println(sb);
    }

    private static int binarySearch(int idx) {
        int lo = 1;
        int hi = MAX_FLAVOR;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int sum = getSum(1, MAX_FLAVOR, 1, 1, mid);

            if (sum < idx) {
                lo = mid + 1;
            } else hi = mid;
        }

        return lo;
    }

    private static int getSum(int start, int end, int node, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return segTree[node];

        int mid = (start + end) / 2;

        return getSum(start, mid, 2 * node, left, right) + getSum(mid + 1, end, 2 * node + 1, left, right);
    }

    private static void update(int start, int end, int node, int idx, int value) {
        if (idx < start || end < idx) return;

        segTree[node] += value;
        if (start == end) return;

        int mid = (start + end) / 2;

        update(start, mid, 2 * node, idx, value);
        update(mid + 1, end, 2 * node + 1, idx, value);
    }
}
