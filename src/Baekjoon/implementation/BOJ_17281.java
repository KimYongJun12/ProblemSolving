package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17281 {
	static int N, maxScore;
	static int[][] innings;
	static boolean[] base = new boolean[4];
	static int[] permutation = new int[8];
	static Batter[] batters = new Batter[9];

	static class Batter {
		int idx;
		Batter next;

		public Batter(int idx, Batter next) {
			this.idx = idx;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		innings = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i <= 8; i++) {
			batters[i] = new Batter(i, null);
			if (i == 0) continue;
			permutation[i - 1] = i;
		}

		do {
			int start = permutation[5];
			makeCircularLinkedList();
			simulation(start);
		} while (np());

		System.out.println(maxScore);
	}

	private static boolean np() {
		int i = 7;
		while (i > 0 && permutation[i] <= permutation[i - 1]) i--;
		if (i == 0) return false;
		int j = 7;
		while (permutation[j] <= permutation[i - 1]) j--;
		swap(i - 1, j);
		int k = 7;
		while (i < k) {
			swap(i++, k--);
		}
		return true;
	}

	private static void swap(int i, int j) {
		int temp = permutation[i];
		permutation[i] = permutation[j];
		permutation[j] = temp;
	}

	private static void simulation(int start) {
		int nowScore = 0;
		Batter now = batters[start];
		for (int i = 0; i < N; i++) {
			int out = 0;
			Arrays.fill(base, false);
			while (out < 3) {
				int result = innings[i][now.idx];
				now = now.next;
				if (result == 0) {
					out++;
				} else {
					nowScore += hitBall(result);
				}
			}
		}

		maxScore = Math.max(maxScore, nowScore);
	}

	private static int hitBall(int hitCount) {
		int score = 0;
		base[0] = true;
		for (int j = 0; j < hitCount; j++) {
			rotate();
			if (base[0]) {
				score++;
				base[0] = false;
			}
		}
		return score;
	}

	private static void rotate() {
		boolean third = base[3];
		for (int i = 3; i > 0; i--) {
			base[i] = base[i - 1];
		}
		base[0] = third;
	}

	private static void makeCircularLinkedList() {
		Batter prev = batters[permutation[7]];
		Batter next = batters[permutation[0]];
		for (int i = 0; i < 8; i++) {
			batters[permutation[i]].next = batters[permutation[(i + 1) % 8]];
		}
		prev.next = batters[0];
		batters[0].next = next;
	}
}