package Baekjoon.backTracking;
/**
 * 4 <= N <= 20
 * 비트마스킹 브루트포스
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15661 {
    static int N, min = Integer.MAX_VALUE;
    static int[][] synergy;
    static boolean[] isFirstTeam;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        synergy = new int[N][N];
        isFirstTeam = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                synergy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0);
        System.out.println(min);
    }

    private static void backTracking(int depth) {
        if (depth == N) {
            int[] teamScore = new int[2];
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (isFirstTeam[i] == isFirstTeam[j]) {
                        if (isFirstTeam[i]) {
                            teamScore[0] += synergy[i][j] + synergy[j][i];
                        } else {
                            teamScore[1] += synergy[i][j] + synergy[j][i];
                        }
                    }

                }
            }

            min = Math.min(min, Math.abs(teamScore[0] - teamScore[1]));
            if (min == 0) {
                System.out.println(min);
                System.exit(0);
            }

            return;
        }

        isFirstTeam[depth] = false;
        backTracking(depth + 1);
        isFirstTeam[depth] = true;
        backTracking(depth + 1);
    }
}
