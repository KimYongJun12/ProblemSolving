package Baekjoon.graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 시작 :
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 *
 * 구해야 하는 것
 *
 * 문제 입력
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_4803 {
    static final int MAX_N = 500;
    static final String MANY_TREES = "A forest of %d trees.";
    static final String ONE_TREE = "There is one tree.";
    static final String NO_TREE = "No trees.";
    static int N, M;
    static boolean isTree;
    static ArrayList<Integer>[] graph = new ArrayList[MAX_N + 1];
    static boolean[] visited = new boolean[MAX_N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String input;

        for (int i = 1; i <= MAX_N; i++) {
            graph[i] = new ArrayList<>();
        }

        int idx = 1;
        while (!(input = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            init();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                isTree = true;
                if (visited[i]) continue;
                dfs(0, i);
                if (isTree) cnt++;
            }

            sb.append("Case ").append(idx++).append(": ");

            if (cnt == 0) sb.append(NO_TREE);
            else if (cnt == 1) sb.append(ONE_TREE);
            else sb.append(String.format(MANY_TREES, cnt));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int parent, int now) {
        if (!isTree) return;
        visited[now] = true;

        for (int next : graph[now]) {
            if (next == parent) continue;
            if (visited[next]) {
                isTree = false;
            } else {
                dfs(now, next);
            }
        }
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            graph[i].clear();
            visited[i] = false;
        }
    }
}
