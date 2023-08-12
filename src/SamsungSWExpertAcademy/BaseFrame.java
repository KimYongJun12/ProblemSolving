package SamsungSWExpertAcademy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BaseFrame {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {



            sb.append('#').append(test_case).append(' ').append("").append('\n');
        }
    }
}
