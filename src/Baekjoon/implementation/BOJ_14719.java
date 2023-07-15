package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        boolean[][] blocks = new boolean[H][W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int height = Integer.parseInt(st.nextToken());
            for (int j = 0; j < height; j++) {
                blocks[j][i] = true;
            }
        }

        int sum = 0;
        for (int i = 0; i < H; i++) {
            boolean isFirst = true;
            int prev = 0;
            for (int j = 0; j < W; j++) {
                if (blocks[i][j]) {
                    if (isFirst) {
                        isFirst = false;
                        prev = j;
                    } else {
                        sum += j - prev - 1;
                        prev = j;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
