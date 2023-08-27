package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:07
 * 풀이 완료 : 11:00
 * 풀이 시간 : 53분
 *
 * 문제 해석
 * 택시 운전
 * N * N의 격자 맵
 * 벽이 있을 수 있음
 * 손님은 M명
 * 1칸 이동할 때마다 연료 1 소모
 * 손님을 목적지로 운반하면 승객을 태워 이동한 거리 * 2만큼 연료가 참
 * 목적지에 도달함과 동시에 연료가 0이 되어도 됨
 * 승객 선정 기준
 *  - 거리순
 *  - 거리가 같으면 작은 행
 *  - 행이 같으면 작은 열
 *
 * 구해야 하는 것
 * M명의 승객을 모두 목적지까지 운반했을 때 남은 연료의 양
 * 실패했다면 -1 출력
 *
 * 문제 입력
 * 첫째 줄 : N, M, 초기 연료 F
 * 둘째 줄 ~ N개 줄 : 활동 영역 지도 0 = 빈 칸, 1 = 벽
 * 다음 줄 : 택시의 초기 위치
 * 다음 줄 ~ M개 줄 : 승객의 위치, 목적지의 위치
 *
 * 제한 요소
 * 2 <= N <= 20
 * 1 <= M <= N^2
 * 1 <= F <= 500000
 *
 * 생각나는 풀이
 * 택시의 위치에서 BFS 탐색 (연료가 가능한 곳까지)
 * => 손님을 찾았다면 손님 위치로 이동, 연료 감소
 * => 손님의 목적지까지 bfs 탐색 (연료가 가능한 곳까지)
 *  - 목적지까지 이동했다면 거리만큼 연료에 더해줌, 택시 위치 목적지로 옮김
 *  - 목적지까지 도달 못했으면 종료
 * 손님이 다 운반될 때까지 진행
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 맵
 * 2. 손님을 저장할 배열
 * 3. 시뮬레이션
 *  3-1. 택시의 위치에서 연료 양만큼 거리를 탐색
 *      - 손님 발견했다면 거리만큼 연료 감소 후 다음 과정 진행
 *      - 손님 발견 못했다면 시뮬레이션 종료, -1반환
 *  3-2. 손님의 위치에서 연료 양만큼 bfs 탐색
 *      - 목적지의 좌표에 도달했다면 거리만큼 연료에 더해줌, 택시의 좌표를 목적지 좌표로 변경
 *      - 목적지의 좌표에 도달하지 못했다면 시뮬레이션 종료, -1반환
 *  3-3. 손님 수 M번만큼 가능하면 남은 연료 양 반환
 *
 *
 */
public class BOJ_19238 {
    static int N, M, fuel, pNumber = 0;
    static int[][] map;
    static Node[] passenger; // 손님의 목적지 좌표 저장
    static Node taxi;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static boolean[][] visited;
    static class Node implements Comparable<Node> {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        passenger = new Node[M + 1];
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) map[i][j] = -1;
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int destX = Integer.parseInt(st.nextToken());
            int destY = Integer.parseInt(st.nextToken());

            map[startX][startY] = i;
            passenger[i] = new Node(destX, destY, 0);
        }

        while (M-- > 0) {
            if (!simulation()) {
                fuel = -1;
                break;
            }
        }

        System.out.println(fuel);
    }

    private static boolean simulation() {
        int minDist = findPassenger();
        if (minDist == -1) return false;
        fuel -= minDist;
        int destDist = findDestination();
        if (destDist == -1) return false;
        fuel += destDist;
        return true;
    }

    private static int findPassenger() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) Arrays.fill(visited[i], false);
        visited[taxi.x][taxi.y] = true;
        pq.offer(new Node(taxi.x, taxi.y, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.dist > fuel) break;
            if (map[now.x][now.y] > 0) {
                pNumber = map[now.x][now.y];
                map[now.x][now.y] = 0;
                taxi = now;
                return now.dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextX = now.x + dx[dir];
                int nextY = now.y + dy[dir];

                if (!isInRange(nextX, nextY) || map[nextX][nextY] == -1 || visited[nextX][nextY]) continue;
                pq.offer(new Node(nextX, nextY, now.dist + 1));
                visited[nextX][nextY] = true;
            }
        }

        return -1;
    }

    private static int findDestination() {
        int destX = passenger[pNumber].x;
        int destY = passenger[pNumber].y;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) Arrays.fill(visited[i], false);
        visited[taxi.x][taxi.y] = true;
        pq.offer(new Node(taxi.x, taxi.y, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.dist > fuel) break;
            if (now.x == destX && now.y == destY) {
                taxi = now;
                return now.dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextX = now.x + dx[dir];
                int nextY = now.y + dy[dir];

                if (!isInRange(nextX, nextY) || map[nextX][nextY] == -1 || visited[nextX][nextY]) continue;
                pq.offer(new Node(nextX, nextY, now.dist + 1));
                visited[nextX][nextY] = true;
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
}
