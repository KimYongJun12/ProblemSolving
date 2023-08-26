package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:50
 * 풀이 완료 : 10:40
 * 풀이 시간 : 1시간 50분
 *
 * 문제 해석
 * 낚시터 크기 R * C
 * 각 칸에는 상어가 최대 한 마리
 * 초기에 낚시왕은 1번 열 왼쪽에 있음
 * 1초동안 일어나는 일
 *  1. 낚시왕이 오른쪽으로 이동
 *  2. 낚시왕이 열에 있는 상어 중 가장 땅에 가까운 상어 낚시
 *  3. 상어 이동
 * 상어는 입력으로 주어진 속도로 이동, 속도는 칸/초
 *  - 이동할 때 격자판 경계를 넘어가는 경우 반대로 남은 이동거리만큼 이동 (방향도 반대로 변경)
 * 상어가 이동 마친 후에 여러 상어가 한 칸에 있을 수 있음
 *  => 가장 큰 상어 제외하고 모든 상어를 제거
 *
 * 구해야 하는 것
 * 낚시왕이 상어 낚시를 할 때 잡은 상어의 크기 총합을 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : R, C, 상어의 수 M
 * 둘째 줄 ~ M개 줄 : 상어의 정보
 * r c s d z
 * r, c : 좌표
 * s : 속도
 * d : 방향 (1 : 상, 2 : 하, 3 : 우, 4 : 좌)
 * z : 크기
 *
 * 제한 요소
 * 2 <= R, C <= 100
 * 0 <= M <= R * C
 * 0 <= s <= 1000
 * 1 <= d <= 4
 * 1 <= z <= 10000
 *
 * 생각나는 풀이
 * 구현
 * 1. 상어 클래스
 *  - 멤버 : x, y, s, d, z
 *  - 메서드 : move()
 *      - 좌우로 움직인다면 s = s % (2 * (C - 1))만큼 움직이는 것과 같음
 *          - s > C - 1이라면 좌표를 (C + 1 - c)로, 방향을 반대로 변경하고 (s - C - 1)만큼 이동하면 됨
 *      - 상하로 움직인다면 s = s % (2 * (R - 1))만큼 움직이는 것과 같음
 *          - s > R - 1이라면 좌표를 (R + 1 - r)로, 방향을 반대로 변경하고 (s - R - 1)만큼 이동하면 됨
 * 2. 상어 이동용 큐
 *  => 동시에 처리하기 위해 큐에 전부 넣는다
 *  => 상어의 이동을 전부 처리했으면 다시 맵에 넣는다
 *  => 맵의 같은 칸에 상어가 이미 있을 시에는 큰 것만 남긴다
 *
 *
 * 구현해야 하는 기능
 * 1. 상어 클래스
 * 2. R * C 크기의 상어 클래스 배열
 * 3. 상어의 움직임 처리용 큐
 *
 * 시뮬레이션 순서
 * 1. 낚시왕 이동
 * 2. 해당 열 탐색 => 상어 만나면 상어 사이즈를 total에 더해주고 break
 * 3. 상어의 이동
 *  3-1. 모든 칸 탐색 후 상어가 있으면 상어 이동 메서드 수행 후 큐에 담고 해당 칸 null로 만들어줌
 * 4. 모든 칸 탐색이 끝나고 모든 상어가 이동을 마쳤다면 큐에서 하나씩 목표 칸에 넣어줌
 *  4-1. 만약 목표 칸에 이미 상어가 있다면 사이즈를 비교해 큰 상어만 남김
 * 5. 1 ~ C만큼 반복
 *
 */
public class BOJ_17413 {
    static int R, C; // 물의 크기
    static Shark[][] water; // 상어가 들어있는 물
    static int[] delta = {0, -1, 1, 1, -1}; // 상하우좌
    static Queue<Shark> q = new ArrayDeque<>(); // 상어 이동 관리용 큐

    static class Shark { // 상어 정보 저장용 클래스
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            // * 좌우로 움직인다면 s = s % (2 * (C - 1))만큼 움직이는 것과 같음
            // * 상하로 움직인다면 s = s % (2 * (R - 1))만큼 움직이는 것과 같음
            this.s = (d <= 2) ? s % (2 * (R - 1)) : s % (2 * (C - 1));
            this.d = d;
            this.z = z;
        }

        public void move() { // 상어의 이동 메서드
            int tempS = s;
            if (d <= 2) { // 상하 이동
                if (tempS > R - 1) { // R - 1만큼 이동하면 상하 대칭 좌표로 이동
                    r = (R + 1 - r); // 대칭 좌표
                    tempS -= (R - 1); // 남은 이동 거리
                    d = reverseDir(d); // 방향 전환
                }

                if (d == 2 && r + tempS > R) { // 아래쪽 벽에 부딪히는 경우
                    r = R - ((r + tempS) - R); // 상어의 최종 위치
                    d = reverseDir(d); // 방향 전환
                } else if (d == 1 && r - tempS < 1) { // 위쪽 벽에 부딪히는 경우
                    r = 2 + (tempS - r);
                    d = reverseDir(d);
                } else { // 벽에 부딪히지 않는 경우
                    r += delta[d] * tempS;
                }
            } else { // 좌우 이동
                if (tempS > C - 1) { // C - 1만큼 이동하면 좌우 대칭 좌표로 이동
                    c = (C + 1 - c);
                    tempS -= (C - 1);
                    d = reverseDir(d);
                }

                if (d == 3 && c + tempS > C) { // 오른쪽 벽에 부딪히는 경우
                    c = C - ((c + tempS) - C);
                    d = reverseDir(d);
                } else if (d == 4 && c - tempS < 1) { // 왼쪽 벽에 부딪히는 경우
                    c = 2 + (tempS - c);
                    d = reverseDir(d);
                } else { // 벽에 부딪히지 않는 경우
                    c += delta[d] * tempS;
                }
            }
        }

        private int reverseDir(int d) { // 방향 전환용 메서드
            return (d & 1) == 0 ? d - 1 : d + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        water = new Shark[R + 1][C + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            water[r][c] = new Shark(r, c, s, d, z);
        }

        int total = 0;
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R; j++) {
                if (water[j][i] == null) continue; // 상어가 있을 때까지 아래로 탐색
                total += water[j][i].z; // 처음 상어 발견하면 낚음
                water[j][i] = null; // 상어 사라짐
                break;
            }
            moveAllShark(); // 상어 이동
        }

        System.out.println(total);
    }

    private static void moveAllShark() {
        // 모든 물 칸에 대해 상어 탐색
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (water[i][j] == null) continue;
                q.offer(water[i][j]); // 상어가 있다면 이동 큐에 넣어줌
                water[i][j] = null; // 현재 칸 비워줌
            }
        }

        while (!q.isEmpty()) {
            Shark now = q.poll(); // 상어 한 마리씩 꺼내며 이동시킴
            now.move(); // 상어 이동
            if (water[now.r][now.c] == null) { // 현재 빈 칸이라면
                water[now.r][now.c] = now; // 상어 채워줌
            } else { // 이미 상어가 있다면 큰 상어만 남김
                water[now.r][now.c] = (water[now.r][now.c].z > now.z) ? water[now.r][now.c] : now;
            }
        }
    }
}