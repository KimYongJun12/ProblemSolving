package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
    static long[] num, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        num = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = Long.parseLong(br.readLine());
        }

        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exponent);
        tree = new long[treeSize];

        init(1, n, 1);

        int query = m + k;
        while (query-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int firstNum = Integer.parseInt(st.nextToken());
            long secondNum = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                long diff = secondNum - num[firstNum];
                num[firstNum] = secondNum;
                update(1, n, 1, firstNum, diff);
            } else {
                sb.append(prefixSum(1, n, 1, firstNum, (int) secondNum)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) return tree[node] = num[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static void update(int start, int end, int node, int index, long diff) {
        if (start > index || end < index) return;

        tree[node] += diff;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    private static long prefixSum(int start, int end, int node, int startSum, int endSum) {
        if (startSum > end || endSum < start) return 0;
        if (startSum <= start && end <= endSum) return tree[node];

        int mid = (start + end) / 2;
        return prefixSum(start, mid, node * 2, startSum, endSum) + prefixSum(mid + 1, end, node * 2 + 1, startSum, endSum);
    }
}
