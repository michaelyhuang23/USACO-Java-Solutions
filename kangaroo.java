import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class kangaroo {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int length = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		long MOD = (long) 1e9 + 7;
		long[][] dp = new long[length + 1][length + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= length; i++) {
			if (i == start || i == end) {
				for (int j = 1; j <= length; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
					dp[i][j] %= MOD;
				}
			} else {
				int totalPos = 0;
				if (i < start)
					totalPos++;
				if (i < end)
					totalPos++;
				for (int j = 1; j <= length; j++) { // mind upper bound
					dp[i][j] += dp[i - 1][j - 1] * (j-2+totalPos);
					dp[i][j] %= MOD;
					if (j < length)
						dp[i][j] += dp[i - 1][j + 1] * j;
					dp[i][j] %= MOD;

				}
			}
		}
		System.out.println(dp[length][1]);
	}
}
