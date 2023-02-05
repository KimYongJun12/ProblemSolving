package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1525 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) sb.append(st.nextToken());
        }

        System.out.println(bfs(sb.toString()));
    }

    private static int bfs(String first) {
        String answer = "123456780";
        Queue<String> q = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();
        visited.put(first, 0);
        q.offer(first);

        while (!q.isEmpty()) {
            String now = q.poll();
            int move = visited.get(now);
            if (now.equals(answer)) return move;

            int zero = now.indexOf('0');
            int x = zero / 3;
            int y = zero % 3;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (!isInRange(nextX, nextY)) continue;

                int nextZero = nextX * 3 + nextY;
                String nextS = swap(now, zero, nextZero);
                if (!visited.containsKey(nextS)) {
                    q.offer(nextS);
                    visited.put(nextS, move + 1);
                }

            }
        }

        return -1;
    }

    private static String swap(String now, int zero, int nextZero) {
        StringBuilder sb = new StringBuilder();
        sb.append(now);
        char ch = sb.charAt(nextZero);
        sb.delete(zero, zero + 1);
        sb.insert(zero, ch);
        sb.delete(nextZero, nextZero + 1);
        sb.insert(nextZero, '0');

        return sb.toString();
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

}
