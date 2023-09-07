package Baekjoon.sweeping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:08
 * 풀이 완료 : 10:44
 * 풀이 시간 : 36분
 *
 * 문제 해석
 * 도시에 큰 강이 흐르고 있음
 * 모든 사람의 집은 이 강 근처
 * 집은 0번 ~ M번까지 강을 따라 번호가 매겨져 있음
 * 인접한 집 사이의 거리는 1
 * N명의 사람을 모두 태워주어 목적지까지 도착하고 M번 집에 가는데 최소 거리를 구해야 함
 *
 * 구해야 하는 것
 * N명의 사람을 모두 태워주어 목적지까지 도착하고 M번 집에 가는데 최소 거리를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : N, M
 * 둘째 줄 ~ N개 줄 : 각 사람이 수상 택시를 타는 위치와 목적지
 *
 * 제한 요소
 * N <= 300_000
 * 3 <= M <= 1_000_000_000
 * 시작점 = 0
 * 끝점 = M
 *
 * 생각나는 풀이
 * 정렬
 * 출발지 < 목적지라면 고려할 필요가 없음
 * M이 기본 거리
 * 역으로 가는 사람들을 정렬해서 만약 경로가 겹치는 사람이 있다면 같이 태워감
 * 다음 태울 사람과 경로가 안겹치면 내려주고 다시 돌아가는게 더 가까움
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_2836 {
    static class Pair implements Comparable<Pair> {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.start == o.start) return o.end - this.end;
            return o.start - this.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long sum = M;
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (start < end) continue;
            pq.offer(new Pair(start, end));
        }

        long prevEnd = 0, prevStart = 0;
        if (!pq.isEmpty()) {
            Pair now = pq.poll();
            prevEnd = now.end;
            prevStart = now.start;
        }

        while (!pq.isEmpty()) {
            Pair now = pq.poll();
            if (now.start >= prevEnd) {
                prevEnd = Math.min(now.end, prevEnd);
            } else {
                sum += 2 * (prevStart - prevEnd);
                prevEnd = now.end;
                prevStart = now.start;
            }
        }

        sum += 2 * (prevStart - prevEnd);
        System.out.println(sum);
    }
}
