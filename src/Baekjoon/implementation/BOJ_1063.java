package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1063 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] move = {"RT", "T", "LT", "L", "LB", "B", "RB", "R"};
        int[] dx = {1, 1, 1, 0, -1, -1, -1, 0};
        int[] dy = {1, 0, -1, -1, -1, 0, 1, 1};
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        Point king = new Point(s.charAt(1) - '1', s.charAt(0) - 'A');
        s = st.nextToken();
        Point stone = new Point(s.charAt(1) - '1', s.charAt(0) - 'A');
        
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < 8; j++) {
                if (s.equals(move[j])) {
                    int nextX = king.x + dx[j];
                    int nextY = king.y + dy[j];
                    if (!isInRange(nextX, nextY)) continue;
                    if (stone.x == nextX && stone.y == nextY) {
                        if (!isInRange(stone.x + dx[j], stone.y + dy[j])) continue;
                        stone.x += dx[j];
                        stone.y += dy[j];
                    }
                    king.x += dx[j];
                    king.y += dy[j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append((char) (king.y+ 'A')).append(king.x + 1).append('\n').append((char) (stone.y + 'A')).append(stone.x + 1);
        System.out.println(sb);
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
