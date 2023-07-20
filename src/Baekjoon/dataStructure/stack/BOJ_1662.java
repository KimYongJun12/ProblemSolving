package Baekjoon.dataStructure.stack;
/*
입력 : 숫자, (, )로 이루어진 최대 길이 50인 문자열
구해야 할 것 : 압축되지 않은 문자열의 길이
압축 : K(Q), K = 1자리 정수, Q = 0자리 이상의 문자열
Q라는 문자열이 K번 반복된다는 의미
생각나는 풀이 : 괄호문제는 스택 국룰
현재 자리 문자의 종류
1. 숫자
    cnt++
2. (
    배수 스택에 바로 전 숫자 push
    cnt = 0
3. )
    현재 깊이에서 숫자 개수 = (괄호 안 숫자 개수 * 배수) + 괄호 들어가기 전 저장해 놨던 개수
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> number = new Stack<>();
        Stack<Integer> multiple = new Stack<>();
        String input = br.readLine();

        int cnt = 0;

        for (int i = 0; i < input.length(); i++) {
            char now = input.charAt(i);
            if (now == '(') {
                number.push(cnt - 1);
                multiple.push(input.charAt(i - 1) - '0');
                cnt = 0;
            } else if (now == ')') {
                cnt = (cnt * multiple.pop()) + number.pop();
            } else {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
