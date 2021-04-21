import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AT2015 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] bots = new int[R + 1][C + 1];
		int eR = 0, eC = 0;
		for (int r = 1; r <= R; r++) {
			String input = f.readLine();
			for (int c = 1; c <= C; c++) {
				bots[r][c] = (input.charAt(c - 1) == 'o') ? 1 : 0;
				if (input.charAt(c - 1) == 'E') {
					eR = r;
					eC = c;
				}
			}
		}
		assert eR != 0 && eC != 0;
		int[][] countr = new int[R + 1][C + 1];
		int[][] countc = new int[C + 1][R + 1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				countr[r][c] = countr[r][c - 1] + bots[r][c];
			}
		}
		for (int c = 1; c <= C; c++) {
			for (int r = 1; r <= R; r++) {
				countc[c][r] = countc[c][r - 1] + bots[r][c];
			}
		}
		short[][][][] dp = new short[R + 1][R + 1][C + 1][C + 1];

		int max = 0;
		for (int h = 1; h <= R; h++) {
			for (int u = 1; u <= R; u++) {
				int d = u + h - 1;
				if (d < eR || u > eR || d > R)
					continue; // up down inclusiveness
				for (int w = 1; w <= C; w++) {
					for (int l = 1; l <= C; l++) {
						int r = l + w - 1;
						if (r < eC || l > eC || r > C)
							continue; // left right inclusiveness
						int lbound = r - (eC - 1);
						int rbound = l + (C - eC);
						int ubound = d - (eR - 1);
						int dbound = u + (R - eR); // they are last that can be accessed;
						int ubound2 = Math.max(ubound, u);
						int dbound2 = Math.min(dbound, d);
						int lbound2 = Math.max(lbound, l);
						int rbound2 = Math.min(rbound, r);
						assert ubound <= R && dbound > 0 && lbound <= C && rbound > 0;
						if (u - 1 >= 0 && u - 1 >= ubound) {
							dp[u - 1][d][l][r] = (short) Math.max(dp[u - 1][d][l][r],
									dp[u][d][l][r] + countr[u - 1][rbound2] - countr[u - 1][lbound2 - 1]);
						}
						if (d + 1 <= R && d + 1 <= dbound) {
							dp[u][d + 1][l][r] = (short) Math.max(dp[u][d + 1][l][r],
									dp[u][d][l][r] + countr[d + 1][rbound2] - countr[d + 1][lbound2 - 1]);
						}
						if (l - 1 >= 0 && l - 1 >= lbound) {
							dp[u][d][l - 1][r] = (short) Math.max(dp[u][d][l - 1][r],
									dp[u][d][l][r] + countc[l - 1][dbound2] - countc[l - 1][ubound2 - 1]);
						}
						if (r + 1 <= R && r + 1 <= rbound) {
							dp[u][d][l][r + 1] = (short) Math.max(dp[u][d][l][r + 1],
									dp[u][d][l][r] + countc[r + 1][dbound2] - countc[r + 1][ubound2 - 1]);
						}

						// bound/initialization implication!

						max = Math.max(max, dp[u][d][l][r]);
					}
				}
			}
		}
		System.out.println(max);
	}
}
