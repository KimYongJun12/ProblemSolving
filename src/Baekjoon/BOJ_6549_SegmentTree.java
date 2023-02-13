package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6549_SegmentTree {
    static int n;
    static long[] num;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String s;

        while (!(s = br.readLine()).equals("0")) {
            st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            num = new long[n + 1];
            for (int i = 1; i <= n; i++) num[i] = Long.parseLong(st.nextToken());

            int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int treeSize = (int) Math.pow(2, exponent);
            tree = new int[treeSize];

            init(1, n, 1);
            sb.append(findMaxArea(1, n)).append('\n');
        }

        System.out.println(sb);
    }

    private static long findMaxArea(int start, int end) {
        int mid = findMinHeight(1, n, 1, start, end);
        long area = (end - start + 1) * num[mid];

        if (start <= mid - 1) {
            long temp = findMaxArea(start, mid - 1);
            area = Math.max(area, temp);
        }

        if (end >= mid + 1) {
            long temp = findMaxArea(mid + 1, end);
            area = Math.max(area, temp);
        }

        return area;
    }

    private static int findMinHeight(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return -1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int mLeft = findMinHeight(start, mid, node * 2, left, right);
        int mRight = findMinHeight(mid + 1, end, node * 2 + 1, left, right);

        if (mLeft == -1) return mRight;
        if (mRight == -1) return mLeft;
        return (num[mLeft] > num[mRight]) ? mRight : mLeft;
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = start;

        int mid = (start + end) / 2;

        int left = init(start, mid, node * 2);
        int right = init(mid + 1, end, node * 2 + 1);

        return tree[node] = (num[left] > num[right]) ? right : left;
    }

}