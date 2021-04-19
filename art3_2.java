import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class art3_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] arr = new int[len + 2];
		int[] toPos = new int[len + 1];
		ArrayList<Integer>[] colorPos = new ArrayList[len + 1];
		for (int i = 0; i < colorPos.length; i++) {
			colorPos[i] = new ArrayList<>();
		}
		for (int i = 1; i <= len; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			toPos[i] = colorPos[arr[i]].size();
			colorPos[arr[i]].add(i);
		}

		int[][] dp = new int[len + 2][len + 2];
		for (int i = 1; i <= len; i++) {
			for (int j = i; j <= len; j++) {
				dp[i][j] = Integer.MAX_VALUE / 4;
			}
		}
		for (int range = 1; range <= len; range++) {
			for (int l = 1; l + range - 1 <= len; l++) {
				int r = l + range - 1;
				for (int i = l; i <= r; i++) {

					int first = toPos[i];
					assert first >= 0;
					for (int j = first; j < colorPos[arr[i]].size() && colorPos[arr[i]].get(j) <= r; j++) {
						int add = 1;
						int end = colorPos[arr[i]].get(j);
						if (l > 1 && r < len && arr[l - 1] == arr[i] && arr[r + 1] == arr[i]) // maybe or?
							add--;
						dp[l][r] = Math.min(dp[l][r], dp[i + 1][end - 1] + dp[l][i - 1] + dp[end + 1][r] + add);
						// initialization
					}
				}
			}
		}
		System.out.println(dp[1][len]);
	}
}
