package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 4:34
 * 풀이 완료 : 5:00
 * 풀이 시간 : 26분
 *
 * 문제 해석
 * 크기가 모두 다른 직사각형 모양의 색종이 여러 장이 있음
 * 색종이를 하나씩 올려 놓아 되도록 많은 장수의 색종이를 쌓아야 함
 * 색종이를 위에 올릴 때에는 아래 두 조건을 만족해야 함
 *  - 새로 올려 놓는 색종이는 맨 위의 색종이보다 크지 않아야 함
 *  - 새로 올려 놓는 색종이와 맨 위의 색종이의 변은 서로 평행해야 함 (색종이를 90도 돌릴 수 있다)
 *
 * 구해야 하는 것
 * 쌓을 수 있는 최대 색종이 장 수
 *
 * 문제 입력
 * 첫째 줄 : 색종이의 수 N
 * 둘째 줄 ~ N개 줄 : 색종이의 두 변의 길이
 *
 * 제한 요소
 * 1 <= N <= 100
 * 1 <= H, W < 1000
 *
 * 생각나는 풀이
 * 그리디, 정렬?
 * 혹은 dp
 * 전부 큰 값을 가로, 작은 값을 세로로 둔 다음
 * 가로가 큰 순, 같으면 세로가 큰 순으로 정렬함
 * 그 후 놓을 수 있는 애들 카운팅 하면 되지 않을까
 *
 * -> 그리디하면 반례가 생김 (한쪽만 긴 경우 -> 이후 애들 못놓음)
 * -> 그럼 정렬을 넓이로 해야 하나?
 * dp??
 * lis처럼 dp[i] = i번 종이를 놓았을 때 최대 종이 수
 * dp[i] = 앞의 종이들의 가로, 세로가 나보다 작으면 걔네 중 가장 큰 값 + 1
 *
 * 구현해야 하는 기능
 * 1. 종이 가로 세로 저장할 클래스
 * 2. 정렬
 * 3. dp
 *  lis와 유사한 과정으로
 *  앞에 있는 애가 가로, 세로 전부 나보다 작은 애 중 가장 많이 쌓을 수 있는 양 + 1이 현재 내가 쌓을 수 있는 종이의 양
 */
public class BOJ_2643 {
    static class Paper implements Comparable<Paper> {
        int w, h;

        public Paper(int a, int b) {
            w = Math.max(a, b);
            h = Math.min(a, b);
        }

        @Override
        public int compareTo(Paper o) {
            if (this.w * this.h == o.w * o.h) return this.w - o.w;
            return this.w * this.h - o.w * o.h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Paper[] papers = new Paper[N + 1];
        papers[0] = new Paper(0, 0);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            papers[i] = new Paper(a, b);
        }

        Arrays.sort(papers);
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Paper now = papers[i];
            for (int j = 0; j < i; j++) {
                Paper prev = papers[j];
                if (prev.h <= now.h && prev.w <= now.w) {
                    if (dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
