package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10868 {
    static int[] num;
    static int[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        num = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int exp = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exp);
        segTree = new int[treeSize];

        init(1, N, 1);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            sb.append(findMin(1, N, 1, a, b)).append('\n');
        }

        System.out.println(sb);
    }

    private static int findMin(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return segTree[node];

        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, 2 * node, left, right), findMin(mid + 1, end, 2 * node + 1, left, right));
    }

    private static int init(int start, int end, int node) {
        if (start == end) return segTree[node] = num[start];

        int mid = (start + end) / 2;
        return segTree[node] = Math.min(init(start, mid, 2 * node), init(mid + 1, end, 2 * node + 1));
    }
}