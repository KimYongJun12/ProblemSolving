package Baekjoon.graph.tree.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:08
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 각 노드가 자식을 최대 K개 가질 수 있는 K진 트리
 * 총 N개의 노드로 이루어진 K진 트리가 주어질 때
 * 두 노드 x와 y 사이의 거리를 구해야 함
 *
 * 구해야 하는 것
 * Q번 쿼리에서 두 노드 x와 y 사이의 거리를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : N, K, Q
 * 둘째 줄 ~ Q개 줄 : 거리 구해야 하는 두 노드 x, y
 *
 * 제한 요소
 * 1 <= N <= 10^15
 * 1 <= K <= 1000
 *
 * 생각나는 풀이
 * 노드 개수가 10^15면 순회로는 못풀듯
 * 각 깊이에 따른 노드 수
 * 0 : 1
 * 1 : K
 * 2 : K * K
 * 3 : K * K * K
 * i : K^i
 *
 * K = 1일 때 제외하곤 시간초과 안날듯
 * 부모 노드 구하는 방법
 * ((idx - 2) / k) + 1
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_11812 {
    static long N;
    static int K, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long ans;
            if (K == 1) {
                ans = Math.abs(a - b);
            } else {
                ans = findLCA(Math.min(a, b), Math.max(a, b));
            }
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }

    private static long findLCA(long a, long b) {
        int aDepth = getDepth(a);
        int bDepth = getDepth(b);
        int distance = 0;

        while (aDepth != bDepth) {
            b = (b - 2) / K + 1;
            bDepth--;
            distance++;
        }

        while (a != b) {
            a = (a - 2) / K + 1;
            b = (b - 2) / K + 1;
            distance += 2;
        }

        return distance;
    }

    private static int getDepth(long x) {
        int depth = 0;
        long sum = 1;

        while (sum < x) {
            sum += Math.pow(K, ++depth);
        }

        return depth;
    }
}
