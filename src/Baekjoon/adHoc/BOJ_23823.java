package Baekjoon.adHoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23823 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] chocoChips = new int[2][n]; // [0] = 가로, [1] = 세로
        int hHeight = 0, vHeight = 0; // 가로, 세로 초코칩 최다 개수
        int hMax = n, vMax = n; // 가로, 세로 초코칩 최다 개수인 줄의 수

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken()) - 1;
            int idx = Integer.parseInt(st.nextToken()) - 1;
            chocoChips[query][idx]++;

            /**
             * 한 줄 추가한 개수 > 기존 최다 개수라면
             * 최다 개수 갱신, 최다 개수 줄의 수 1로 변경
             * 한 줄 추가한 개수 = 기존 최다 개수라면
             * 최고 줄 개수 + 1
             * 최고 높이 초코칩 개수는 최다 개수 가로줄 * 최다 개수 세로줄
             */

            if (query == 0) { // 가로 한 줄 추가
                if (chocoChips[query][idx] > hHeight) {
                    hHeight++;
                    hMax = 1;
                } else if (chocoChips[query][idx] == hHeight) {
                    hMax++;
                }
            } else { // 세로 한 줄 추가
                if (chocoChips[query][idx] > vHeight) {
                    vHeight++;
                    vMax = 1;
                } else if (chocoChips[query][idx] == vHeight) {
                    vMax++;
                }
            }

            sb.append(hMax * vMax).append('\n');
        }

        System.out.println(sb);
    }
}
