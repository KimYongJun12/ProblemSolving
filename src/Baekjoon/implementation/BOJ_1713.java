package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1713 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> frames = new ArrayList<>();
        int[] votesOfCandidate = new int[101];
        int[] numOfVotes = new int[M + 1];
        numOfVotes[0] = M;
        int min = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int nowVote = Integer.parseInt(st.nextToken());
            if (frames.contains(nowVote)) {
                numOfVotes[votesOfCandidate[nowVote]]--;
                votesOfCandidate[nowVote]++;
                numOfVotes[votesOfCandidate[nowVote]]++;
                if (numOfVotes[min] == 0) min++;
                continue;
            }

            frames.add(nowVote);
            numOfVotes[votesOfCandidate[nowVote]]--;
            votesOfCandidate[nowVote]++;
            numOfVotes[votesOfCandidate[nowVote]]++;
            if (frames.size() == N + 1) {
                for (int j = 0; j < frames.size(); j++) {
                    int nowCandidate = frames.get(j);
                    if (votesOfCandidate[nowCandidate] == min) {
                        numOfVotes[min]--;
                        numOfVotes[0]++;
                        votesOfCandidate[nowCandidate] = 0;
                        frames.remove(j);
                        break;
                    }
                }
            }
            min = 1;
        }

        Collections.sort(frames);
        StringBuilder sb = new StringBuilder();
        for (int value : frames) sb.append(value).append(' ');
        System.out.println(sb);
    }
}
