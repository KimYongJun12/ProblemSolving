package Baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 풀이 시작 : 10:37
 * 풀이 완료 : 11:03
 * 풀이 시간 : 26분
 *
 * 문제 해석
 * 길이가 N짜리 수식이 있음
 * 수식은 한 자리 정수와 연산자로 이루어져 있음
 *  - 연산자는 + - *만 있음
 * 연산자 우선순위는 모두 동일
 * 무조건 왼쪽에서부터 계산
 * 하지만 괄호가 있음
 *  - 괄호 먼저 처리해야 함
 * 괄호는 중첩 불가능, 괄호 안에는 연산자 하나만 가능
 *
 * 구해야 하는 것
 * 수식이 주어졌을 때 괄호를 적절히 추가해 만들 수 있는 최대값
 *
 * 문제 입력
 * 첫째 줄 : 수식의 길이 N
 * 둘째 줄 : 수식
 *
 * 제한 요소
 * 1 <= N <= 19
 * N % 2 == 1
 * 0 <= P[i] <= 9
 *
 * 생각나는 풀이
 * 부분집합 만드는 것처럼 괄호 있을 때, 없을 때 계산 값을 모두 생각하며 끝까지 계산
 * 괄호를 넣을 수 있는 경우
 *  - 현재 수 뒤에 숫자가 있는 경우
 *      => 현재 수와 뒤의 수를 괄호로 계산하고 그 값을 다다음 수로 넘겨줌
 *
 * 구현해야 하는 기능
 * 1. 입력에 따라 수와 연산자를 받음
 *  1.1 수를 저장하는 배열
 *  1.2 연산자를 저장하는 배열
 * 2. 재귀로 끝까지 돌면서 다 돌았으면 최댓값 갱신
 */
public class BOJ_16637_2 {
    static int N, max = Integer.MIN_VALUE;
    static int[] numbers;
    static char[] operators;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N / 2 + 1];
        operators = new char[N / 2];
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            if ((i & 1) == 0) numbers[i >> 1] = input.charAt(i) - '0';
            else operators[i >> 1] = input.charAt(i);
        }

        N = N >> 1;
        findMaxOutPut(0, numbers[0]);
        System.out.println(max);
    }

    private static void findMaxOutPut(int depth, int sum) {
        if (depth == N) {
            max = Math.max(max, sum);
            return;
        }

        findMaxOutPut(depth + 1, calc(sum, operators[depth], numbers[depth + 1]));
        if (depth < N - 1) {
            int now = calc(numbers[depth + 1], operators[depth + 1], numbers[depth + 2]);
            findMaxOutPut(depth + 2, calc(sum, operators[depth], now));
        }
    }

    private static int calc(int a, char c, int b) {
        switch (c) {
            case '+' : return a + b;
            case '*' : return a * b;
            case '-' : return a - b;
        }
        return 0;
    }
}
