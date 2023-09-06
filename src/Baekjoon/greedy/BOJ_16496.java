package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:45
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 음이 아닌 정수 N개 들어 있는 리스트가 주어짐
 * 리스트에 포함된 수를 나열하여 만들 수 있는 가장 큰 수를 구해야 함
 *
 * 구해야 하는 것
 * 리스트에 포함된 수를 나열하여 만들 수 있는 가장 큰 수를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : N
 * 둘째 줄 : N개의 정수
 *
 * 제한 요소
 * 1 <= N <= 1000
 * 0 <= Num[i] <= 100_000_000
 *
 * 생각나는 풀이
 * 정렬
 * 그리디
 * => 각 숫자를 반복하여 10자리 수를 만들어야 함
 * 1 == 11 == 111 == 1111 == 11111
 * 12 == 1212 == 121212 == 12121212
 * 123 < 1234, 1435 < 1444
 * 구현해야 하는 기능
 *
 */
public class BOJ_16496 {
    static class Number implements Comparable<Number> {
        String n;
    StringBuilder tenLengthNum = new StringBuilder();

        public Number(String n) {
            this.n = n;
            for (int i = 0; i < 10; i++) tenLengthNum.append(n.charAt(i % n.length()));

        }
        @Override
        public int compareTo(Number o) {
            long a = Long.parseLong(this.tenLengthNum.toString());
            long b = Long.parseLong(o.tenLengthNum.toString());
            return Long.compare(b, a);
        }
}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Number[] numbers = new Number[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = new Number(st.nextToken());
        }
        Arrays.sort(numbers);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(numbers[i].n);
            if (i == 0 && numbers[i].n.equals("0")) break;
        }

        System.out.println(sb);
    }
}
