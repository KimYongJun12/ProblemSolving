package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 풀이 시작 : 2:40
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * N * N 격자
 * N은 항상 홀수
 * 마법사 상어는 중앙에 있음
 * 일부 칸과 칸 사이에는 벽이 설치됨
 *
 * 1. 가장 처음에 상어가 있는 칸을 제외한 나머지 칸에는 구슬이 하나 들어갈 수 있음.
 * 	구슬은 1번, 2번, 3번구슬이 있음
 * 같은 번호를 가진 구슬이 연속한 칸에 있으면 그 구슬을 연속한 구슬이라 함
 *
 * 2. 블리자드 마법을 수행하려면 방향 d와 거리 s를 정해야 함
 * 방향 : 총 4가지  상하좌우 (1, 2, 3, 4)
 * d방향으로 거리가 s 이하인 모든 칸에 있는 구슬이 파괴되고 빈 칸이 됨
 * 만약 어떤 칸 A의 번호보다 번호가 한 칸 작은 칸이 빈 칸이면 A에 있는 구슬은 빈 칸으로 이동함
 * 더이상 구슬이 이동하지 않을 때까지 반복됨
 *
 * 3. 구슬이 폭발하는 단계
 * 4개 이상 연속하는 구슬이 있을 때 발생
 * 구슬이 폭발하고 나면 또다시 빈 칸 채움
 * 폭발하는 구슬이 없을 때까지 반복됨
 *
 * 4. 구슬 변화 단계
 * 연속하는 구슬을 그룹이라 함
 * 하나의 그룹은 구슬 A와 B로 변한다
 * 그룹의 사이즈에 해당하는 구슬, 그룹을 구성하는 구슬로 나눠짐
 *
 *
 * 구해야 하는 것
 * 블리자드 M번 수행했을 때 1 * 폭발한 1번 구슬 개수 + 2 * 폭발한 2번 구슬 개수 + 3 * 폭발한 3번 구슬 개수를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : N, M
 * 둘째 줄 ~ N개 줄 : 격자의 구슬 정보
 * 다음 줄 ~ M개 줄 : 블리자드 정보
 *
 * 제한 요소
 * 3 <= N <= 49
 * N % 2 == 1
 * 1 <= M <= 100
 *
 * 생각나는 풀이
 * 구현
 * 1. 맵에 구슬 저장
 * 2. 블리자드 마법 수행
 * 	- 블리자드 경로 구슬 null로 변경
 * 3. 맵을 중앙부터 탐색하며 구슬을 list에 담음
 * 4. 맨앞부터 구슬 카운트세면서 카운트 4 이상이면 전부 삭제함
 * 	- 연속된 구슬이 3개 이하인 경우만 남을 때까지 반복
 * 5. 앞부터 탐색하며 카운트 세면서 새로운 리스트에 조건에 맞는 구슬 담음
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_21611 {
	static int N;
	static int[] bombs = new int[4];
	static Node[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1}; // 좌하우상
	static int[] dy = {-1, 0, 1, 0}; // 좌하우상
	static int[] bIdx = {0, 3, 1, 0, 2};
	static Node head = new Node(0);

	static class Node {
		int type;
		Node prev, next;

		public Node(int type) {
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new Node[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 0) continue;
				map[i][j] = new Node(value);
			}
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			shoot(dir, length);
			fillList();
			boom();
			change();
			setMap();
		}

		int ans = 0;
		for (int i = 1; i <= 3; i++) ans += i * bombs[i];
		System.out.println(ans);
	}

	private static void setMap() {
		initVisited();
		int x = N >> 1;
		int y = (N >> 1) - 1;
		Node node = head.next;
		int dir = 0;
		while (isInRange(x, y) && node != null) {
			visited[x][y] = true;
			map[x][y] = node;
			node = node.next;
			int nextDir = (dir + 1) % 4;
			if (!visited[x + dx[nextDir]][y + dy[nextDir]]) {
				dir = nextDir;
			}
			x += dx[dir];
			y += dy[dir];
		}
	}

	private static void change() {
		if (head.next == null) return;
		Node temp = new Node(0);
		Node tail = temp;
		int nodes = 1;
		Node now = head.next;
		int prev = now.type;
		int cnt = 0;
		for (; now != null; now = now.next) {
			if (prev == now.type) {
				cnt++;
			} else {
				Node nextNode = new Node(cnt);
				nextNode.prev = tail;
				tail.next = nextNode;
				tail = tail.next;
				Node nextNode2 = new Node(prev);
				nextNode2.prev = tail;
				tail.next = nextNode2;
				tail = tail.next;
				nodes += 2;
				if (nodes == N * N) {
					head = temp;
					return;
				}
				cnt = 1;
			}
			prev = now.type;
		}
		Node nextNode = new Node(cnt);
		nextNode.prev = tail;
		tail.next = nextNode;
		tail = tail.next;
		Node nextNode2 = new Node(prev);
		nextNode2.prev = tail;
		tail.next = nextNode2;
		head = temp;
	}

	private static void boom() {
		boolean isFinish = false;
		int same = 0;
		int prev = 0;
		Node tail = null;
		while (!isFinish) {
			isFinish = true;

			tail = head.next;
			if (tail == null) break;
			prev = tail.type;
			same = 0;
			Node now = head.next;
			for (; now != null; now = now.next) {
				if (now.type == prev) same++;
				else {
					if (same >= 4) {
						isFinish = false;
						bombs[prev] += same;
						Node start = now.prev;
						for (int i = 0; i < same; i++) {
							start = start.prev;
						}
						start.next = now;
						now.prev = start;
					}
					same = 1;
				}
				prev = now.type;
				tail = now;
			}
		}
		if (same >= 4) {
			for (int i = 0; i < same; i++) {
				tail = tail.prev;
			}
			bombs[prev] += same;
		}
	}

	private static void fillList() {
		initVisited();
		head = new Node(0);
		int x = N >> 1;
		int y = (N >> 1) - 1;
		Node tail = head;
		int dir = 0;
		while (isInRange(x, y)) {
			visited[x][y] = true;
			if (map[x][y] != null) {
				tail.next = map[x][y];
				map[x][y].prev = tail;
				tail = tail.next;
				map[x][y] = null;
			}
			int nextDir = (dir + 1) % 4;
			if (!visited[x + dx[nextDir]][y + dy[nextDir]]) {
				dir = nextDir;
			}
			x += dx[dir];
			y += dy[dir];
		}
	}

	private static boolean isInRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void initVisited() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		visited[N >> 1][N >> 1] = true;
	}

	private static void shoot(int dir, int length) {
		int x = N >> 1;
		int y = N >> 1;
		dir = bIdx[dir];
		for (int i = 1; i <= length; i++) {
			map[x + (i * dx[dir])][y + (i * dy[dir])] = null;
		}
	}

}