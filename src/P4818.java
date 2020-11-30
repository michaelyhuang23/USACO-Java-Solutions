import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4818 {
	static int numRow, numCol;
	static int[][] map;
	static final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static boolean[][][] visited;
	static int minDist = Integer.MAX_VALUE;

	static class Point {
		int row, col, hasOrange, step;

		public Point(int r, int c, int orange, int s) {
			row = r;
			col = c;
			hasOrange = orange;
			step = s;
		}

		public boolean isSlider() {
			return false;
		}
	}

	static class Slider extends Point {
		int[] direction;

		public Slider(int r, int c, int orange, int s, int[] D) {
			super(r, c, orange, s);
			direction = D;
		}

		public boolean isSlider() {
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); // new FileReader("shortcut.in") //new
																					// InputStreamReader(System.in)
		// PrintWriter out = new PrintWriter(new BufferedWriter(new
		// FileWriter("shortcut.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRow = Integer.parseInt(st.nextToken());
		numCol = Integer.parseInt(st.nextToken());
		map = new int[numRow][numCol];
		visited = new boolean[numRow][numCol][2];

		for (int r = 0; r < numRow; r++) {
			st = new StringTokenizer(f.readLine());
			for (int c = 0; c < numCol; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		bfs();
		if (minDist >= Integer.MAX_VALUE / 2 - 1)
			System.out.println(-1);
		else
			System.out.println(minDist);

	}

	private static void bfs() {
		Queue<Point> frontier = new ArrayDeque<>();
		frontier.offer(new Point(0, 0, 0, 0));
		while (!frontier.isEmpty()) {
			Point thisPoint = frontier.poll();
			if (thisPoint.isSlider()) {
				Slider thisSlider = (Slider) thisPoint;
				int newR = thisSlider.row + thisSlider.direction[0];
				int newC = thisSlider.col + thisSlider.direction[1];
				if (newR < 0 || newR >= numRow || newC < 0 || newC >= numCol || map[newR][newC] == 0
						|| map[newR][newC] == 3) {
					thisPoint = new Point(thisSlider.row, thisSlider.col, 0, thisSlider.step);
				} else if (map[newR][newC] != 4) {
					frontier.offer(new Point(newR, newC, (map[newR][newC] == 2) ? 1 : 0, thisSlider.step + 1));
					continue;
				} else {
					frontier.offer(new Slider(newR, newC, 0, thisSlider.step + 1, thisSlider.direction));
					continue;
				}
			}
			if (visited[thisPoint.row][thisPoint.col][thisPoint.hasOrange]) {
				continue;
			}
			visited[thisPoint.row][thisPoint.col][thisPoint.hasOrange] = true;
			if (thisPoint.row == numRow - 1 && thisPoint.col == numCol - 1)
				minDist = Math.min(minDist, thisPoint.step);

			for (int i = 0; i < 4; i++) {
				int newR = thisPoint.row + DIR[i][0];
				int newC = thisPoint.col + DIR[i][1];
				int newHasOrange = thisPoint.hasOrange;
				if (newR < 0 || newR >= numRow || newC < 0 || newC >= numCol)
					continue;
				if (map[newR][newC] == 0 || map[newR][newC] == 3 && thisPoint.hasOrange == 0)
					continue;
				if (map[newR][newC] == 2)
					newHasOrange = 1;
				if (map[newR][newC] == 4) {
					newHasOrange = 0;

					frontier.offer(new Slider(newR, newC, 0, thisPoint.step + 1, DIR[i]));
				} else
					frontier.offer(new Point(newR, newC, newHasOrange, thisPoint.step + 1));
			}
		}
	}
}
