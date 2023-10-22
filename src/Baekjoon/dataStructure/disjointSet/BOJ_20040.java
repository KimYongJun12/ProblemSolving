package Baekjoon.dataStructure.disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:51
 * 풀이 완료 : 8:57
 * 풀이 시간 :
 *
 * 문제 해석
 * 사이클 게임
 * 0 ~ N - 1까지 번호가 있는 점 N개 주어짐
 * 매 차례마다 플레이어가 두 점을 선택해서 선분을 그음
 * 사이클 발생하면 종료
 *
 * 구해야 하는 것
 * 점 개수 N과 게임 턴 M이 주어질 때 사이클이 발생했는지
 * 발생했다면 사이클이 몇 번째 턴에 발생했는지
 *
 * 문제 입력
 * 첫째 줄 : 점의 개수 N, 진행된 턴 M
 * 둘째 줄 ~ M개 줄 : i번째 차례에 해당 플레이어가 선택한 두 점
 *
 * 제한 요소
 * 3 <= N <= 500_000
 * 3 <= M <= 1_000_000
 *
 * 생각나는 풀이
 * 유니온 파인드
 *
 * 구현해야 하는 기능
 * 1. 유니온 파인드
 */
public class BOJ_20040 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int i = 1; i < N; i++) parent[i] = i;

        int ans = 0;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!union(a, b)) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
