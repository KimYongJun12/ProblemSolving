package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:00
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 게리맨더링
 * N개의 구역으로 나뉘어 있고 구역은 1 ~ N번까지 번호가 매겨짐
 * 구역을 두 개의 선거구로 나눠야 함, 각 구역은 반드시 두 선거구 중 하나에 포함되어야 함
 * 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어야 함.
 * 두 선거구에 포함된 인구 차이를 최소로 해야 함.
 *
 * 구해야 하는 것
 * 시의 정보가 주어졌을 때 인구 차이의 최솟값을 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : N
 * 둘째 줄 : 구역의 인구 1 ~ N번
 * 셋째 줄 ~ N개 줄 : 각 구역과 인접한 구역의 정보
 *  - 첫 번째 정수 : 그 구역과 인접한 구역의 수, 그 뒤로 인접한 구역의 번호
 *
 * 제한 요소
 * 2 <= N <= 10
 * 1 <= P[i] <= 100
 *
 * 생각나는 풀이
 * 부분집합을 1 ~ N/2개 뽑는 경우의 수만큼 뽑고
 * 그래프 탐색해서 한 번에 탐색 가능하다면 최솟값 갱신한다
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 인구 수 저장
 * 2. 입력에 따른 그래프 생성
 * 3. 부분집합 만드는 메서드
 * 4. 그래프 탐색, 매 조합마다 해야 함
 *  4-1. boolean형 배열을 이용해 같은 지역인지 판별하며 이동 가능한지 탐색
 *  4-2. 이동 가능하다면 차이 값 구하기
 *
 */
public class BOJ_17471 {
    static int N, minDiff = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static boolean[] isSelected;
    static int[] population;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        isSelected = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        population = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        generatePowerSet(0);
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    private static void generatePowerSet(int depth) {
        if (depth == N) {
            boolean isTwo = false;
            for (int i = 1; i < N; i++) {
                isTwo = isSelected[i - 1] ^ isSelected[i];
                if (isTwo) break;
            }

            if (isTwo) {
                if (bfs()) {
                    getDiff();
                }
            }
            return;
        }

        isSelected[depth] = true;
        generatePowerSet(depth + 1);
        isSelected[depth] = false;
        generatePowerSet(depth + 1);
    }

    private static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int sT = -1, sF = -1;
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) sT = i;
            else sF = i;
            if (sT != -1 && sF != -1) break;
        }

        visited[sT] = visited[sF] = true;
        q.offer(sT);
        q.offer(sF);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if ((isSelected[now]^isSelected[next]) || visited[next]) continue;
                q.offer(next);
                visited[next] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }

    private static void getDiff() {
        int aSum = 0, bSum = 0;
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) aSum += population[i];
            else bSum += population[i];
        }

        minDiff = Math.min(minDiff, Math.abs(aSum - bSum));
    }

}
