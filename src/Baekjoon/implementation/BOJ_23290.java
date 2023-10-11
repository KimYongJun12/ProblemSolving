package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:25
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 마법사 상어 복제
 * 4 * 4칸
 * 격자에는 물고기 M마리 있음
 * 물고기는 격자 한 칸에 들어가있고, 이동 방향을 가짐
 * 이동 방향
 * - 상하좌우
 * - 대각선 4방
 * 상어도 격자 한 칸에 들어가있음
 * 한 칸에 물고기 둘 이상 있을 수 있고, 상어 칸에 물고기가 있을 수 있음
 * 마법 연습 과정
 * 1. 상어가 모든 물고기에게 복제 마법 수행, 5번 과정에서 복제가 일어남
 * 2. 모든 물고기가 한 칸 이동, 상어가 있는 칸, 물고기 냄새 있는 칸, 격자 범위 벗어나는 칸으로는 이동 불가능
 *  - 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 반시계방향으로 45도씩 회전
 *  - 8방 다봐도 이동 불가능하면 이동하지 않음
 * 3. 상어가 연속해서 3칸 이동, 현재 칸에서 "상하좌우" 인접 칸으로만 이동 가능
 *  - 연속해서 이동하는 중에 물고기가 있는 칸으로 이동하면 그 칸에 있는 모든 물고기는 격자에서 제외, 물고기 냄새를 남김
 *  - 가능한 이동 방법 중 제외되는 물고기가 가장 많은 방법으로 이동
 *  - 그것도 같다면 사전 순으로 가장 앞서는 방법으로 이동
 *      사전순 이동
 *      방향을 상좌하우 각각 1, 2, 3, 4로 봄
 *      3번 이동했을 때 각각 번호를 붙여서 만든 3자리 수가 작은 순으로 우선순위가 있음
 *      상상상 = 111, 하우하 = 343, 111 < 343이므로 상상상이 우선
 *       [상, 상, 상], [상, 상, 좌], [상, 상, 하], [상, 상, 우], [상, 좌, 상], [상, 좌, 좌], [상, 좌, 하], [상, 좌, 우], [상, 하, 상],
 *       ..., [우, 하, 하], [우, 하, 우], [우, 우, 상], [우, 우, 좌], [우, 우, 하], [우, 우, 우]
 *       위의 순서대로임.
 *
 * 4. 두 번 전 연습에서 생긴 물고기 냄새가 격자에서 사라짐
 * 5. 물고기 복제됨. 초기 조건으로
 *
 * 구해야 하는 것
 * 연습 마친 후 격자에 있는 물고기 수
 *
 * 문제 입력
 * 첫째 줄 : 물고기 수 M, 상어가 마법 쓰는 횟수 S
 * 둘째 줄 ~ M개 줄 : 물고기 정보 fx, fy, d
 *  - d : 1 ~ 8로 표현, 각 방향은 9시방향부터 시계방향으로 회전하는 방향
 * 마지막 줄 : 상어의 위치 sx sy
 *
 * 제한 요소
 * 1 <= M <= 10
 * 1 <= S <= 100
 * 1 <= max(fish) <= 1_000_000
 *
 * 생각나는 풀이
 * 구현
 * 1. 복제과정
 *  - 16칸 순회하며 남은 물고기를 Queue에 복제
 * 2. 물고기 이동
 *  - 방향 판별
 *      델타배열 8방 확인
 *      - 범위 밖
 *      - 상어 칸
 *      - 물고기 냄새 칸 (물고기 냄새가 현재 시간 - 2보다 작다면 이동 가능)
 *      아니면 가고 8방 전부 확인했을땐 이동안함
 * 3. 상어 이동
 *  - 방향 판별
 *      사전순으로 64개 방향 전부 조사
 *          - 이동 가능하지 않거나
 *          - 물고기 개수가 적거나
 *          그러면 방향 갱신하지 않음
 *  - 방향 정하고 이동
 *      이동경로에 있는 모든 칸의 큐를 비움
 *      이동경로에 있는 물고기냄새를 현재 타임으로 갱신
 * 4. 복제한 큐에서 물고기 풀어넣음
 *
 * 구현해야 하는 기능
 * 1. 각 칸 저장할 16개의 큐 배열
 * 2. 복제한 물고기를 임시 저장할 큐
 * 3. 물고기의 이동 메서드
 *  - 물고기 이동용 큐
 * 4. 상어의 이동 메서드
 *
 */
