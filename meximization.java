import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class meximization {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numTest = Integer.parseInt(f.readLine());
		for (int i = 0; i < numTest; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			int[] arr = new int[101];
			TreeSet<Integer> items = new TreeSet<>();
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[num]++;
				items.add(num);
			}
			int[] results = new int[n];
			int count = 0;
			for (int j : items) {
				results[count] = j;
				arr[j]--;
				count++;
			}
			for (int j = 0; j < 101; j++) {
				while (arr[j] > 0) {
					results[count] = j;
					count++;
					arr[j]--;
				}
			}
			assert count == n;

			for (int j = 0; j < n; j++) {
				if (j == n - 1)
					System.out.println(results[j]);
				else
					System.out.print(results[j] + " ");

			}
		}
	}
}
