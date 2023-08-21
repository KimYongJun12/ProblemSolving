package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:08
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 직선 도로상에 왼쪽부터 오른쪽으로 1 ~ N번까지 번호가 붙여진 마을이 있음
 * 마을에 있는 물건을 배송하기 위한 트럭 한 대가 있음
 * 트럭은 아래의 조건을 만족하며 최대한 많은 박스를 배송해야 함
 *  - 박스를 트럭에 실으면 박스는 받는 마을에서만 내림
 *  - 트럭은 지나온 마을로 돌아가지 않음
 *  - 박스들 중 일부만 배송하는 것도 가능
 *
 * 구해야 하는 것
 * 배송하는 박스의 최대 수량
 *
 * 문제 입력
 * 첫째 줄 : 마을 수 N, 트럭 용량 C
 * 둘째 줄 : 박스 정보의 개수 M
 * 셋째 줄 ~ M개 줄 : 박스를 보내는 마을 번호, 박스 받는 마을 번호, 박스 개수
 *
 * 제한 요소
 * 2 <= N <= 2000
 * 1 <= C <= 10000
 * 1 <= M <= 10000
 * 1 <= 한 세트 박스 개수 <= 10000
 * 1 <= start < end <= N
 *
 * 생각나는 풀이
 * 그리디는 그리디같은데
 * 최대한 빠르게 내릴 수 있는 애들 싣는게 맞는 거 같은데
 * 출발 마을 -> 목표 마을 배송한다는 얘기
 *  => 목표 마을 전까지 트럭 용량을 차지한다는 얘기
 *  => 모든 마을의 초기 용량을 트럭 용량 C로 잡아두고
 *  => 배송하는 애가 있으면 배송 시작 지점 ~ 목표 지점 직전까지 마을의 용량을 빼줌
 *
 * 구현해야 하는 기능
 * 1. 각 마을별 배송하는 정보 저장
 * 2. 배송 완료 마을 오름차순으로 정렬
 * 3. 배송 가능한지 체크하며 마을 이동
 *
 */
public class BOJ_8980 {
    static class Town implements Comparable<Town> {
        int start, end, weight;

        public Town(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Town o) {
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] capacityOfTowns = new int[N + 1];
        Arrays.fill(capacityOfTowns, C);

        PriorityQueue<Town> pq = new PriorityQueue<>();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Town(start, end, weight));
        }

        int max = 0;
        while (!pq.isEmpty()) {
            Town now = pq.poll();
            int load = now.weight;
            for (int i = now.start; i < now.end; i++) {
                load = Math.min(capacityOfTowns[i], load);
            }
            for (int i = now.start; i < now.end; i++) {
                capacityOfTowns[i] -= load;
            }
            max += load;
        }

        System.out.println(max);
    }
}
