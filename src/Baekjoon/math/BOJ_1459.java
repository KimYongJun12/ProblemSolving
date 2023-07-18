package Baekjoon.math;
/*
1. 대각선이동 < 1칸이동 * 2일 때
-> min(가로, 세로)만큼 대각선 이동
2. 대각선이동 > 1칸이동 * 2일 때
-> 무조건 1칸이동
3. 대각선이동 < 1칸이동일 때
-> min(가로, 세로)만큼 대각선 이동
    -> 남은 길이가 짝수라면 대각선 지그재그로 이동
    -> 남은 길이가 홀수라면 남은 길이 1까지 지그재그 이동 + 1칸이동
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1459 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        if (x < y) {
            long tmp = x;
            x = y;
            y = tmp;
        }

        long w = Long.parseLong(st.nextToken());
        long s = Long.parseLong(st.nextToken());
        long minTime;

        if (s < w) {
            minTime = (x + y) % 2 == 0 ? x * s : (x - 1) * s + w;
        } else if (s < w * 2) {
            minTime = y * s + (x - y) * w;
        } else {
            minTime = (x + y) * w;
        }

        System.out.println(minTime);
    }
}
