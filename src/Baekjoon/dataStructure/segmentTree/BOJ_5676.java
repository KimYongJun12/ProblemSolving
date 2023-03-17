package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5676 {
    static int[] num;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String s = "";

        while ((s = br.readLine()) != null) {
            st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            num = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int now = Integer.parseInt(st.nextToken());
                now = (now == 0) ? 0 : (now > 0) ? 1 : -1;
                num[i] = now;
            }

            int exp = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int treeSize = (int) Math.pow(2, exp);
            tree = new int[treeSize];

            init(1, n, 1);

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd == 'C') {
                    b = (b == 0) ? 0 : (b > 0) ? 1 : -1;
                    if (num[a] == b) continue;
                    update(1, n, 1, a, b);
                } else {
                    int ans = multiply(1, n, 1, a, b);
                    char ch = (ans == 0) ? '0' : (ans > 0) ? '+' : '-';
                    sb.append(ch);
                }
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = num[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, 2 * node) * init(mid + 1, end, 2 * node + 1);
    }

    private static int update(int start, int end, int node, int idx, int value) {
        if (start > idx || end < idx) return tree[node];
        if (start == end) return tree[node] = num[idx] = value;

        int mid = (start + end) / 2;
        return tree[node] = update(start, mid, 2 * node, idx, value) * update(mid + 1, end, 2 * node + 1, idx, value);
    }

    private static int multiply(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return multiply(start, mid, 2 * node, left, right) * multiply(mid + 1, end, 2 * node + 1, left, right);
    }

}
