package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3665 {
    static int[] inDegree;
    static int[] lastRank;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            inDegree = new int[n + 1];
            lastRank = new int[n + 1];
            list = new ArrayList[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                lastRank[i] = Integer.parseInt(st.nextToken());
                list[i] = new ArrayList<>();
            }

            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    list[lastRank[i]].add(lastRank[j]);
                    inDegree[lastRank[j]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                swap(first, second);
            }

            Queue<Integer> q = new LinkedList<>();
            StringBuilder sb2 = new StringBuilder();
            boolean isCorrect = true;

            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) q.offer(i);
            }

            for (int i = 0; i < n; i++) {
                if (q.size() == 0) {
                    sb.append("IMPOSSIBLE");
                    isCorrect = false;
                    break;
                }

                if (q.size() > 1) {
                    sb.append("?");
                    isCorrect = false;
                    break;
                }

                int now = q.poll();
                sb2.append(now).append(' ');
                for (int next : list[now]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) q.offer(next);
                }

            }

            if (isCorrect) sb.append(sb2);
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void swap(int a, int b) {
        int winner = a;
        int loser = b;

        if (list[a].contains(b)) {
            winner = b;
            loser = a;
        }

        list[loser].remove((Integer) winner);
        list[winner].add(loser);
        inDegree[loser]++;
        inDegree[winner]--;
    }
}