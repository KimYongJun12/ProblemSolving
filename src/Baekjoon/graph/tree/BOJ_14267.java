package Baekjoon.graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:33
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 1번이 루트
 * 칭찬 받은 사람은 모든 부하에게 칭찬
 * 직속 상사의 번호는 자신보다 작음
 *
 * 구해야 하는 것
 * 모든 사람 각각이 칭찬 받은 정도
 *
 * 문제 입력
 * 첫째 줄 : 직원 수 N, 칭찬 횟수 M
 * 둘째 줄 : 직속 상사
 * 셋쩨 줄 ~ M개 줄 : 칭찬 받은 직원 번호 i, 칭찬 수치 w
 *
 * 제한 요소
 * 2 <= N, M <= 100000
 * 2 <= i <= N
 * 1 <= w <= 1000
 *
 * 생각나는 풀이
 * 루트와 직속 상사가 주어지므로 트리 생성
 * m번만큼 i번 노드에 초기값 채워줌
 * 루트(1번)부터 dfs돌면서 누적 합
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 트리
 * 2. 각 노드의 초기값 설정
 * 3. dfs
 *
 */
public class BOJ_14267 {
    static int N, M;
    static ArrayList<Integer>[] tree;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        value = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken(); // 첫 번째는 어차피 -1이므로 쓸모없음
        for (int i = 2; i <= N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            tree[parent].add(i);
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            value[i] += w;
        }

        dfs(1, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(value[i]).append(' ');
        }
        System.out.println(sb);
    }

    private static void dfs(int now, int parent) {
        value[now] += value[parent];
        for (int child : tree[now]) {
            dfs(child, now);
        }
    }
}
