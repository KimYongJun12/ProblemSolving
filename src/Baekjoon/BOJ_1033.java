package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1033 {
    static int n;
    static long sum;
    static ArrayList<Node>[] graph;
    static long[] ans;
    static boolean[] visited;

    static class Node {
        int p, q, end;

        public Node(int end, int p, int q) {
            this.end = end;
            this.p = p;
            this.q = q;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n];
        ans = new long[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        sum = 1;

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            long div = gcd(p, q);

            sum *= p * q / div;

            graph[start].add(new Node(end, p, q));
            graph[end].add(new Node(start, q, p));
        }

        ans[0] = sum;

        for (int i = 0; i < graph[0].size(); i++) {
            dfs(0, graph[0].get(i));
        }

        for (int i = 0; i < n; i++) sum = gcd(sum, ans[i]);
        for (int i = 0; i < n; i++) ans[i] /= sum;

        for (long val : ans) System.out.print(val + " ");
    }

    private static void dfs(int parent, Node now) {
        visited[parent] = true;
        ans[now.end] = ans[parent] / now.p * now.q;

        for (Node next : graph[now.end]) {
            if (!visited[next.end]) {
                dfs(now.end, next);
            }
        }
    }

    private static long gcd(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }


}
