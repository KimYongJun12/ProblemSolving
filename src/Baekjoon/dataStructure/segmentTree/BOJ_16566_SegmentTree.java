package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_16566_SegmentTree {
    static int n;
    static int[] tree;
    static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exponent);
        tree = new int[treeSize];
        set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        init(1, n, 1);

        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {
            int nowNum = Integer.parseInt(st.nextToken());
            sb.append(solve(nowNum + 1)).append('\n');
        }

        System.out.println(sb);
    }

    private static int solve(int start) {
        int lo = start;
        int hi = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (sum(1, n, 1, start, mid) > 0) {
                hi = mid - 1;
            } else lo = mid + 1;
        }

        update(1, n, 1, lo);
        return lo;
    }

    private static int sum(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int idx) {
        if (idx < start || end < idx) return;
        tree[node]--;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx);
        update(mid + 1, end, node * 2 + 1, idx);
    }


    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = (set.contains(start)) ? 1 : 0;
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }
}