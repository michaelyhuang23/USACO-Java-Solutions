import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class squarefreediv {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numTest = Integer.parseInt(f.readLine());
		for (int i = 0; i < numTest; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			long[] arr = new long[n];
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			boolean[] stop = new boolean[n];
			int prev = 0;
			for (int j = 0; j < n; j++) {
				for (int j2 = prev; j2 < j; j2++) {
					if (isSquare(arr[j] * arr[j2])) {
						stop[j] = true;
						prev = j;
					}
				}
			}
			int count = 0;
			for (int j = 0; j < stop.length; j++) {
				if (stop[j])
					count++;
			}
			System.out.println(count + 1);
		}
	}

	private static boolean isSquare(long l) {
		long k = Math.round(Math.sqrt(l));
		return k * k == l;
	}
}
