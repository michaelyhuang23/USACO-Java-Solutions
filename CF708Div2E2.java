import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CF708Div2E2 {
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
			int[] arr = new int[n + 1];
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
			int[][] fp = new int[n + 1][k + 1];
			for (int j = 0; j <= k; j++) {
				int r = 1;
				HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
				tracker.put(arr[1], 1);
				int countTracker = 0;
				for (int l = 1; l <= n; l++) {
					while (countTracker <= j) {
						fp[l][j] = r;
						r++;
						if (r > n) {
							break;
						}
						if (tracker.containsKey(arr[r])) {
							countTracker++;
							tracker.put(arr[r], tracker.get(arr[r]) + 1);
						} else
							tracker.put(arr[r], 1);
					}
					if (r > n) {
						fp[l][j] = n;
						continue;
					}
					tracker.put(arr[r], tracker.get(arr[r]) - 1);
					if (tracker.get(arr[r]) == 0)
						tracker.remove(arr[r]);
					else
						countTracker--;
					r--;
					tracker.put(arr[l], tracker.get(arr[l]) - 1);
					if (tracker.get(arr[l]) == 0)
						tracker.remove(arr[l]);
					else
						countTracker--;
				}
			}

			int[][] dp = new int[n + 1][k + 1];
			HashSet<Integer> track = new HashSet<Integer>();
			int c = 1;
			for (int j = 1; j <= n; j++) {
				if (track.contains(arr[j])) {
					track = new HashSet<Integer>();
					dp[c][0] = j - 1;
					c++;
				}
				track.add(arr[j]);
			}
			for (int j = 1; j <= n; j++) {
				for (int j2 = 0; j2 <= k; j2++) {
					for (int j3 = 0; j3 <= j2; j3++) {
						if (dp[j - 1][j2 - j3] + 1 > n)
							dp[j][j2] = Math.max(dp[j][j2], n);
						else
							dp[j][j2] = Math.max(dp[j][j2], fp[dp[j - 1][j2 - j3] + 1][j3]);
					}
				}
			}
			int min = Integer.MAX_VALUE / 2;
			for (int j = n; j >= 0; j--) {
				for (int j2 = 0; j2 <= k; j2++) {
					if (dp[j][j2] >= n)
						min = j;
				}
			}
			System.out.println(min);
		}
	}
}
