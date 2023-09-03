package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:17
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 스브러스는 다른 아이의 사탕을 뺏음
 * 한 아이의 사탕을 뺏고, 그 아이의 모든 친구의 사탕을 뺏음
 * 사탕을 빼앗긴 아이들은 울고, 우는 아이가 K명 이상이 되면 어른들에게 들킴
 *
 * 구해야 하는 것
 * 어른들에게 들키지 않고 뺏을 수 있는 최대 사탕의 양
 *
 * 문제 입력
 * 첫째 줄 : 아이의 수 N, 친구 관계 수 M, 어른들에게 들키는 조건 K
 * 둘째 줄 : 아이들이 받은 사탕의 수 C가 N번 주어짐
 * 셋째 줄 ~ M개 줄 : 친구 관계 a, b
 *
 * 제한 요소
 * 1 <= N <= 30000
 * 0 <= M <= 100_000
 * 1 <= K <= min(N, 3000)
 * 1 <= C <= 10000
 * 1 <= a, b <= N, a != b
 *
 * 생각나는 풀이
 * 그래프 탐색으로 같은 그룹의 아이들 수와 얻을 수 있는 사탕을 매칭
 * 냅색
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 그래프 구현
 * 2. 그래프 탐색으로 친구 그룹과 사탕 개수 매칭시켜 각각의 배열 같은 인덱스에 저장
 *  --> 탐색이 아니라 union-find 써야 시간 내에 가능
 * 3. 배낭 문제 해결
 *
 */
public class BOJ_20303 {
    static int N, K;
    static int[] candy, parent, people;
    static int[][] dp;
    static ArrayList<Pair> pairs = new ArrayList<>();
    static class Pair {
        int weight, value;

        public Pair(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        parent = new int[N + 1];
        people = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
            people[i] = 1;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        makePairs();

        dp = new int[pairs.size() + 1][K];
        for (int i = 0; i < pairs.size(); i++) {
            int weight = pairs.get(i).weight;
            int value = pairs.get(i).value;

            for (int j = 0; j < K; j++) {
                if (weight <= j) {
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - weight] + value);
                } else dp[i + 1][j] = dp[i][j];
            }
        }

        System.out.println(dp[pairs.size()][K - 1]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else parent[a] = b;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void makePairs() {
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) continue;
            int p = find(i);
            candy[p] += candy[i];
            people[p] += people[i];
        }

        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) {
                pairs.add(new Pair(people[i], candy[i]));
            }
        }
    }

}
