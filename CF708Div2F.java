
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CF708Div2F {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		int maxNum = (int) 1e7;
		int[] maxPrimeDiv = new int[maxNum + 1];
		for (int i = 2; i <= maxNum; i++) {
			if (maxPrimeDiv[i] == 0) {
				for (int j = i; j <= maxNum; j += i) {
					maxPrimeDiv[j] = i;
				}
			}
		}
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			int[] arr = new int[n+1];
			for (int j = 1; j <= n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 1; j <= n; j++) {
				// note special case of 1
				int result = 1;
				int temp = arr[j];
				while (temp > 1) {
					int count = 0;
					int div = maxPrimeDiv[temp];
					while (temp % div == 0) {
						temp /= div;
						count++;
					}
					if (count % 2 == 1)
						result *= div;
				}
				arr[j] = result;
			}
			int[][] dp = new int[n+1][k+1];
			for (int j = 1; j <= n; j++) {
				for (int k1 = 1; k1 <= k; k1++) {
					dp[j][k1]=Math.min(dp[j-1][k1],dp[j-1][k1-1]);
				}
			}
		}
	}
}
