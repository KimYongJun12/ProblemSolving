package SamsungSWExpertAcademy.SWEA_1204;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_1204/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int tcNum = Integer.parseInt(br.readLine());
            int[] count = new int[101];
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int i = 0; i < 1000; i++) {
                count[Integer.parseInt(stringTokenizer.nextToken())]++;
            }

            int idx = 0, maxCnt = 0;
            for (int i = 100; i >= 0; i--) {
                if (maxCnt < count[i]) {
                    maxCnt = count[i];
                    idx = i;
                }
            }
            stringBuilder.append('#').append(tcNum).append(' ').append(idx).append('\n');
        }

        System.out.println(stringBuilder);
    }
}