public class BOJ_23290 {
    static int time;
    static int[][] fishSmell = new int[4][4];
    static ArrayList<Fish>[][] water = new ArrayList[4][4];
    static ArrayList<Fish> copyList = new ArrayList<>();
    static ArrayList<Fish> fishList = new ArrayList<>();
    static int[] fishDx = {0, -1, -1, -1, 0, 1, 1, 1}; // 9시방향 시작, 시계방향
    static int[] fishDy = {-1, -1, 0, 1, 1, 1, 0, -1}; // 9시방향 시작, 시계방향
    static int[] sharkDx = {-1, 0, 1, 0}; // 상좌하우
    static int[] sharkDy = {0, -1, 0, 1}; // 상좌하우
    static int[][] nextSharkMove = new int[3][2];
    static Node shark;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Fish extends Node {
        int dir;

        public Fish(int x, int y, int dir) {
            super(x, y);
            this.dir = dir;
        }

        public Fish clone() {
            return new Fish(this.x, this.y, this.dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                water[i][j] = new ArrayList<>();
                fishSmell[i][j] = -3;
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            fishList.add(new Fish(x, y, dir));
        }

        st = new StringTokenizer(br.readLine());
        shark = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        while (S-- > 0) {
            simulation();
        }


        System.out.println(fishList.size());
    }

    private static void simulation() {
//        System.out.println("#############"+time+"번째 수행#############");
//        System.out.println("--------초기상태--------");
//        print();
        copy();
        moveFish();
//        System.out.println("--------물고기 움직임--------");
//        print();
        moveShark();
//        System.out.println("-------상어 움직임---------");
//        print();
        paste();
//        System.out.println("-------복제후-------");
//        print();
        reset();
        time++;

//        System.out.println(shark);
    }

    private static void reset() {
        fishList.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Fish fish : water[i][j]) fishList.add(fish);
                water[i][j].clear();
            }
        }
    }

    private static void copy() {
        copyList.clear();
        for (Fish fish : fishList) copyList.add(fish.clone());
    }

    private static void moveFish() {
        for (Fish fish : fishList) {
            int nowDir = fish.dir;
            for (int nextDir = nowDir + 8; nextDir > nowDir; nextDir--) {
                int modDir = nextDir % 8;
                int nextX = fish.x + fishDx[modDir];
                int nextY = fish.y + fishDy[modDir];
                if (isInRange(nextX, nextY) && fishSmell[nextX][nextY] + 2 < time && !(shark.x == nextX && shark.y == nextY)) {
                    fish.x = nextX;
                    fish.y = nextY;
                    fish.dir = modDir;
                    break;
                }
            }
            water[fish.x][fish.y].add(fish);
        }
    }

    private static void moveShark() {
        int nowX = shark.x;
        int nowY = shark.y;
        int max = -1;

//        System.out.println("초기 상어 위치 : " + nowX + ", " + nowY);
        for (int i = 0; i < 4; i++) {
            int firstX = nowX + sharkDx[i];
            int firstY = nowY + sharkDy[i];
            if (!isInRange(firstX, firstY)) continue;
//            int now = water[firstX][firstY].size();
            for (int j = 0; j < 4; j++) {
//                if (Math.abs(i - j) == 2) continue;
                int secondX = firstX + sharkDx[j];
                int secondY = firstY + sharkDy[j];
                if (!isInRange(secondX, secondY)) continue;
//                now += water[secondX][secondY].size();
                for (int k = 0; k < 4; k++) {
//                    if (Math.abs(j - k) == 2) continue;
                    int thirdX = secondX + sharkDx[k];
                    int thirdY = secondY + sharkDy[k];
                    if (!isInRange(thirdX, thirdY)) continue;
                    int now = water[firstX][firstY].size() + water[secondX][secondY].size() + water[thirdX][thirdY].size();
//                    now += water[thirdX][thirdY].size();
                    if (firstX == thirdX && firstY == thirdY) now -= water[thirdX][thirdY].size();
//                    if (i == 3 && j == 3 && k == 2) {
//                        System.out.println(water[firstX][firstY].size());
//                        System.out.println(water[secondX][secondY].size());
//                        System.out.println(water[thirdX][thirdY].size());
//                        System.out.println(now);
//                    }
                    if (now > max) {
                        max = now;
                        nextSharkMove[0][0] = firstX;
                        nextSharkMove[0][1] = firstY;
                        nextSharkMove[1][0] = secondX;
                        nextSharkMove[1][1] = secondY;
                        nextSharkMove[2][0] = thirdX;
                        nextSharkMove[2][1] = thirdY;
//                        System.out.println("물고기 수 : " + max + " 방향 : " + i + " " + j + " " + k);
                    }
//                    now -= water[thirdX][thirdY].size();
                }
//                now -= water[secondX][secondY].size();
            }
        }

        shark.x = nextSharkMove[2][0];
        shark.y = nextSharkMove[2][1];
        for (int i = 0; i < 3; i++) {
            int x = nextSharkMove[i][0];
            int y = nextSharkMove[i][1];
//            System.out.println((i+1) + "번" + "상어 이동 경로 : " + x + ", " + y);
            if (!water[x][y].isEmpty()) {
                fishSmell[x][y] = time;
                water[x][y].clear();
            }
        }
    }

    private static void paste() {
        for (Fish fish : copyList) {
            water[fish.x][fish.y].add(fish);
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    private static void print() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(water[i][j].size() + " ");
            }
            System.out.println();
        }
    }
}
