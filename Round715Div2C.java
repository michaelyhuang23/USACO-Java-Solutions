import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Round715Div2C {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		long[] arr = new long[n+1];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 1; i < n+1; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long[][] dp = new long[n+1][n+1];
		for (int i = 0; i <= n; i++) {
			dp[i][i]=0;
		}
		for (int i = 2; i <= n; i++) {
			for (int start = 1; i+start-1 <= n; start++) {
				int end = i+start-1;
				dp[start][end]=Math.min(dp[start+1][end], dp[start][end-1])+arr[end]-arr[start];
			}
		}
		System.out.println(dp[1][n]);
	}
}
