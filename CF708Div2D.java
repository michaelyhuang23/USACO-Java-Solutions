import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF708Div2D {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] tags = new int[n + 1];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 1; j <= n; j++) {
				tags[j] = Integer.parseInt(st.nextToken());
			}
			int[] scores = new int[n + 1];
			st = new StringTokenizer(f.readLine());
			for (int j = 1; j <= n; j++) {
				scores[j] = Integer.parseInt(st.nextToken());
			}

			long[] dp = new long[n];

			for (int A = 2; A <= n; A++) {
				long[] prefix = new long[A];
				for (int d = 1; d <= A - 1; d++)
					prefix[d] = Math.max(prefix[d - 1], dp[d]);
				for (int d = A - 1; d >= 3; d--) {
					if (tags[A - d] != tags[A] && tags[A - 1] != tags[A - d]) {
						long score = Math.abs(scores[A - d] - scores[A]) + Math.abs(scores[A - d] - scores[A - 1]);
						dp[d] = prefix[d - 2] + score;
					}
				}
				if (A - 1 > 0 && tags[A - 1] != tags[A])
					dp[1] = prefix[A - 1] + Math.abs(scores[A - 1] - scores[A]);
			}
			long max = 0;
			for (int d = 1; d < n; d++) {
				long maxExtra = 0;
				for (int j = n-d-1; j >= 1; j--) {
					maxExtra=Math.max(maxExtra, Math.abs(scores[j]-scores[n]));
				}
				max = Math.max(max, dp[d]+maxExtra);
			}
			System.out.println(max);
		}
	}
}
