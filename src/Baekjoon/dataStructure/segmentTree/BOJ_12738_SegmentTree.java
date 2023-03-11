package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_12738_SegmentTree {
    static ArrayList<Pair> list = new ArrayList<>();
    static int[] tree;

    static class Pair implements Comparable<Pair> {
        int idx, value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.value == o.value) {
                return o.idx - this.idx;
            }
            return this.value - o.value;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            list.add(new Pair(i, Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        int treeSize = (int) Math.pow(2, (int) (Math.ceil(Math.log(n) / Math.log(2))) + 1);
        tree = new int[treeSize];

        for (int i = 0; i < n; i++) {
            Pair p = list.get(i);
            int idx = p.idx;
            int maxValue = 0;
            if (idx != 1) {
                maxValue = findMaxValue(1, n, 1, 1, idx - 1);
            }

            update(1, n, 1, idx, maxValue + 1);
        }

        System.out.println(tree[1]);
    }

    private static int findMaxValue(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return Math.max(findMaxValue(start, mid, node * 2, left, right), findMaxValue(mid + 1, end, node * 2 + 1, left, right));

    }

    private static int update(int start, int end, int node, int idx, int value) {
        if (idx < start || end < idx) return 0;
        if (start == end) return tree[node] = value;

        int mid = (start + end) / 2;
        return tree[node] = Math.max(tree[node], Math.max(update(start, mid, node * 2, idx, value), update(mid + 1, end, node * 2 + 1, idx, value)));
    }

}