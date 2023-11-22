package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
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
public class BOJ_13913 {
    static int N, K;
    static final int INF = 987654321;
    static int[] visited = new int[200001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(visited, INF);
        if (N == K) {
            System.out.printf("0\n%d", N);
        } else {
            int min = bfs();
            int idx = K;
            int dist = min - 2;
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            while (idx != N) {
                stack.push(idx);
                if (idx > 0 && visited[idx - 1] == dist) {
                    idx--;
                } else if ((idx & 1) == 0 && visited[idx >> 1] == dist) {
                    idx >>= 1;
                } else {
                    idx++;
                }
                dist--;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(min - 1).append('\n').append(N).append(' ');
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(' ');
            }

            System.out.println(sb);
        }
    }

    private static int bfs() {
        int cnt = 1;
        Queue<Integer> q = new ArrayDeque<>();
        visited[N] = 0;
        q.offer(N);
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int now = q.poll();
                if (now == K) return cnt;

                if (now != 0 && visited[now - 1] > cnt) {
                    q.offer(now - 1);
                    visited[now - 1] = cnt;
                }

                if (now > K) continue;

                if (visited[now + 1] > cnt) {
                    q.offer(now + 1);
                    visited[now + 1] = cnt;
                }

                if (visited[now << 1] > cnt) {
                    q.offer(now << 1);
                    visited[now << 1] = cnt;
                }
            }
            cnt++;
        }
        return cnt;
    }
}
