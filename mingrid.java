import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mingrid {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			long[] costs = new long[n + 1];
			for (int j = 1; j <= n; j++) {
				costs[j] = Integer.parseInt(st.nextToken());
			}
			// choose up first
			long[] prefix = new long[n + 1];
			for (int j = 1; j <= n; j++) {
				prefix[j] = prefix[j - 1] + costs[j];
			}
			long[] minUp = new long[n + 1];
			Arrays.fill(minUp, Integer.MAX_VALUE);
			for (int j = 1; j <= n; j++) {
				minUp[j] = minUp[j - 1];
				if (j % 2 == 1)
					minUp[j] = Math.min(minUp[j], costs[j]);
			}
			long[] minRight = new long[n + 1];
			Arrays.fill(minRight, Integer.MAX_VALUE);
			for (int j = 1; j <= n; j++) {
				minRight[j] = minRight[j - 1];
				if (j % 2 == 0)
					minRight[j] = Math.min(minRight[j], costs[j]);
			}
			long cost1 = Long.MAX_VALUE / 2;
			for (int p = 1; p <= n; p++) {
				long cost = prefix[p];
				int upLeft = n - (p + 1) / 2;
				int rightLeft = n - p / 2;
				cost += upLeft * minUp[p];
				cost += rightLeft * minRight[p];
				cost1 = Math.min(cost1, cost);
			}

			// choose right first
			prefix = new long[n + 1];
			for (int j = 1; j <= n; j++) {
				prefix[j] = prefix[j - 1] + costs[j];
			}
			minUp = new long[n + 1];
			Arrays.fill(minUp, Integer.MAX_VALUE);
			for (int j = 1; j <= n; j++) {
				minUp[j] = minUp[j - 1];
				if (j % 2 == 0)
					minUp[j] = Math.min(minUp[j], costs[j]);
			}
			minRight = new long[n + 1];
			Arrays.fill(minRight, Integer.MAX_VALUE);
			for (int j = 1; j <= n; j++) {
				minRight[j] = minRight[j - 1];
				if (j % 2 == 1)
					minRight[j] = Math.min(minRight[j], costs[j]);
			}
			long cost2 = Long.MAX_VALUE / 2;
			for (int p = 1; p <= n; p++) {
				long cost = prefix[p];
				int upLeft = n - p / 2;
				int rightLeft = n - (p + 1) / 2;
				cost += upLeft * minUp[p];
				cost += rightLeft * minRight[p];
				cost2 = Math.min(cost2, cost);
			}
			System.out.println(Math.min(cost1, cost2));
		}
	}
}
