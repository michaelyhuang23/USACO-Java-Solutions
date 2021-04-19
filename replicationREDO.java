import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class replicationREDO {
	static class Point {
		int r, c, t;

		public Point(int R, int C, int T) {
			r = R;
			c = C;
			t = T;
		}
	}

	public static void main(String[] args) throws IOException {
		final int[][] DIR = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int len = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());
		int[][] blockD = new int[len][len];

		Queue<Point> frontier = new ArrayDeque<>();
		boolean[][] visited = new boolean[len][len];
		int[][] sizes = new int[len][len];
		for (int i = 0; i < len; i++) {
			String input = f.readLine();
			for (int j = 0; j < len; j++) {
				if (input.charAt(j) == '#')
					blockD[i][j] = 0;
				else
					blockD[i][j] = Integer.MAX_VALUE / 2;
				if (input.charAt(j) == 'S') {
					frontier.offer(new Point(i, j, 0)); // handle visited
					visited[i][j] = true;
					sizes[i][j] = 1;
				}
			}
		}
		for (int r = 1; r < len; r++) // down
			for (int c = 0; c < len; c++)
				blockD[r][c] = Math.min(blockD[r][c], blockD[r - 1][c] + 1);

		for (int r = len - 2; r >= 0; r--) // up
			for (int c = 0; c < len; c++)
				blockD[r][c] = Math.min(blockD[r][c], blockD[r + 1][c] + 1);

		for (int c = 1; c < len; c++) // right
			for (int r = 0; r < len; r++)
				blockD[r][c] = Math.min(blockD[r][c], blockD[r][c - 1] + 1);

		for (int c = len - 2; c >= 0; c--) // left
			for (int r = 0; r < len; r++)
				blockD[r][c] = Math.min(blockD[r][c], blockD[r][c + 1] + 1);

		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				assert blockD[i][j] < Integer.MAX_VALUE / 3;

		while (!frontier.isEmpty()) {
			Point prt = frontier.poll();
			int size;
			if (prt.t % time == 0) {
				size = prt.t / time + 1;
				if (size > blockD[prt.r][prt.c])
					continue;
				sizes[prt.r][prt.c] = size;
			}
			for (int i = 0; i < 4; i++) {
				int newR = prt.r + DIR[i][0];
				int newC = prt.c + DIR[i][1];
				size = prt.t / time + 1;
				if (size > blockD[newR][newC])
					continue;
				if (visited[newR][newC])
					continue;
				visited[newR][newC] = true;
				sizes[newR][newC] = size;
				frontier.offer(new Point(newR, newC, prt.t + 1));
			}
		}

		int[][] botD = new int[len][len];
		for (int i = 0; i < botD.length; i++) {
			Arrays.fill(botD[i], Integer.MAX_VALUE / 2);
		}
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (visited[i][j])
					botD[i][j] = -sizes[i][j] + 1;
			}
		}
		for (int r = 1; r < len; r++) // down
			for (int c = 0; c < len; c++)
				botD[r][c] = Math.min(botD[r][c], botD[r - 1][c] + 1);

		for (int r = len - 2; r >= 0; r--) // up
			for (int c = 0; c < len; c++)
				botD[r][c] = Math.min(botD[r][c], botD[r + 1][c] + 1);

		for (int c = 1; c < len; c++) // right
			for (int r = 0; r < len; r++)
				botD[r][c] = Math.min(botD[r][c], botD[r][c - 1] + 1);

		for (int c = len - 2; c >= 0; c--) // left
			for (int r = 0; r < len; r++)
				botD[r][c] = Math.min(botD[r][c], botD[r][c + 1] + 1);

		int count = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (botD[i][j] <= 0)
					count++;
			}
		}
		System.out.println(count);
	}
}
