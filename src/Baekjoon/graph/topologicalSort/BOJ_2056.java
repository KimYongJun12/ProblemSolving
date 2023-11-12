package Baekjoon.graph.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:17
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 수행해야 할 작업 N개가 있음
 * 몇몇 작업은 순서가 있음
 * 선행 관계가 없는 작업은 동시에 수행 가능
 *
 * 구해야 하는 것
 * 모든 작업을 완료하기 위한 최소 시간을 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : N
 * 둘째 줄 ~ N개 줄 : i번 작업에 걸리는 시간, i번 작업의 선행 작업 개수, 선행 작업들 번호
 *
 * 제한 요소
 * 3 <= N <= 1000
 * 1 <= T[i] <= 100
 *
 * 생각나는 풀이
 * 위상 정렬
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_2056 {
    static int N;
    static int[] time;
    static int[] depth;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        depth = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            int m = Integer.parseInt(st.nextToken());
            depth[i] = m;
            if (m == 0) q.offer(i);
            for (int j = 0; j < m; j++) {
                int prev = Integer.parseInt(st.nextToken());
                graph[prev].add(i);
            }
        }

        System.out.println(topologicalSort());
    }

    private static int topologicalSort() {
        int[] total = time.clone();
        int max = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph[now]) {
                total[next] = Math.max(total[next], time[next] + total[now]);
                if (--depth[next] == 0) q.offer(next);
            }
        }

        for (int i = 1; i <= N; i++) max = Math.max(max, total[i]);

        return max;
    }
}
