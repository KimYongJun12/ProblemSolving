package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:26
 * 풀이 완료 : 8:47
 * 풀이 시간 :
 *
 * 문제 해석
 * 정수 S를 T로 바꿔아 햠
 * 가능한 연산은 4가지
 * 1. s = s + s
 * 2. s = s - s
 * 3. s = s * s
 * 4. s = s / s (s != 0)
 *
 * 구해야 하는 것
 * S -> T 최소 횟수일 때 연산 과정
 *
 * 문제 입력
 * 첫째 줄 : S T
 *
 * 제한 요소
 * 1 <= s, t <= 10^9
 *
 * 생각나는 풀이
 * bfs인데 방문처리가 관건
 * set써야될듯
 * Queue에 숫자만 넣으면 과정 트래킹이 안됨
 * class 만들어서 넣자
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_14395 {
    static long S, T;
    static HashSet<Long> visited = new HashSet<>();

    static class Num {
        long n;
        String oper;

        public Num(long n, String oper) {
            this.n = n;
            this.oper = oper;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        if (S == T) {
            System.out.println(0);
        } else {
            System.out.println(bfs(S));
        }
    }

    private static String bfs(long s) {
        Queue<Num> q = new ArrayDeque<>();
        visited.add(s);
        q.offer(new Num(s, ""));

        while (!q.isEmpty()) {
            Num now = q.poll();

            if (now.n == T) return now.oper;
            long n = now.n;
            if (!visited.contains(n * n)) {
                visited.add(n * n);
                q.offer(new Num(n * n, now.oper + '*'));

            }

            if (!visited.contains(n + n)) {
                visited.add(n + n);
                q.offer(new Num(n + n, now.oper + '+'));
            }
            if (!visited.contains(n - n)) {
                visited.add(n - n);
                q.offer(new Num(n - n, now.oper + '-'));
            }

            if (n != 0L && !visited.contains(n / n)) {
                visited.add(n / n);
                q.offer(new Num(n / n, now.oper + '/'));
            }
        }
        return "-1";
    }
}
