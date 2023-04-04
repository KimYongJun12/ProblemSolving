package Baekjoon.dataStructure.disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17398 {
    static int[] parent;
    static long[] groupSize;
    static Connection[] connects;

    static class Connection {
        int a, b;

        public Connection(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        groupSize = new long[N + 1];
        Arrays.fill(groupSize, 1);
        connects = new Connection[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            connects[i] = new Connection(a, b);
        }

        boolean[] cutLine = new boolean[M + 1];
        Stack<Integer> stack = new Stack<>();
        while (Q-- > 0) {
            int idx = Integer.parseInt(br.readLine());
            cutLine[idx] = true;
            stack.push(idx);
        }

        for (int i = 1; i <= M; i++) {
            if (cutLine[i]) continue;
            int a = connects[i].a;
            int b = connects[i].b;

            union(a, b);
        }

        long ans = 0L;

        while (!stack.isEmpty()) {
            Connection now = connects[stack.pop()];
            int a = find(now.a);
            int b = find(now.b);
            if (a != b) ans += groupSize[a] * groupSize[b];

            union(a, b);
        }

        System.out.println(ans);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (a != b) {
            groupSize[a] += groupSize[b];
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

}
