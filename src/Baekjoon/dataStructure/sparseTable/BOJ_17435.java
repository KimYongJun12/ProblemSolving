package Baekjoon.dataStructure.sparseTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17435 {
    static int M;
    static int[][] sparseTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        M = Integer.parseInt(br.readLine());
        int size = 1, cnt = 0;
        while (size < 500000) {
            size <<= 1;
            cnt++;
        }

        sparseTable = new int[M + 1][cnt];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sparseTable[i][0] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < cnt - 1; i++) {
            for (int j = 1; j <= M; j++) {
                int prevNum = sparseTable[j][i];
                sparseTable[j][i + 1] = sparseTable[prevNum][i];
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            sb.append(solve(n, x)).append('\n');
        }

        System.out.println(sb);
    }

    private static int solve(int n, int x) {
        int idx = 0;
        while (n != 0) {
            if (n % 2 == 1) x = sparseTable[x][idx];
            idx++;
            n >>= 1;
        }

        return x;
    }

}
