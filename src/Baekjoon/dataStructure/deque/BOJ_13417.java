package Baekjoon.dataStructure.deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:35
 * 풀이 완료 : 10:43
 * 풀이 시간 : 8분
 *
 * 문제 해석
 * N장의 카드, 각 카드에는 알파벳이 적혀 있음
 * 가장 왼쪽의 카드부터 차례대로 한 장씩 가져올 수 있음
 * 가장 처음에 가져온 카드는 자신 앞에 둠
 * 그 다음부터는 가져온 카드를 카드의 가장 왼쪽 혹은 가장 오른쪽에 둠
 * 만들 수 있는 문자열 중 가장 사전순으로 빠른 문자열을 구해야 함
 *
 * 구해야 하는 것
 * 만들 수 있는 문자열 중 가장 사전순으로 빠른 문자열을 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : 테스트케이스 수 T
 * 테케당 입력
 * 첫째 줄 : 문자의 수 N
 * 둘째 줄 : 문자의 정보 N개
 *
 * 제한 요소
 * 1 <= N <= 1000
 *
 * 생각나는 풀이
 * Deque 사용해서 가장 앞 문자 비교, 가장 앞보다 현재 글자가 앞이면 앞에, 그렇지 않으면 뒤에 붙임
 *
 * 구현해야 하는 기능
 * 1. 문자 저장할 deque
 * 2. 문자 비교
 *  2-1. 현재 문자 < deque.peekFirst 이면 deque.offerFirst
 *  2-2. 그렇지 않으면 deque.offerLast
 */
public class BOJ_13417 {
    static class Node {
        char value;
        Node next;

        public Node(char value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Node head = new Node('0', null);

            head.next = new Node(st.nextToken().charAt(0), null);
            Node tail = head.next;
            for (int i = 1; i < N; i++) {
                char now = st.nextToken().charAt(0);
                if (now <= head.next.value) {
                    head.next = new Node(now, head.next);
                } else {
                    tail.next = new Node(now, null);
                    tail = tail.next;
                }
            }

            for (Node node = head.next; node != null; node = node.next) sb.append(node.value);
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
