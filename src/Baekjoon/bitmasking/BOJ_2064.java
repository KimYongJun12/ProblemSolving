package Baekjoon.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] computers = new int[4][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(),".");
            for (int j = 0; j < 4; j++) {
                computers[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int[] subnetMask = {255, 255, 255, 255};

        fo : for (int i = 0; i < 4; i++) {
            for (int j = 7; j >= 0; j--) {
                boolean isZero = false, isOne = false;
                for (int k = 0; k < N; k++) {
                    if ((computers[i][k] & (1 << j)) == 0) isZero = true;
                    else isOne = true;
                    if (isOne && isZero) {
                        while (j >= 0) {
                            subnetMask[i] -= (1 << j--);
                        }

                        while (++i < 4) {
                            subnetMask[i] = 0;
                        }
                        break fo;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            computers[i][0] &= subnetMask[i];
        }
        System.out.printf("%d.%d.%d.%d%n", computers[0][0], computers[1][0], computers[2][0], computers[3][0]);
        System.out.printf("%d.%d.%d.%d%n", subnetMask[0], subnetMask[1], subnetMask[2], subnetMask[3]);
    }
}
