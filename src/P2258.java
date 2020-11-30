import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2258 {
	static int N, M, R, C, ans;
	static int[][] grid;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new int[R + 1];
		ans = Integer.MAX_VALUE;
		dfs(1, 0);
		System.out.println(ans);
	}

	static void calc() {
		int[][] dp = new int[M + 1][C + 1]; // dp[i][j]前i列选j列且选了第i列的最小分数
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= C; j++) {
				dp[i][j] = Integer.MAX_VALUE >> 1;
			}
		}

		int[] sumx = new int[M + 1];
		for (int col = 1; col <= M; col++) {
			for (int count = 1; count < R; count++)
				sumx[col] += Math.abs(grid[visited[count]][col] - grid[visited[count + 1]][col]);
		}
		// TODO

		for (int i = 1; i <= M; i++) {
			dp[i][1] = sumx[i]; // 边界条件
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 2; j <= C; j++) {
				for (int k = j - 1; k < i; k++) {
					int sumy = 0;
					for (int count = 1; count <= R; count++)
						sumy += Math.abs(grid[visited[count]][i] - grid[visited[count]][k]);
					dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + sumx[i] + sumy);
				}
			}
		}

		for (int i = C; i <= M; i++) {
			ans = Math.min(ans, dp[i][C]);
		}
	}

	static void dfs(int count, int previousLineId) {
		if (count > R) {
			calc();
			return;
		}
		for (int i = previousLineId + 1; i <= N; i++) {
			visited[count] = i;
			dfs(count + 1, i);
		}
	}

}
