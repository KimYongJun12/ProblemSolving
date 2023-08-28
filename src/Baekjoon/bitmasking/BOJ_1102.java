package Baekjoon.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:27
 * 풀이 완료 : 10:02
 * 풀이 시간 : 35분
 *
 * 문제 해석
 * 발전소 N개가 있다. 각각의 발전소는 고장났거나 멀쩡하거나
 * 고장나지 않은 발전소를 이용해 고장난 발전소를 고칠 수 있다
 * 고칠때 비용이 발생한다. 이 비용은 어떤 발전소에서 어떤 발전소를 고치느냐에 따라 다르다
 *
 * 구해야 하는 것
 * 적어도 P개의 발전소가 고장나지 않도록 발전소를 고치는 비용의 최솟값을 구해야 한다
 *
 * 문제 입력
 * 첫째 줄 : 발전소의 개수 N
 * 둘째 줄 ~ N개 줄 : 발전소 i를 이용해 발전소 j를 고칠 때 드는 비용
 * 다음 줄 : 초기에 발전소가 켜있는지, 고장났는지 Y = 켜짐, N = 꺼짐
 * 다음 줄 : 최소 갯수 P
 *
 * 제한 요소
 * 1 <= N <= 16
 * 0 <= E[i][j] <= 36
 *
 * 생각나는 풀이
 * MST를 생각했는데 MST는 그리디하게 연결하기 때문에 완전히 연결되지 않은 경우에는 최소 비용을 보장하지 않음
 * 비트마스킹 + dp
 * dp[101101] = min(dp[001101] + cost, dp[100101] + cost, dp[101001] + cost, dp[101100] + cost)
 *
 * 구현해야 하는 기능
 * dp
 */
public class BOJ_1102 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int INF = 987654321;
        int[] dp = new int[1 << N];
        int[][] cost = new int[N][N];
        Arrays.fill(dp, INF);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start = 0;
        String startE = br.readLine();
        for (int i = 0; i < N; i++) {
            if (startE.charAt(i) == 'Y') {
                start |= 1 << i;
            }
        }

        int P = Integer.parseInt(br.readLine());
        dp[start] = 0;
        for (int flag = start; flag < 1 << N; flag++) {
            for (int offIdx = 0; offIdx < N; offIdx++) {
                if ((flag & 1 << offIdx) == 0) {
                    for (int onIdx = 0; onIdx < N; onIdx++) {
                        if ((flag & 1 << onIdx) > 0) {
                            dp[(flag | (1 << offIdx))] = Math.min(dp[(flag | (1 << offIdx))], dp[flag] + cost[onIdx][offIdx]);
                        }
                    }
                }
            }
        }

        int min = INF;
        for (int i = (1 << P) - 1; i < 1 << N; i++) {
            if (dp[i] >= INF) continue;
            if (Integer.bitCount(i) >= P) min = Math.min(min, dp[i]);
        }

        System.out.println(min == INF ? -1 : min);
    }
}
