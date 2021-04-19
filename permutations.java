import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class permutations {
	private static boolean getSign(int x1, int y1, int x2, int y2) {
		int val = x1 * y2 - y1 * x2; // cross product
		assert val != 0; // else we have collinearity
		if (val > 0)
			return true;
		else
			return false;
	}

	private static boolean containPrt(int prt, int a, int b, int c) {
		int newAx = x[a] - x[prt];
		int newAy = y[a] - y[prt];
		int newBx = x[b] - x[prt];
		int newBy = y[b] - y[prt];
		int newCx = x[c] - x[prt];
		int newCy = y[c] - y[prt];
		boolean sign1 = getSign(newAx, newAy, newBx, newBy);
		boolean sign2 = getSign(newBx, newBy, newCx, newCy);
		boolean sign3 = getSign(newCx, newCy, newAx, newAy);
		if (sign1 && sign2 && sign3)
			return true;
		if (!sign1 && !sign2 && !sign3)
			return true;
		return false;
	}

	static int[] x;
	static int[] y;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		x = new int[n];
		y = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		int[][][] prtCount = new int[n][n][n];
		for (int a = 0; a < n; a++) {
			for (int b = a + 1; b < n; b++) {
				for (int c = b + 1; c < n; c++) {
					for (int i = 0; i < n; i++) {
						if (i == a || i == b || i == c)
							continue;
						if (containPrt(i, a, b, c))
							prtCount[a][b][c]++;
					}
				}
			}
		}
		long MOD = (long) (1e9 + 7);
		long[][][][] dp = new long[n + 1][n][n][n]; // 1 dim reducible
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if (b == a)
					continue;
				for (int c = 0; c < n; c++) {
					if (c == b || c == a)
						continue;
					dp[3][a][b][c] = 1;
				}
			}
		}
		for (int i = 4; i <= n; i++) {
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					if (b == a)
						continue;
					for (int c = 0; c < n; c++) {
						if (c == b || c == a)
							continue;
						int newC = Math.max(a, Math.max(b, c));
						int newA = Math.min(a, Math.min(b, c));
						int newB = 0;
						if (a != newA && a != newC)
							newB = a;
						if (b != newA && b != newC)
							newB = b;
						if (c != newA && c != newC)
							newB = c;
						int already = i - 1 - 3;
						int k = prtCount[newA][newB][newC] - already;
						dp[i][a][b][c] = dp[i - 1][a][b][c] * k;
						for (int x = 0; x < n; x++) {
							if (x == a || x == b || x == c)
								continue;
							if (!containPrt(x, newA, newB, newC))
								continue;
							dp[i][a][b][c] += dp[i - 1][a][b][x] + dp[i - 1][x][b][c] + dp[i - 1][a][x][c];
							dp[i][a][b][c] %= MOD;
							if(dp[i][a][b][c]<0)
								System.out.println('o');
						}
					}
				}
			}
		}
		long total = 0;
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if (b == a)
					continue;
				for (int c = 0; c < n; c++) {
					if (c == b || c == a)
						continue;
					total += dp[n][a][b][c];
					total %= MOD;
				}
			}
		}
		System.out.println(total);
	}
}
