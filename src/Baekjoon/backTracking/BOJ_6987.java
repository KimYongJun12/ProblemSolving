package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987 {
    static int[][] match;
    static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        match = new int[6][3];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int totalScore = 0;

            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    match[j][k] = Integer.parseInt(st.nextToken());
                    totalScore += match[j][k];
                }
            }
            if (totalScore != 30){
                sb.append(0).append(' ');
                continue;
            }

            sb.append(canMatch(0) ? 1 : 0).append(' ');
        }

        System.out.println(sb);
    }

    private static boolean canMatch(int depth) {
        if (depth == 15) return true;
        if (match[home[depth]][0] > 0 && match[away[depth]][2] > 0) {
            match[home[depth]][0]--;
            match[away[depth]][2]--;
            if (canMatch(depth + 1)) return true;
            match[home[depth]][0]++;
            match[away[depth]][2]++;
        }

        if (match[home[depth]][1] > 0 && match[away[depth]][1] > 0) {
            match[home[depth]][1]--;
            match[away[depth]][1]--;
            if (canMatch(depth + 1)) return true;
            match[home[depth]][1]++;
            match[away[depth]][1]++;
        }

        if (match[home[depth]][2] > 0 && match[away[depth]][0] > 0) {
            match[home[depth]][2]--;
            match[away[depth]][0]--;
            if (canMatch(depth + 1)) return true;
            match[home[depth]][2]++;
            match[away[depth]][0]++;
        }

        return false;
    }
}
