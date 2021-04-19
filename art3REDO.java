import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class art3REDO {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		int[] color = new int[n + 2];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 1; i <= n; i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][i] = 1;
			for (int j = i + 1; j <= n; j++) {
				dp[i][j] = Integer.MAX_VALUE / 2;
			}
		}
		for (int r = 2; r <= n; r++) {
			for (int i = 1; i + r - 1 <= n; i++) {
				int j = i + r - 1;
				if (color[i] == color[j])
					dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]);
				else
					for (int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
					}
			}
		}

		System.out.println(dp[1][n]);
	}
}
