package Baekjoon.dataStructure.sparseTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3117 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] firstVideo = new int[N];
        dp = new int[K + 1][32]; // dp[i][j] = i번 동영상에서 2^j번 건너뛴 영상

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) firstVideo[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // dp[i][0] = i번째 영상의 2^0 (1)번째 추천 영상 번호
        for (int i = 1; i <= K; i++) dp[i][0] = Integer.parseInt(st.nextToken());

        for (int j = 0; j < 31; j++) {
            for (int i = 1; i <= K; i++) {
                int prevNum = dp[i][j];
                // j가 1씩 커지면 실제로는 2배씩 이동하므로
                dp[i][j + 1] = dp[prevNum][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(findMstVideo(firstVideo[i], M - 1)).append(' ');
        }

        System.out.println(sb);
    }

    /**
     * 1 ~ M(최대 10억)은 32비트로 표현 가능하다 2^31 = 21억
     * 한 번 이동할 때마다 남은 양의 반씩 건너뛰어 시간을 단축 -> 희소 배열
     */
    private static int findMstVideo(int nowVideo, int flag) {
        int idx = 0;

        while (flag != 0) {
            if (flag % 2 == 1) nowVideo = dp[nowVideo][idx];
            idx++;
            flag = flag >> 1;
        }

        return nowVideo;
    }
}
