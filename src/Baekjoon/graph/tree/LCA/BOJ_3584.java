package Baekjoon.graph.tree.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3584 {
    static int N;
    static ArrayList<Integer>[] tree;
    static int[] parent;
    static int[] depth;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            tree = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }
            parent = new int[N + 1];
            depth = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b] = a;
                tree[a].add(b);
            }

            for (int i = 1; i <= N; i++) {
                if (parent[i] == 0) {
                    dfs(i, 0);
                    break;
                }
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(findLCA(a, b)).append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int node, int d) {
        depth[node] = d;

        for (int child : tree[node]) {
            dfs(child, d + 1);
        }
    }

    private static int findLCA(int a, int b) {
        int aDepth = depth[a];
        int bDepth = depth[b];

        while (aDepth > bDepth) {
            a = parent[a];
            aDepth--;
        }

        while (aDepth < bDepth) {
            b = parent[b];
            bDepth--;
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
