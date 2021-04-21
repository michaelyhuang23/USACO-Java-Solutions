import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CFE106F {
	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		connector = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			connector[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			connector[x].add(y);
			connector[y].add(x);
		}
		dp = new long[n][k + 1];
		dp2 = new long[n][k + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
			dp2[i][0] = 1;
		}
		dfs(0, 0);
		long max = 0;
		for (int j = 0; j <= k; j++) {
			max = Math.max(max, dp2[0][j]);
		}
		System.out.println(safeMod(max));
	}

	static ArrayList<Integer>[] connector;
	static int n, k;
	static long[][] dp, dp2;
	static long MOD = 998244353L;

	private static void dfs(int cur, int parent) {
		// TODO Auto-generated method stub
		if (cur != parent && connector[cur].size() == 1 || connector[cur].size() == 0) {
			dp[cur][0] = 1;
			dp2[cur][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp2[cur][j] = dp[cur][j] + dp2[cur][j - 1];
				dp2[cur][j] = safeMod(dp2[cur][j]);
			}
			return;
		}
		for (int son : connector[cur]) {
			if (son == parent)
				continue;
			dfs(son, cur);
		}
		for (int son : connector[cur]) {
			if (son == parent)
				continue;
			dp[cur][0] *= dp2[son][k];
			dp[cur][0] = safeMod(dp[cur][0]);
		}
		dp2[cur][0] = dp[cur][0];
		for (int j = 1; j <= k / 2; j++) { // mind j=0
			dp2[cur][j] = 1;
			for (int son : connector[cur]) {
				if (son == parent)
					continue;
				dp2[cur][j] *= safeMod(dp2[son][j - 1] + dp2[son][k]);
				dp2[cur][j] = safeMod(dp2[cur][j]);
			}
			dp[cur][j] = dp2[cur][j] - dp2[cur][j - 1]; // mind j=1 case
			dp[cur][j] = safeMod(dp[cur][j]);
		}
		for (int j = k / 2 + 1; j <= k; j++) {
			long prod = 1;
			for (int son : connector[cur]) {
				if (son == parent)
					continue;
				long tmp = 0;
				if (k - j - 1 >= 0)
					tmp = dp2[son][k - j - 1];
				prod *= safeMod(tmp + dp2[son][k]);
				prod = safeMod(prod);
			}
			long sum = 0;
			for (int son : connector[cur]) {
				if (son == parent)
					continue;
				long tmp = 0;
				if (k - j - 1 >= 0)
					tmp = dp2[son][k - j - 1];
				sum += safeMod(safeMod(prod * safeMod(gcdInverse(tmp + dp2[son][k]))) * dp[son][j - 1]);
				sum = safeMod(sum);
			}
			dp[cur][j] = sum;
			dp2[cur][j] = dp2[cur][j - 1] + dp[cur][j];
			dp2[cur][j] = safeMod(dp2[cur][j]);
		}

	}

	private static long gcdInverse(long a) {
		x1 = 1;
		x2 = 1;
		gcdExtended(a, MOD);
		return x1;
	}

	static long x1, x2;

	public static void gcdExtended(long a, long b) {
		// Base Case
		if (a == 0) {
			x1 = 0L;
			x2 = 1L;
			return;
		}

		x1 = 1;
		x2 = 1;
		gcdExtended(b % a, a);

		// Update x and y using results of recursive
		// call
		long x = x2 - (b / a) * x1;
		long y = x1;
		x1 = x;
		x2 = y;
		return;
	}

	private static long safeMod(long n) {
		return (n % MOD + MOD) % MOD;
	}
}
