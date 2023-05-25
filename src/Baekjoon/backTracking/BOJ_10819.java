package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819 {
    static int N, max = 0;
    static int[] num;
    static int[] diff;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        num = new int[N];
        diff = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0);
        System.out.println(max);
    }

    private static void backTracking(int depth) {
        if (depth == N) {
            int now = 0;
            for (int i = 0; i < N - 1; i++) {
                now += Math.abs(diff[i] - diff[i + 1]);
            }

            max = Math.max(max, now);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                diff[depth] = num[i];
                backTracking(depth + 1);
                visited[i] = false;
            }
        }
    }
}
