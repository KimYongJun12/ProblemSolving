package Baekjoon.dataStructure.disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:10
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 일자로 되어 있는 건물, N개의 방은 일직선 상에 존재
 * 각 방에는 번호가 있음. 맨 왼쪽 방은 1 ~ 맨 오른쪽 방은 N
 * 각 방 사이에는 방을 구분하는 벽이 존재
 *
 * 빌런이 벽을 허무는 규칙
 *  1. x < y를 만족하는 두 방에 대해 x ~ y 사이의 모든 벽은 허문다
 *  2. 두 방 사이의 벽이 허물어지면 두 방은 하나의 방이 된다
 *  3. 이미 허물어진 벽이 존재하면 무시하고 다음 방을 허문다
 *  4. 1번 방의 왼쪽 벽과 N번 방의 오른쪽 벽은 허물지 않는다
 * 빌런은 허물기 작업을 M번 한다
 * M번 이후 방의 개수 출력
 *
 * 구해야 하는 것
 * M번 이후 방의 개수 출력
 *
 * 문제 입력
 * 첫째 줄 : 방의 개수 N
 * 둘째 줄 : 방을 허무는 횟수 M
 * 셋째 줄 ~ M개 줄 : 부술 방의 번호 x, y
 *
 * 제한 요소
 * 2 <= N <= 1_000_000
 * 0 <= M <= 5000
 *
 * 생각나는 풀이
 * 유니온 파인드같은데
 * x와 y의 부모가 다르면 union
 * 같으면 다음 과정
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_14595 {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (i == parent[i]) cnt++;
        }

        System.out.println(cnt);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        for (int i = y; i > x; i--) {
            if (parent[i] == x) return; // 중복된 연산 제거
            parent[i] = x;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
