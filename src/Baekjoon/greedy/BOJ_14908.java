package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:53
 * 풀이 완료 :
 * 풀이 시간 :
 * <p>
 * 문제 해석
 * 구두 수선공의 작업 N개
 * 하루에 한 작업만 가능
 * i번째 작업은 Ti 시간이 걸림
 * i번째 작업 시작 전에 하루가 지연될 때마다 보상금 Si를 지불해야 함
 * 구두 수선공이 지급할 보상금이 최저가 되는 순서를 출력해야 함
 * <p>
 * 구해야 하는 것
 * 구두 수선공이 지급할 보상금이 최저가 되는 순서를 출력해야 함
 * <p>
 * 문제 입력
 * 첫째 줄 : N
 * 둘째 줄 ~ N개 줄 : Ti, Si
 * <p>
 * 제한 요소
 * 1 <= N <= 1000
 * 1 <= Ti <= 1000
 * 1 <= Si <= 10000
 * <p>
 * 생각나는 풀이
 * 그리디 or DP
 * 벌금이 최소가 되려면
 * 가성비가 떨어지는 애를 먼저 넣으면 되지 않을까
 *
 * <p>
 * 구현해야 하는 기능
 * 1. 입력값 저장
 * 2. 그리디 구현
 */
public class BOJ_14908 {
    static class Work implements Comparable<Work> {
        int idx;
        double ratio;

        public Work(int idx, int time, int cost) {
            this.idx = idx;
            this.ratio = (double) cost / time;
        }

        @Override
        public int compareTo(Work o) {
            if (Math.abs(this.ratio - o.ratio) < 0.000000001) return this.idx - o.idx;
            return Double.compare(o.ratio, this.ratio);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Work> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Work(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(pq.poll().idx).append(' ');
        }
        System.out.println(sb);
    }
}
