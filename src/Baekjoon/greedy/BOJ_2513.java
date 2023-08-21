package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:15
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 일직선상의 도로가 있다
 * 도로 위에 여러 개의 아파트 단지와 하나의 학교가 있다
 * 학교에서 통학버스를 운행한다
 * 통학버스는 정원 K명만큼 태울 수 있다.
 * 모든 학생을 학교까지 태울 때 가장 최소 거리를 구해야 한다
 *
 * 구해야 하는 것
 * 통학버스의 최소 이동 거리
 *
 * 문제 입력
 * 첫째 줄 : 아파트 단지의 수 N, 통학 버스의 정원 K, 학교의 위치 S
 * 둘째 줄 ~ N개 줄 : 아파트 단지의 위치, 아파트에 사는 학생의 수
 *
 * 제한 요소
 * 2 <= N <= 30,000
 * 1 <= K <= 2000
 * 0 <= 학교, 아파트 단지의 좌표 <= 100,000
 * 1 <= 아파트에 사는 학생 수 <= 2000
 * 모든 좌표는 다름
 *
 * 생각나는 풀이
 * 그리디하게 최장거리 애들부터 실어날라야 함
 * 학교의 왼쪽 오른쪽을 따로 저장
 * 아파트단지의 정보 담는 클래스
 * 학교까지의 거리 내림차순으로 정렬
 * 가야 하는 거리 = 2 * 아파트단지까지 거리
 * 정원만큼 학생 수 꽉채워서 이동
 * 여러 아파트 단지 사람들도 정원 찰때까지 넣음
 *
 * 구현해야 하는 기능
 * 1. 입력에 따라 아파트 단지 정보 받음
 * 2. 학교와의 위치를 비교해 왼쪽 오른쪽 나눠서 리스트에 추가 => 우선순위 큐로 대체
 *  2-1. 아파트 정보 가진 클래스 생성
 *  멤버 : 거리, 학생 수
 * 3. 각각 리스트를 정렬 (거리 내림차순) => 우선순위 큐 사용으로 정렬 필요 x
 * 4. 학생 전부 없어질 때까지 수송
 */
public class BOJ_2513 {
    static int N, K, S;

    static class Apart implements Comparable<Apart> {
        int students, dist;

        public Apart(int dist, int students) {
            this.dist = Math.abs(dist - S);
            this.students = students;
        }

        @Override
        public int compareTo(Apart o) {
            return o.dist - this.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        PriorityQueue<Apart> left = new PriorityQueue<>();
        PriorityQueue<Apart> right = new PriorityQueue<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            if (a < S) {
                left.offer(new Apart(a, s));
            } else right.offer(new Apart(a, s));
        }

        int totalDist = 0;
        while (!left.isEmpty()) {
            int peopleInCar = 0;
            totalDist += 2 * left.peek().dist;
            while (!left.isEmpty() && (peopleInCar + left.peek().students <= K)) peopleInCar += left.poll().students;
            if (!left.isEmpty()) left.peek().students -= (K - peopleInCar);
        }

        while (!right.isEmpty()) {
            int peopleInCar = 0;
            totalDist += 2 * right.peek().dist;
            while (!right.isEmpty() && (peopleInCar + right.peek().students <= K)) peopleInCar += right.poll().students;
            if (!right.isEmpty()) right.peek().students -= (K - peopleInCar);
        }

        System.out.println(totalDist);
    }
}
