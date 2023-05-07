package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2580 {
    static boolean check = false;
    static int blankLen = 0;
    static int[][] board = new int[9][9];
    static boolean[][] verticalLine = new boolean[9][10];
    static boolean[][] horizontalLine = new boolean[9][10];
    static boolean[][][] square = new boolean[3][3][10];
    static ArrayList<Node> blank = new ArrayList<>();

    static class Node {
        int x, y, sqX, sqY;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            sqX = x / 3;
            sqY = y / 3;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        blankLen = 0;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blankLen++;
                    blank.add(new Node(i, j));
                    continue;
                }
                horizontalLine[i][board[i][j]] = true;
                verticalLine[j][board[i][j]] = true;
                square[i / 3][j / 3][board[i][j]] = true;
            }
        }


        backTracking(0);
    }

    private static void backTracking(int depth) {
        if (check) return;
        if (depth == blankLen) {
            check = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }

            System.out.println(sb);
            return;
        }

        Node now = blank.get(depth);
        for (int i = 1; i <= 9; i++) {
            if (horizontalLine[now.x][i] || verticalLine[now.y][i] || square[now.sqX][now.sqY][i]) continue;
            board[now.x][now.y] = i;
            horizontalLine[now.x][i] = true;
            verticalLine[now.y][i] = true;
            square[now.sqX][now.sqY][i] = true;
            backTracking(depth + 1);
            horizontalLine[now.x][i] = false;
            verticalLine[now.y][i] = false;
            square[now.sqX][now.sqY][i] = false;
        }
    }
}
