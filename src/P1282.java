import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1282 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCard = Integer.parseInt(f.readLine());
		int[] cardUps = new int[numCard + 1];
		int[] cardDowns = new int[numCard + 1];
		int currentDiff = 0;
		for (int c = 1; c <= numCard; c++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			cardUps[c] = Integer.parseInt(st.nextToken());
			cardDowns[c] = Integer.parseInt(st.nextToken());
			currentDiff += cardUps[c] - cardDowns[c];
		}

		int[][] dp = new int[numCard + 1][10001];
		for (int i = 0; i <= numCard; i++) {
			Arrays.fill(dp[i], 1001);
			dp[i][currentDiff + 5000] = 0;
		}
		for (int doneCard = 0; doneCard < numCard; doneCard++) {
			for (int diff = 0; diff <= 10000; diff++) {
				dp[doneCard + 1][diff] = Math.min(dp[doneCard + 1][diff], dp[doneCard][diff]);
				int newDiff;
				newDiff = diff - 2 * (cardUps[doneCard + 1] - cardDowns[doneCard + 1]);
				if (newDiff <= 10000 && newDiff >= 0)
					dp[doneCard + 1][newDiff] = Math.min(dp[doneCard + 1][newDiff], dp[doneCard][diff] + 1);
			}
		}
		int leastVal = 10001;
		int min = 1000;
		for (int diff = 0; diff <= 10000; diff++) {
			int actualDiff = Math.abs(diff - 5000);
			if (actualDiff < leastVal && dp[numCard][diff] < 1001) {
				leastVal = actualDiff;
				min = dp[numCard][diff];
			}
			if (actualDiff == leastVal && dp[numCard][diff] < min) {
				min = dp[numCard][diff];
			}
		}
		System.out.println(min);

	}
}
