package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:13
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 2차원 평면 격자 도로망
 * 왼쪽 아래 (1,1), 오른쪽 위 (m,n)
 * 버스는 운행하는 선분 사이의 모든 교차점에서 멈춤
 * 출발지 교차점과 목적지 교차점이 주어질 때 이용하는 버스 최소 수를 구해야 함
 *
 * 구해야 하는 것
 * 출발지 교차점과 목적지 교차점이 주어질 때 이용하는 버스 최소 수를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : 수직선 수 M, N
 * 둘째 줄 : 버스 수 K
 * 셋째 줄 ~ K개 줄 : 버스 운행 정보 5개
 *  - 버스 번호, 시작 좌표 (x1, y1), 끝 좌표 (x2, y2)
 *
 * 제한 요소
 * 1 <= N, M <= 100000
 * 1 <= K <= 5000
 *
 * 생각나는 풀이
 * bfs
 * 노선이 겹치는 경우
 *  - 가로세로, 세로가로
 *      - 세로 길이 안에 가로 y가 있어야 함, 가로 길이 안에 세로 x가 있어야 함
 *  - 가로가로
 *      - y좌표가 같고 x가 겹쳐야 함
 *  - 세로세로
 *      - x좌표가 같고 y가 겹쳐야 함
 * 시간복잡도 O(K*K) 가능할듯
 *
 * 구현해야 하는 기능
 * 1. 버스 노선 저장하는 배열
 *  - 버스 방향은 가로 or 세로
 *  - 무조건 시작 좌표는 작은 쪽, 끝 쫘표는 큰 쪽 설정
 * 2. 시작 좌표부터 bfs 탐색
 *  - 버스 노선 하나당 비용 1
 * 3. 선분 교차하는지 비교하는 로직
 *
 */
public class BOJ_2536 {
    static int N, M, K;
    static Queue<Bus> q = new ArrayDeque();
    static Bus start, end;
    static boolean[] visited;
    static Bus[] buses;

    static class Bus {
        int startX, startY, endX, endY;
        boolean isVertical;

        public Bus(int x1, int y1, int x2, int y2) {
            this.startX = Math.min(x1, x2);
            this.startY = Math.min(y1, y2);
            this.endX = Math.max(x1, x2);
            this.endY = Math.max(y1, y2);
            this.isVertical = (x1 == x2);
        }

        boolean canGo(Bus b) {
            // 가로세로인 경우
            if (this.isVertical ^ b.isVertical) {
                // 세로 버스
                Bus v = (this.isVertical) ? this : b;
                // 가로 버스
                Bus h = (this.isVertical) ? b : this;

                if (v.startY > h.startY || v.endY < h.startY) return false;
                if (h.startX > v.startX || h.endX < v.startX) return false;
                return true;
            }
            // 세로세로인 경우
            else if (this.isVertical) {
                if (this.startX != b.startX) return false;
                if (this.startY > b.endY || this.endY < b.startY) return false;
                return true;
            }
            // 가로가로인 경우
            else {
                if (this.startY != b.startY) return false;
                if (this.startX > b.endX || this.endX < b.startX) return false;
                return true;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        buses = new Bus[K];
        visited = new boolean[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            buses[i] = new Bus(x1, y1, x2, y2);
        }

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int endX = Integer.parseInt(st.nextToken());
        int endY = Integer.parseInt(st.nextToken());

        start = new Bus(startX, startY, startX, startY);
        end = new Bus(endX, endY, endX, endY);

        init();
        System.out.println(findMin());
    }

    private static int findMin() {
        int cnt = 1;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Bus now = q.poll();
                if (end.canGo(now)) return cnt;

                for (int j = 0; j < K; j++) {
                    if (visited[j]) continue;
                    if (!now.canGo(buses[j])) continue;
                    q.offer(buses[j]);
                    visited[j] = true;
                }
            }
            cnt++;
        }
        return -1;
    }

    private static void init() {
        for (int i = 0; i < K; i++) {
            if (start.canGo(buses[i])) {
                q.offer(buses[i]);
                visited[i] = true;
            }
        }
    }
}
