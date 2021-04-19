import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class marray {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numTest = Integer.parseInt(f.readLine());
		for (int i = 0; i < numTest; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			int[] hist = new int[m];
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken()) % m;
				hist[arr[j]]++;
			}
			int count = 0;
			for (int j = 0; j < m; j++) {
				int a = hist[j];
				int b = hist[(m - j) % m];
				if (a == 0 || b == 0)
					continue;
				if (a < b) {
					hist[(m - j) % m] -= a + 1;
					hist[j] = 0;
				} else if (b < a) {
					hist[j] -= b + 1;
					hist[(m - j) % m] = 0;
				} else {
					hist[j] = 0;
					hist[(m - j) % m] = 0;
				}
				count++;
			}
			for (int j = 0; j < m; j++) {
				count += hist[j];
			}
			System.out.println(count);
		}
	}
}
