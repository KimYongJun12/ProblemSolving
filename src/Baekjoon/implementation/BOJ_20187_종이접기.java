package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 4:40
 * 풀이 완료 : 6:00
 * 풀이 시간 : 80분
 *
 * 문제 해석
 * 종이 접기
 * 종이를 접는 경우의 수
 * D - 아래쪽으로 반으로 접음
 * U - 윗쪽으로 반으로 접음
 * R - 오른쪽으로 반으로 접음
 * L - 왼쪽으로 반으로 접음
 *
 * 한 변의 길이가 2^k인 정사각형 종이를 가로 k번, 세로 k번 접으면 가로 세로 길이가 1인 정사각형 됨
 * 그 후 정사각형 네 모퉁이 중 하나에 구멍 뚫은 후 모두 펼치면 2^2k의 구멍이 생김
 * 구멍의 위치 출력
 *
 * 구해야 하는 것
 * 2^k * 2^k의 종이를 모두 접어 만든 1 * 1 칸의 종이에 구멍 뚫고 펼치면 나오는 모든 구멍 위치
 *
 * 문제 입력
 * 첫째 줄 : k
 * 둘째 줄 : 종이를 접는 순서 2k번
 * 셋째 줄 : 1 * 1 종이에서 구멍 뚫는 위치 h
 *
 * 제한 요소
 * 가로 세로 접는 횟수는 각각 k번
 * 1 <= k <= 8
 *
 * 생각나는 풀이
 * 입력의 역순으로 펼치면서 배열을 채워나감
 * 최악의 경우 : 8번을 한 쪽으로만 접는 경우
 *  -> 필요한 배열 크기 : 2^8 * 2^8 = 2^16 = int[65536] = 262144byte < 512MB
 * 입력에 따른 기능
 * startX = 현재 채워진 배열의 시작 행, startY = 현재 채워진 배열의 시작 열
 * endX = 현재 채워진 배열의 끝 행, endY = 현재 채워진 배열의 끝 열
 * height = 현재 배열의 높이, width = 현재 배열의 너비
 * U - 현재 채워진 배열의 크기만큼 반복하며 (endX + 1, startY) ~ (endX + height, endY)의 값을 채움, 상하반전으로, 0 <-> 2, 1 <-> 3
 * D - 현재 채워진 배열의 크기만큼 반복하며 (startX - height, startY) ~ (startX - 1, endY)의 값을 채움, 상하반전으로 0 <-> 2, 1 <-> 3
 * L - 현재 채워진 배열의 크기만큼 반복하며 (startX, endY + 1) ~ (endX, endY + width)의 값을 채움, 좌우반전으로 0 <-> 1, 2 <-> 3
 * R - 현재 채워진 배열의 크기만큼 반복하며 (startX, startY - width) ~ (endX, startY - 1)의 값을 채움, 좌우반전으로 0 <-> 1, 2 <-> 3
 *
 * 구현해야 하는 기능
 * 1. 종이의 상태를 기록할 2차원 배열
 * 2. 접는 순서를 역순으로 저장할 스택
 * 3. 종이의 시작 지점과 종료 지점을 저장할 변수 startX, startY, endX, endY
 * 4. 각 방향으로 펼치는 기능
 */
public class BOJ_20187_종이접기 {
    static int K;
    static int startX, startY, endX, endY; // 전체 배열에서 종이의 시작 좌표, 끝 좌표
    static int height = 1, width = 1; // 현재까지 종이의 세로/가로 크기
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        paper = new int[1 << (K + 1)][1 << (K + 1)]; // 최악의 조건일 때에도 ArrayIndexOutOfBoundsException 안뜨도록 크게 잡아줌
        startX = startY = endX = endY = paper.length / 2 - 1; // 중앙에 가장 가까운 좌표로 시작 지점 설정
        ArrayDeque<Character> stack = new ArrayDeque<>(); // 순서를 역순으로 처리할 스택
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < (K << 1); i++) {
            stack.push(st.nextToken().charAt(0)); // 스택에 접는 순서 저장
        }

        paper[startX][startY] = Integer.parseInt(br.readLine()); // 1칸짜리 종이 초기값 할당
        while (!stack.isEmpty()) { // 역순으로 펼치기
            unfold(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                sb.append(paper[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 펼치는 기능
    private static void unfold(char direction) {
        switch (direction) {
            case 'U' :
                flipU(); // 위로 접은 경우 => 아래로 펼침
                break;
            case 'D' :
                flipD(); // 아래로 접은 경우 => 위로 펼침
                break;
            case 'R' :
                flipR(); // 오른쪽으로 접은 경우 => 왼쪽으로 펼침
                break;
            case 'L' :
                flipL(); // 왼쪽으로 접은 경우 => 오른쪽으로 펼침
                break;
        }
    }

    // 위나 아래로 펼치는 경우 상하반전이어야 함
    private static void flipU() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                paper[endX + height - i][startY + j] = inverseUD(paper[startX + i][startY + j]); // 상하반전
            }
        }
        endX += height; // 배열에서 종이의 끝 지점 갱신
        height *= 2; // 상하로 펼쳤으므로 종이 세로 크기 2배로
    }

    // 위나 아래로 펼치는 경우 상하반전이어야 함
    private static void flipD() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                paper[startX - 1 - i][startY + j] = inverseUD(paper[startX + i][startY + j]); // 상하반전
            }
        }
        startX -= height; // 배열에서 종이의 시작 지점 갱신
        height *= 2; // 상하로 펼쳤으므로 종이 세로 크기 2배로
    }
    private static void flipR() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                paper[startX + i][startY - 1 - j] = inverseRL(paper[startX + i][startY + j]); // 좌우반전
            }
        }
        startY -= width; // 배열에서 종이의 시작 지점 갱신
        width *= 2; // 좌우로 펼쳤으므로 종이 가로 크기 2배로
    }

    private static void flipL() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                paper[startX + i][endY + width - j] = inverseRL(paper[startX + i][startY + j]); // 좌우반전
            }
        }
        endY += width; // 배열에서 종이의 끝 지점 갱신
        width *= 2; // 좌우로 펼쳤으므로 종이 가로 크기 2배로
    }

    // 구멍 뚫린 위치 상하반전 메서드
    private static int inverseUD(int prev) {
        switch (prev) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
                return 1;
        }
        return 0;
    }

    // 구멍 뚫린 위치 좌우반전 메서드
    private static int inverseRL(int prev) {
        switch (prev) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }
        return 0;
    }
}
