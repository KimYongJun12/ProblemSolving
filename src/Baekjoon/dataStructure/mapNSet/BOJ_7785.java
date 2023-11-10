package Baekjoon.dataStructure.mapNSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 풀이 시작 :
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 *
 * 구해야 하는 것
 *
 * 문제 입력
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_7785 {
    static final String ENTER = "enter";
    static final String LEAVE = "leave";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TreeSet<String> set = new TreeSet<>((o1, o2) -> o2.compareTo(o1));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String log = st.nextToken();

            if (ENTER.equals(log)) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : set) {
            sb.append(s).append('\n');
        }
        System.out.println(sb);
    }
}
