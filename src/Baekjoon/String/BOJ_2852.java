package Baekjoon.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2852 {
    static class goal {
        int time, team;

        public goal(int time, int team) {
            this.time = time;
            this.team = team;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        goal[] goals = new goal[N + 2];
        goals[0] = new goal(0, 0);
        goals[N + 1] = new goal(48*60, 0);

        int prev = 0;
        int scoreA = 0;
        int scoreB = 0;

        int aTime = 0;
        int bTime = 0;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " :");
            int team = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

            if (scoreA > scoreB) {
                aTime += time - prev;
            } else if (scoreA < scoreB) {
                bTime += time - prev;
            }

            if (team == 1) {
                scoreA++;
            } else scoreB++;

            prev = time;
        }

        int remainTime = 48*60 - prev;
        if (scoreA > scoreB) {
            aTime += remainTime;
        } else if (scoreA < scoreB) {
            bTime += remainTime;
        }
        System.out.printf("%02d:%02d\n%02d:%02d", aTime / 60, aTime % 60, bTime / 60, bTime % 60);
    }
}
