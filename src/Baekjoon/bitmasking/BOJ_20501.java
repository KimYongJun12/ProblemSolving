package Baekjoon.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] friendRelationship = new int[2001][64]; // int = 2^32, 32 * 64 >= 2000 (2000비트 사용 가능)

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();

            for (int j = 1; j <= N; j++) {
                char now = s.charAt(j - 1);
                /**
                 * 친구 관계라면 친구 관계 배열에 비트로 저장
                 * 배열의 1칸 = int형 최대 크기 32비트까지 저장 가능
                 * friendRelationship[본인 번호][상대 번호를 기준값(32)으로 나눈 몫] = 상대방과 친구 관계를 true로 변경
                 * 배열 1칸에 32명씩 친구 관계가 저장되어 있다고 보면 된다.
                 * |= 연산은 기존 값과 우측 값을 비트 OR 연산한 결과를 대입한다는 의미
                 */
                if (now == '1') friendRelationship[i][j / 32] |= (1 << (j % 32));
            }
        }

        int Q = Integer.parseInt(br.readLine());

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long sum = 0;
            for (int i = 0; i < 64; i++) {
                /**
                 * 0 ~ 2048(32 * 64)의 범위를 탐색하여 a와 b에 해당하는 배열 값을 비트 AND 연산하면
                 * 같은 자리에 1이 있는 경우만 남는다. 즉 공통된 친구만 남길 수 있다.
                 * Integer.bitCount = int형 정수를 이진수로 변환했을 때 1의 개수를 구하는 메소드
                 */
                sum += Integer.bitCount(friendRelationship[a][i] & friendRelationship[b][i]);
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);

    }
}