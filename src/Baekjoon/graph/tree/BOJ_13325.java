package Baekjoon.graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:50
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 각 간선이 양의 가중치가 있는 높이 K인 포화이진트리가 있을 때
 * 루트에서 어떤 리프까지의 거리는 루트에서 그 리프까지 거치는 모든 간선 가중치의 합이다
 * 어떤 에지들의 가중치를 증가시켜 루트에서 모든 리프까지의 거리를 같게 하고 가중치의 합이 최소가 되게 해야 한다
 *
 * 구해야 하는 것
 * 모든 리프까지의 거리를 같게 할 때 가중치 합의 최솟값
 *
 * 문제 입력
 * 첫째 줄 : 트리 높이 K
 * 둘째 줄 : 모든 간선의 가중치
 *  - 루트에서 가까운 순, 같다면 왼쪽, 오른쪽 순
 *
 * 제한 요소
 * 1 <= K <= 20
 * 1 <= W[i] <= 1000
 *
 * 생각나는 풀이
 * 형제 노드는 반드시 같은 값을 가져야 함 => 루트 ~ 부모는 동일한 경로이기 때문
 * 합을 가장 적게 올리는 방법은 가능한 루트에 가까운 노드를 바꾸는 것
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_13325 {
    static int K, treeSize, ans;
    static int[] weight;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        treeSize = (1 << (K + 1)) - 1;
        weight = new int[treeSize + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= treeSize; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1);
        System.out.println(ans);
    }

    private static int dfs(int idx) {
        if (idx << 1 >= treeSize) { // 리프 노드
            ans += weight[idx]; // 답에 더해줌
            return weight[idx]; // 리프 노드값 반환
        }
        int left = dfs(idx << 1); // 왼쪽 자식 노드
        int right = dfs((idx << 1) + 1); // 오른쪽 자식 노드

        ans += weight[idx] + Math.abs(left - right); // 자식 둘 중 작은 노드를 큰 노드까지 증가시켜야 함
        return weight[idx] += Math.max(left, right);
    }
}
