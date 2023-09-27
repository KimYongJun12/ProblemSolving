package Baekjoon.dataStructure.sparseTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 7:53
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 트리가 주어짐
 * 트리에서 두 노드의 LCA를 구해야 함
 *
 * 구해야 하는 것
 * 트리에서 노드 쌍이 주어졌을 때 그 노드 쌍의 LCA를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : 노드 개수 N
 * 둘째 줄 ~ N - 1개 줄 : 노드를 잇는 간선
 * 다음 줄 : 쿼리 개수 M
 * 다음 줄 ~ M개 줄 : LCA를 구해야 할 노드 쌍
 *
 * 제한 요소
 * 1 <= N <= 100000
 * 1 <= M <= 100000
 * 루트 = 1
 *
 * 생각나는 풀이
 * 트리가 10만, 쿼리가 10만
 * 최악 상황 = 트리가 노드 10만개의 편향트리이고 루트와 리프를 물어보는 쿼리만 10만번 들어올 때
 * LCA, sparse table을 이용해서 시간을 줄여야 함
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 트리 구현
 * 2. 루트부터 dfs탐색해서 트리의 높이를 구함, sparse table의 인덱스 0 (2^0)에 자신의 부모 노드를 저장
 * 3. sparse table을 채움
 * 4. 쿼리 수행
 */
public class BOJ_11438 {
    static int N, logN;
    static int[][] table;
    static int[] depth;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        logN = (int) Math.ceil(Math.log(N) / Math.log(2));
        table = new int[logN + 1][N + 1]; // table[k][i] = i번 노드의 2^k번 건너뛴 부모 노드

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(0, 1, 1);

        fillTable();

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findLCA(a, b)).append('\n');
        }

        System.out.println(sb);
    }

    private static int findLCA(int a, int b) {
        if (a == b) return a;
        if (a == 1 || b == 1) return 1;
        int depthA = depth[a];
        int depthB = depth[b];

        int idx = logN;
        while (depthA > depthB) {
            if (depthA - depthB >= (1 << idx)) {
                depthA -= (1 << idx);
                a = table[idx][a];
            }
            idx--;
        }
        idx = logN;
        while (depthA < depthB) {
            if (depthB - depthA >= (1 << idx)) {
                depthB -= (1 << idx);
                b = table[idx][b];
            }
            idx--;
        }

        idx = logN;
        fo : while (table[0][a] != table[0][b]) {
            for (; idx >= 0; idx--) {
                if (table[idx][a] == table[idx][b]) continue;
                a = table[idx][a];
                b = table[idx][b];
                continue fo;
            }
        }
        return a == b ? a : table[0][a];
    }

    private static void fillTable() {
        for (int i = 0; i < logN; i++) {
            for (int j = 1; j <= N; j++) {
                int nextIdx = table[i][j];
                table[i + 1][j] = table[i][nextIdx];
            }
        }
    }

    private static void dfs(int parent, int now, int dep) {
        depth[now] = dep;
        for (int child : tree[now]) {
            if (child == parent) continue;
            table[0][child] = now;
            dfs(now, child, dep + 1);
        }
    }
}
