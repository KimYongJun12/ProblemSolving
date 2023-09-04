package Baekjoon.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:54
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 집에서 물건을 전부 가지고 나가야 함
 *
 * 구해야 하는 것
 * 물건들의 위치와 집의 모양이 주어졌을 때 물건을 모두 챙겨서 외출하기까지 최소 걸음 수
 *
 * 문제 입력
 * 첫째 줄 : 집의 가로, 세로 길이 N, M
 * 두 번째 줄 ~ M개 줄 : 집의 모습
 *  - '#' : 벽, 'X' : 물건, '.' : 빈 칸, 'S' : 시작점, 'E' 끝 점
 *
 * 제한 요소
 * 3 <= N, M <= 50
 * 물건의 개수 <= 5
 *
 * 생각나는 풀이
 * bfs, bitmasking
 * 방문 처리를 비트마스킹으로 하여 bfs하면 될 것 같음
 * 1. 입력에 따라 집 상태를 입력받음
 * 2. 순회하며 X를 만나면 번호로 치환
 * 3. S지점부터 bfs 탐색
 * 4. 모든 비트가 채워졌을 때 탈출하는 최소값 찾음
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 집 모양 저장
 * 2. 배열 순회하며 X를 만나면 X 카운트 증가, X -> 숫자로 변환
 * 3. S의 위치부터 bfs 탐색
 *  - 같은 곳을 방문하더라도 체킹된 비트가 다르면 다른 경우
 * 4. 모든 물건을 찾은 후 탈출
 */
public class BOJ_17244 {
    static int R, C, cnt;
    static char[][] map;
    static Node end;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, flag, dist;

        public Node(int x, int y, int flag, int dist) {
            this.x = x;
            this.y = y;
            this.flag = flag;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[R][];
        Node start = null;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = (char) cnt++;
                } else if (map[i][j] == 'S') {
                    start = new Node(i, j, 0, 0);
                } else if (map[i][j] == 'E') {
                    end = new Node(i, j, 0, 0);
                }
            }
        }

        System.out.println(bfs(start));
    }

    private static int bfs(Node start) {
        int finished = (1 << cnt) - 1;
        boolean[][][] visited = new boolean[1 << cnt][R][C];
        Queue<Node> q = new ArrayDeque<>();
        visited[0][start.x][start.y] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == end.x && now.y == end.y) {
                if (now.flag == finished) return now.dist;
                else continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || map[nextX][nextY] == '#' || visited[now.flag][nextX][nextY]) continue;
                if (map[nextX][nextY] < cnt) {
                    q.offer(new Node(nextX, nextY, now.flag | 1 << map[nextX][nextY], now.dist + 1));
                } else {
                    q.offer(new Node(nextX, nextY, now.flag, now.dist + 1));
                }
                visited[now.flag][nextX][nextY] = true;
            }
        }

        return 0;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
