import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF708Div2D_redo {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] exp = new int[30];
		exp[0] = 1;
		for (int i = 1; i < 30; i++)
			exp[i] = exp[i - 1] * 2; // if a==2 : exp[i] = exp[i-1]<<1
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] tags = new int[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				tags[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(f.readLine());
			long[] ss = new long[n];
			for (int j = 0; j < n; j++) {
				ss[j] = Integer.parseInt(st.nextToken());
			}
			long[] dp = new long[n];
			for (int v = 1; v < n; v++) {
				for (int u = v - 1; u >= 0; u--) {
					if (tags[u] == tags[v])
						continue;
					long oldv = dp[v];
					long oldu = dp[u];
					dp[u] = Math.max(oldu, oldv + Math.abs(ss[u] - ss[v]));
					dp[v] = Math.max(oldv, oldu + Math.abs(ss[u] - ss[v]));
				}
			}
			long max = 0;
			for (int j = 0; j < dp.length; j++) {
				max = Math.max(max, dp[j]);
			}
			System.out.println(max);
		}
	}
}
